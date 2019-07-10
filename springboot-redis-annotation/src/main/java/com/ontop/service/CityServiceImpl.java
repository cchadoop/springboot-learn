package com.ontop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontop.mapper.CityMapper;
import com.ontop.pojo.City;

@Service
public class CityServiceImpl implements CityService {

	/*
	 * @Cacheable
	 * @CachePut
	 * @CacheEvict  key值需一致
	 * 
	 */
	
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询所有城市
     * 查询逻辑：
     * 一般全量数据使不会存入缓存的，故直接查数据库，不需要和缓存有任何操作
     *
     * @return
     */
    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }

    /**
     * 通过Id查找城市
     * 查询逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存中不存在，从DB中获取并插入缓存
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    @Cacheable(value="redisCache",key="'redis_user_'+#id")
    public City findById(Long id) {
    		long start  =System.currentTimeMillis();
        City city = cityMapper.findById(id);
        long end  =System.currentTimeMillis();
        System.out.println("处理时间"+(end-start)+"ms");
        return city;
    }

    /**
     * 通过Id删除城市
     * 删除逻辑：
     * 如果删除的数据在缓存中存在，则把缓存中的数据也删除
     *
     * @param id
     */
    @Override
    @Transactional
    @CacheEvict(value="redisCache",beforeInvocation=false,key="'redis_user_'+#id")
    public int deleteById(Long id) {
        int result = cityMapper.deleteById(id);
        return result;
    }

    /**
     * 添加城市
     * 添加逻辑：
     * 直接插入数据库，不用操作缓存，因为后续查询的时候缓存中没有会添加。
     * 用户关心的也只是添加这个动作成功了没，插入DB中直接跟用户说成功就行，
     * 如果额外增加一部添加到缓存会多额外的时间消耗
     *
     * @param city
     * @return
     */
    @Override
    @Transactional
    //@CachePut(value="redisCache",key="'redis_user_'+#result.id")
    public int addCity(City city) {
        return cityMapper.addCity(city);
    }

    /**
     * 更新城市
     * 更新逻辑：
     * 先更新DB，再删除缓存（如果缓存存在）
     * 不建议先删除缓存再更新DB，在并发的环境下容易产生脏数据
     *
     * @param city
     * @return
     */
    @Override
    @Transactional
    @CachePut(value="redisCache",condition="#result!='null'",key="'redis_user_'+#result.id")
    //redis_user_
    public City updateCity(Long id,String cityName,String desc) {
    		City city = this.findById(id);
    		if(city ==null) {
    			return null;
    		}
    		city.setCityName(cityName);
    		city.setDesc(desc);
    		cityMapper.updateCity(city);
        return city;
    }
}
