package com.DemoTest.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DemoTest.demo.Entity.User;
import com.DemoTest.demo.Services.UserService;

@RestController
@RequestMapping("/mockito/test/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<?>savedUser(@RequestBody User user){
		User response=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?>getUser(@PathVariable("userId") int userId){
		User DBuser=userService.getUser(userId);
		if(DBuser==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(DBuser);
	}
	
	

	
	
	
	
	
	
	
	
	

}
