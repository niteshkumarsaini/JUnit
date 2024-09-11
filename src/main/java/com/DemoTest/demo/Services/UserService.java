package com.DemoTest.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DemoTest.demo.UserRepository;
import com.DemoTest.demo.Entity.User;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(int userId) {
		try {
		User user=userRepository.findById(userId).get();
		return user;
		}catch(Exception e) {
			e.printStackTrace();
	
		}
return null;	
		
	}
	public User saveUser(User user) {
		userRepository.save(user);
		System.out.println("User saved Successfully..");
		return user;
	}

	
	
	
	
}
