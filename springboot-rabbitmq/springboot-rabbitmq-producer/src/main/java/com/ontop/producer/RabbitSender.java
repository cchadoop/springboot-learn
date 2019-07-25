package com.ontop.producer;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.ontop.entity.Order;

@Component
public class RabbitSender {
	Logger logger = LoggerFactory.getLogger(RabbitSender.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			logger.info("correlationData: " + correlationData);
			logger.info("ack: " + ack);
			if (!ack) {
				logger.info("异常处理....");
			}
		}
	};

	// 回调函数: return返回
	final ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
				String exchange, String routingKey) {
			logger.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}", exchange, routingKey,
					replyCode, replyText);
		}
	};

	// 发送消息方法调用: 构建Message消息
	public void send(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message<Object> msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		// id + 时间戳 全局唯一
		String id = UUID.randomUUID().toString();
		logger.info("id: {}", id);
		CorrelationData correlationData = new CorrelationData(id);
		rabbitTemplate.convertAndSend("exchange-1", "springboot.abc", msg, correlationData);
	}

	public void send(Order order) throws Exception {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		String id = UUID.randomUUID().toString();
		logger.info("sendOrder id: {}" + id);
		rabbitTemplate.convertAndSend("exchange-2", "springboot.def", order, new CorrelationData(id));
	}
}
