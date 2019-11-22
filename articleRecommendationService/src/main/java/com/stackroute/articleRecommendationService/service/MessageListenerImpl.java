package com.stackroute.articleRecommendationService.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerImpl implements MessageListener {

	@Autowired
	private RecomArticleService articleService;
	
	@Override
	public void onMessage(String message) {
		System.out.println(new Date());
		//try {
		//	Thread.sleep(5000);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}
		System.out.println("Message Received:" + message);
		this.articleService.updateRecommendation(message);
		System.out.println(new Date());
		
	}

}
