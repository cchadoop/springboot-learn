package com.ontop.config.druid;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidConfig {
	
	private Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class);
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource druidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		LOGGER.info("Open druid连接池");
		return dataSource;
	}
}
