package com.practicalMircorservices.eventProducer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;



@SpringBootApplication
@RabbitListener(queues = "crispyBunOrder")
@EnableAutoConfiguration
public class EventConsumerApplication {

	@Bean
	public Queue crispyBunOrderQueue() {
		return new Queue("crispyBunOrder");
	}
	
	@RabbitHandler
	public void process(@Payload CrispyBunOrder order) {
		StringBuffer SB = new StringBuffer();
		SB.append("New Order Received : \n");
		SB.append("OrderId : " + order.getOrderId());
		SB.append("\nItemId : " + order.getItemId());
		SB.append("\nUserName : " + order.getUserName());
		SB.append("\nDate : " + order.getOrderPlacedTime());
		System.out.println(SB.toString());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EventConsumerApplication.class, args);
	}

}
