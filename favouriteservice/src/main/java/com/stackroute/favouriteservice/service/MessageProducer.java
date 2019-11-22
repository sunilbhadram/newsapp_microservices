package com.stackroute.favouriteservice.service;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.config.RobbitMqConfig;
import com.stackroute.favouriteservice.model.ArticleMessage;

@Service
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	

	public void sendMessage(ArticleMessage message) {

		String jsonMessage = null;
		try {
			jsonMessage = objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(new Date());
		rabbitTemplate.convertAndSend(RobbitMqConfig.ROUTING_KEY, jsonMessage);
		System.out.println("Is listener returned ::: " + rabbitTemplate.isReturnListener());

	}

}
