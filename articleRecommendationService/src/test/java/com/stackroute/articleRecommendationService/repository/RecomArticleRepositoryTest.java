package com.stackroute.articleRecommendationService.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.articleRecommendationService.model.RecomArticle;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecomArticleRepositoryTest {
	
	@Autowired
	private RecomArticleRepository recomArticleRepository;
	private RecomArticle recomArticle;
	
	@Before
    public void setUp() throws Exception {
		recomArticle = new RecomArticle();
		recomArticle.setTitle("title_1");
		recomArticle.setContent("content_1");
		recomArticle.setDescription("desc_1");
		recomArticle.setUrl("url_1");
		recomArticle.setRecomCount(1);
		
	}
	
	@After
	public void tearDown() {
		recomArticleRepository.deleteAll();
	}
	
	@Test
	public void updateRecommendationTest() {
		recomArticleRepository.save(recomArticle);
		RecomArticle article = recomArticleRepository.findById("title_1").get();
		Assert.assertEquals(article.getContent(), recomArticle.getContent());
		
	}
	

}
