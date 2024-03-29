package com.ontop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages="com.ontop.mapper")
public class SpringbootRedisAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisAnnotationApplication.class, args);
	}

}
