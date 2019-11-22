package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {
	
	public User registerUser(User user) throws UserAlreadyExistsException;

	public User getUserById(String userId) throws UserNotFoundException;
	
	public User validateUser(String userName, String password) throws UserNotFoundException;
}
