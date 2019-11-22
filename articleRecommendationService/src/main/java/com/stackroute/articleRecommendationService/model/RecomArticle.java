package com.stackroute.articleRecommendationService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RecomArticle {
	
	@Id
	private String title;
	private String description;
	private String content;
	private String url;
	private int recomCount;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getRecomCount() {
		return recomCount;
	}
	public void setRecomCount(int recomCount) {
		this.recomCount = recomCount;
	}
	

}
