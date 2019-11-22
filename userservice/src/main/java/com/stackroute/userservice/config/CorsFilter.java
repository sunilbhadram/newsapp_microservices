package com.stackroute.userservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CorsFilter extends GenericFilterBean {

	
	@Override
	protected void initFilterBean() throws ServletException {
		System.out.println("$$$$$$$$$$$$$ Filter Initialized");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest requestToUse = (HttpServletRequest)request;
		HttpServletResponse responseToUse = (HttpServletResponse)response;
		responseToUse.setHeader("Access-Control-Allow-Origin", requestToUse.getHeader("Origin"));
		responseToUse.setHeader("Access-Control-Allow-Headers", "content-type");
		chain.doFilter(requestToUse, responseToUse);

	}

}
