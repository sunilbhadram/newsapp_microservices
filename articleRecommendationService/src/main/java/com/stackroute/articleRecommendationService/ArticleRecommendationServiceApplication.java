package com.stackroute.articleRecommendationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.stackroute.articleRecommendationService.config.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.stackroute.articleRecommendationService")
public class ArticleRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleRecommendationServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/recomservice/*");
		return registrationBean;
	}

}

