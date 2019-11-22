package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Article;
import com.stackroute.favouriteservice.model.ArticleMessage;
import com.stackroute.favouriteservice.model.UserArticle;
import com.stackroute.favouriteservice.repository.ArticleRepository;

@Service
public class FavArticleServiceImpl implements FavArticleService {

	private ArticleRepository articleRepository;

	@Autowired
	public FavArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public boolean addToFavArticle(Article article, String username) {

		
		try {
			List<Article> articles = getAllArticleByUserId(username);
			if (articles == null || articles.isEmpty()) {
				articles = new ArrayList<>();
			}
			articles.add(article);
			UserArticle userArticle = new UserArticle();
			userArticle.setUsername(username);
			userArticle.setArticles(articles);
			this.articleRepository.save(userArticle);
		
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	public ArticleMessage createArticleMessage(Article article, String username,String updateType) {
		
		ArticleMessage message = new ArticleMessage();
		message.setTitle(article.getTitle());
		message.setContent(article.getContent());
		message.setDescription(article.getDescription());
		message.setUrl(article.getUrl());
		message.setUsername(username);
		message.setUpdateType(updateType);
		return message;
	}

	@Override
	public boolean removeFavArticle(Article article, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Article> getAllArticleByUserId(String userId) {
		UserArticle userArticle = null;
		List<Article> articles = null;
		Optional<UserArticle> optional = this.articleRepository.findById(userId);
		if(optional.isPresent()) {
			userArticle = optional.get();
			articles = userArticle.getArticles();
		}
		return articles;
	}
	
	public boolean deleteFavourite(String username,String title){

		boolean status = false;
		Optional<UserArticle> optional = this.articleRepository.findById(username);
		if(optional.isPresent()) {
			UserArticle userArticle  = optional.get();
			List<Article> articles = userArticle.getArticles();
			articles.removeIf(article -> article.getTitle().equalsIgnoreCase(title));
			if(articles.isEmpty()) {
				System.out.println("deleted");
				this.articleRepository.delete(userArticle);
			}else {
				System.out.println("updated");
				userArticle.setArticles(articles);
				this.articleRepository.save(userArticle);
			}
			status = true;
		}
		return status;
	}

}
