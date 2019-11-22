package com.stackroute.articleRecommendationService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.articleRecommendationService.model.ArticleMessage;
import com.stackroute.articleRecommendationService.model.RecomArticle;
import com.stackroute.articleRecommendationService.repository.RecomArticleRepository;

@Service
public class RecomArticleServiceImpl implements RecomArticleService {

	private RecomArticleRepository articleRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public RecomArticleServiceImpl(RecomArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public void updateRecommendation(String jsonMessage) {

		ArticleMessage articleMesage = null;
		RecomArticle recomArticle = null;
		try {
			articleMesage = this.objectMapper.readValue(jsonMessage, ArticleMessage.class);
			System.out.println("in RecomArticle----"+articleMesage.getTitle());
			System.out.println("in RecomArticle----"+articleMesage.getUsername());
			Optional<RecomArticle> optional =  this.articleRepository.findById(articleMesage.getTitle());
			if(optional.isPresent()){
				recomArticle = optional.get();
				if("ADD".equalsIgnoreCase(articleMesage.getUpdateType())) {
					recomArticle.setRecomCount(recomArticle.getRecomCount()+1);
				}else {
					recomArticle.setRecomCount(recomArticle.getRecomCount()-1);
				}
				
			}else {
				recomArticle = new RecomArticle();
				recomArticle.setTitle(articleMesage.getTitle());
				recomArticle.setContent(articleMesage.getContent());
				recomArticle.setDescription(articleMesage.getDescription());
				recomArticle.setUrl(articleMesage.getUrl());
				recomArticle.setRecomCount(1);
			}
			if(recomArticle.getRecomCount() <=0 ) {
				this.articleRepository.delete(recomArticle);
			}else {
				this.articleRepository.save(recomArticle);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<RecomArticle> getAllRecommendations() {
		List<RecomArticle> list = this.articleRepository.findAllByOrderByRecomCountDesc();
		return list;
	}

}
