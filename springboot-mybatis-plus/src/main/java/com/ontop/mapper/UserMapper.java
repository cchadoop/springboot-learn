package com.ontop.Dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontop.Entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
