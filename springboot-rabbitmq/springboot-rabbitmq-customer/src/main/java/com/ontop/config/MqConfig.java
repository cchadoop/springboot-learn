package com.ontop.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages="com.ontop.*")
public class MqConfig {
	public static final String QUEUE = "queue-1";
	@Bean
	public Queue queue() {
		return new Queue(QUEUE,true);
	}
}
