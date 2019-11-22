package com.stackroute.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {

		User userObj = null;
		try {
			if(checkIfUserExists(user.getUsername())) {
				throw new UserAlreadyExistsException("username \""+user.getUsername()+ "\" already exists , "
						+ "please enter something else");
			}else {
				userObj = this.userRepository.save(user);
			}
			
		} catch (Exception e) {
			throw e;
		}

		return userObj;
	}

	private boolean checkIfUserExists(String userName) {

		User user = this.userRepository.findByUsername(userName);
		if (user != null && userName.equalsIgnoreCase(user.getUsername())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public User validateUser(String userName, String password) throws UserNotFoundException {

		User validUser = this.userRepository.find(userName, password);

		return validUser;
	}

	@Override
	public User getUserById(String userId) throws UserNotFoundException {

		User user = null;
		return null;
	}

}
