package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/userservice/")
public class UserController {

	static final long EXPIRATION_TIME = 600000;
	
	Map<String,String> map = new HashMap<>();
	private UserService userService;
	private ResponseEntity<?> responseEntity;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("register")
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> createUser(@RequestBody User user) throws UserAlreadyExistsException {
		try {
			user.setUserCreated(new Date());
			User createdUser = this.userService.registerUser(user);
			responseEntity = new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		}

		catch (UserAlreadyExistsException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Error !! Try after sometime or contact Admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@PostMapping("login")
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> validateUser(@RequestBody User user)
			throws UserAlreadyExistsException {
		
		System.out.println("#####username"+user.getUsername());
		System.out.println("#####password"+user.getPassword());
		String jwtToken = "";
		try {
			jwtToken = 	generateJwtToken(user.getUsername(),user.getPassword());
			System.out.println(jwtToken);
			map.clear();
			map.put("message", "User Successfully Logged In");
			map.put("token", jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
				map.clear();
				map.put("message", e.getMessage());
				map.put("token", null);
				responseEntity = new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED); 
				System.out.println(responseEntity);
				return responseEntity;
			}
			
			return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	private String generateJwtToken(String username, String password) throws Exception {
		if(username == null || password == null) {
			throw new ServletException("Please provide proper username and password");
		}
		
		User user = this.userService.validateUser(username, password);
		if(user == null || user.getUserid() == 0) {
			throw new ServletException("Invalid Credentials");
		}
		
	   String jwtToken = Jwts.builder()
		    .setSubject(user.getUsername()+"#"+user.getFullname())
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
		    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		return jwtToken;
	}
}
