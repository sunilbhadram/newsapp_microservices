package com.stackroute.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.stackroute.userservice.config.CorsFilter;
import com.stackroute.userservice.config.JwtFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.stackroute.userservice")
public class UserServiceApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/v1/*");
		return registrationBean;
	}
	
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/userservice/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

