package com.stackroute.userservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean{

	@Override
		protected void initFilterBean() throws ServletException {
			// TODO Auto-generated method stub
		System.out.println("************Filter Initialized");
		}
	
//	To validate the token which is sent as part of Http Request authorization header
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;

		
		//Authorization  Bearer "token"
		String authHeader = httpReq.getHeader("authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			throw new ServletException("Missing or Invalid Authorization Header");
		}
		String token = authHeader.substring(7);
		
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		
		httpReq.setAttribute("claims", claims);
		chain.doFilter(request, response);
	}

}
