package com.stackroute.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	@Query("SELECT p FROM User p WHERE LOWER(p.username) = LOWER(:username) and LOWER(p.password) = LOWER(:password)")
    public User find(@Param("username") String userName,@Param("password") String password);
	
	public User findByUsername(String username);
	
}
