package com.ontop.Entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="t_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId(type=IdType.AUTO,value="id")
	private Long id;
	
	@TableField(value="t_name")
	private String name;
	@TableField(value="t_age")
	private int age;
	@TableField(value="t_birthday")
	private String birthday;
	
}
