package com.stackroute.favouriteservice.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * Annotate this method with @Bean . This method will return an Object of Docket.
	 * This method will implement logic for swagger
	 */
	public static final Contact DEFAULT_CONTACT = new Contact
			("Sunil Bhadram","https://www.linkedin.com/in/sunil-bhadram","sunil.bhadram@in.ibm.com");
	
	public static final ApiInfo API_INFO = new ApiInfo(
			"User Service", "News App SBA", "1.0", "urn:tos", DEFAULT_CONTACT.getName(), "Apache 2.0", 
			"https://www.apache.org/licenses/LICENSE-2.0.html");
			
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));
	
    
	@Bean
    public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO).
				produces(DEFAULT_PRODUCES_AND_CONSUMES).
				consumes(DEFAULT_PRODUCES_AND_CONSUMES);
				
    }
}
