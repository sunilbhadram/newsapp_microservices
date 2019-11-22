package com.stackroute.articleRecommendationService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.articleRecommendationService.model.RecomArticle;





@Repository
public interface RecomArticleRepository extends MongoRepository<RecomArticle, String> {
	
	public List<RecomArticle> findAllByOrderByRecomCountDesc();

}
