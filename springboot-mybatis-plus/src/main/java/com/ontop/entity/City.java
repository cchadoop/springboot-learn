package com.ontop.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "t_city")
public class City implements Serializable {
	private static final long serialVersionUID = -94826303565497616L;

	@TableId(type = IdType.AUTO, value = "id")
	private Long id;
	@TableField(value = "city_name")
	private String cityName;
	@TableField(value = "description")
	private String description;
}
