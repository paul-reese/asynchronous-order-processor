package io.pivotal.sample.orderstatusservice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderStatusServiceApplication {

	@Bean
	public Queue queue() {
		return new Queue("result-queue");
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderStatusServiceApplication.class, args);
	}
}
