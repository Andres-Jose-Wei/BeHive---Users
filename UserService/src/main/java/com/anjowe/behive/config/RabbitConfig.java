package com.anjowe.behive.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ main configurations used through out the application.
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Wei Wu 
 */
@Configuration
public class RabbitConfig {
	
	/**Converts a message received from the messaging queue into a JSON object
	 * @return a message converter
	 */
	@Bean
	public MessageConverter msgConverter() {
		return new Jackson2JsonMessageConverter();
	}

}