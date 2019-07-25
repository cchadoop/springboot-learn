package com.ontop.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
	private static final long serialVersionUID = 7293986973312565395L;
	private String id;
	private String name;
}
