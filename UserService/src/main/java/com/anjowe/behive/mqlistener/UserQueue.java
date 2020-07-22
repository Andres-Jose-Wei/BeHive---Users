package com.anjowe.behive.mqlistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjowe.behive.model.User;
import com.anjowe.behive.service.UserService;
/**
 * Class for consuming messages from the Rabbit messaging queue.
 * 
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Wei Wu 
 */
@Service
public class UserQueue {
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Consumes messages from the User queue
	 * @param user publisher-provided user object after registration
	 */
	@RabbitListener(queues = "${spring.rabbitmq.queue-listener}")
	public void recievedMessage(User user) {
		this.userService.createOrSaveUser(user);
	}
	
}
