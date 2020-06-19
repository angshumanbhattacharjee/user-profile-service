package com.user.profile.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${data.transform.queue}")
	private String transformQueue;
	
	@Value("${spring.rabbitmq.host}")
	private String rabbitHostName;
	
	@Bean
	public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}
	
	@Bean
    public Queue queue() {
        return new Queue(this.transformQueue, true);
    }
	
	@Bean(name = "rabbit-mq")
	public CachingConnectionFactory getConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost(rabbitHostName);
		return cachingConnectionFactory;
	}

}
