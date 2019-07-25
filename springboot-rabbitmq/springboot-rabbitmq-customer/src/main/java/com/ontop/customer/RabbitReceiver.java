package com.ontop.customer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import com.ontop.entity.Order;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitReceiver {
	Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

	@SuppressWarnings("rawtypes")
	@RabbitListener(bindings = 
		@QueueBinding(
				value = @Queue(value = "queue-1", durable = "true"), 
				exchange = @Exchange(value = "exchange-1", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"), 
				key = "springboot.*")
	)
	@RabbitHandler
	public void onMessage(Message message, Channel channel) throws Exception {
		logger.info("--------------------------------------");
		// Payload即Producer发送过来的message
		logger.info("消费端Payload: " + message.getPayload());// Hello RabbitMQ For Spring Boot!
		logger.info("消费端Headers" + message.getHeaders());
		// Headers{amqp_receivedDeliveryMode=PERSISTENT,
		// amqp_receivedExchange=exchange-1, amqp_deliveryTag=1,
		// amqp_consumerQueue=queue-1, amqp_redelivered=false,
		// number=12345, amqp_receivedRoutingKey=springboot.abc,
		// spring_listener_return_correlation=d31b2b40-9594-4751-9ded-5f85db9cab99,
		// send_time=2019-07-25 09:13:48,
		// spring_returned_message_correlation=6f6718f6-bd73-4274-bce5-24739be3dc7f,
		// id=66d56277-7552-08ce-9475-335ea142b7d2,
		// amqp_consumerTag=amq.ctag-ByhQpbKA4ja13quXr7_C8g,
		// contentType=application/x-java-serialized-object, timestamp=1564017228756}
		Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		// 手工ACK
		// 作用:防止数据丢失，Customer异常退出，数据需经过一段时间处理，而未完成导致数据丢失
		channel.basicAck(deliveryTag, false);
	}

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}", durable = "${spring.rabbitmq.listener.order.queue.durable}"), exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}", durable = "${spring.rabbitmq.listener.order.exchange.durable}", type = "${spring.rabbitmq.listener.order.exchange.type}", ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"), key = "${spring.rabbitmq.listener.order.key}"))
	@RabbitHandler
	public void onOrderMessage(@Payload Order order, Channel channel, @Headers Map<String, Object> headers)
			throws Exception {
		log.info("--------------------------------------");
		log.info("消费端order: " + order.getId());
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		// 手工ACK
		channel.basicAck(deliveryTag, false);
	}

}
