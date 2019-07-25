package com.ontop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontop.entity.Order;
import com.ontop.producer.RabbitSender;

@RestController
public class TestController {

	@Autowired
	private RabbitSender sender;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@GetMapping("sender")
	public String send() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put("number", "12345");
		properties.put("send_time", simpleDateFormat.format(new Date()));
		sender.send("Hello RabbitMQ For Spring Boot!", properties);
		return "success";
	}

	@GetMapping("sender2")
	public String send2() throws Exception {
		Order order = new Order("1", "第一个订单");
		sender.send(order);
		return "success";
	}
}
