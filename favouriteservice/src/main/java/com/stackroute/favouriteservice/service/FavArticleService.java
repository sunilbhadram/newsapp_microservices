package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.model.Article;
import com.stackroute.favouriteservice.model.ArticleMessage;

public interface FavArticleService {

	public boolean addToFavArticle(Article article,String userId);
	
	public boolean removeFavArticle(Article article,String userId);
	
	public List<Article> getAllArticleByUserId(String userId);
	
	public boolean deleteFavourite(String username,String title);
	
	public ArticleMessage createArticleMessage(Article article, String username,String updateType);
}
