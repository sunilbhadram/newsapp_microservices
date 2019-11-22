package com.stackroute.userservice.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAuthenticationRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("in Before#################");
		user = new User();
		user.setUsername("sunil");
		user.setPassword("password");
		user.setFullname("Sunil Bhadram");
		user.setEmail("sunilkumar@gmail.com");

		
	}
	
	@After
    public void tearDown() throws Exception {
		System.out.println("in After#################");
        this.userRepository.deleteAll();
		
    }
	
	@Test
    public void testRegisterUserSuccess() throws Exception{
		this.userRepository.save(user);
        User object = this.userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(user.getFullname(), object.getFullname());
        this.userRepository.delete(object);
    }
	
	
	@Test
    public void testLoginUserSuccess() throws Exception{
		this.userRepository.save(user);
        User object = this.userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(user.getPassword(), object.getPassword());
        this.userRepository.delete(object);
    }

}
