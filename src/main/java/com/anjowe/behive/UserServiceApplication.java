package com.anjowe.behive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anjowe.behive.logger.AppLogger;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		AppLogger.log.info("Application initiated");
	}
	

}
