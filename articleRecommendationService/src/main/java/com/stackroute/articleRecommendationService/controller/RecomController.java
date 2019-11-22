package com.stackroute.articleRecommendationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.articleRecommendationService.model.RecomArticle;
import com.stackroute.articleRecommendationService.service.RecomArticleService;

@RestController
@RequestMapping("recomservice/")
public class RecomController {

	private ResponseEntity<?> responseEntity;
	private RecomArticleService articleService;

	@Autowired
	public RecomController(RecomArticleService articleService) {
		this.articleService = articleService;

	}



	@GetMapping("listrecom")
	public ResponseEntity<?> listFavourites() throws Exception {
		try {

			List<RecomArticle> articles = this.articleService.getAllRecommendations();
			if (articles != null && articles.size() > 0) {
				responseEntity = new ResponseEntity<>(articles,HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("No Recom Items.",HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Error !! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	
}
