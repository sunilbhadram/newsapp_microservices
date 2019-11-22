package com.stackroute.favouriteservice.config;

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
		System.out.println("%%%%%%%%%%%%%%%% in cors filter");
		HttpServletRequest requestToUse = (HttpServletRequest)request;
		HttpServletResponse responseToUse = (HttpServletResponse)response;


		//System.out.println(requestToUse.getHeader("content-type"));
		//System.out.println(authHeader);
		//if(authHeader == null || !authHeader.startsWith("Bearer")) {
		//	throw new ServletException("Missing or Invalid Authorization Header");
		//}
		//String token = authHeader.substring(7);
		//System.out.println(authHeader);
		/*Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		requestToUse.setAttribute("claims", claims);*/

		responseToUse.setHeader("Access-Control-Allow-Origin", requestToUse.getHeader("Origin"));
		responseToUse.setHeader("Access-Control-Allow-Headers", "authorization,content-type");
		chain.doFilter(requestToUse, responseToUse);

	}

}
