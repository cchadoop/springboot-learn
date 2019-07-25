package com.ontop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ontop.entity.Order;
import com.ontop.producer.RabbitSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqProducerApplicationTests {

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private RabbitSender sender;

	@Test
	public void contextLoads() {
	}

	@Test
	public void send() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put("number", "12345");
		properties.put("send_time", simpleDateFormat.format(new Date()));
		sender.send("Hello RabbitMQ For Spring Boot!", properties);
	}

	@Test
	public void sendOrder() throws Exception {
		Order order = new Order("1", "第一个订单!");
		sender.send(order);
	}
}
