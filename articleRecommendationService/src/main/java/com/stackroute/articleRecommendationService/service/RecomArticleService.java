package com.stackroute.articleRecommendationService.service;

import java.util.List;

import com.stackroute.articleRecommendationService.model.RecomArticle;

public interface RecomArticleService {

	public void updateRecommendation(String jsonMessage);
	
	public List<RecomArticle> getAllRecommendations();
	
	
}
