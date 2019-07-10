package com.ontop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.ontop.mapper")
public class SpringbootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);
	}

}
