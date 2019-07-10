package com.ontop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontop.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
