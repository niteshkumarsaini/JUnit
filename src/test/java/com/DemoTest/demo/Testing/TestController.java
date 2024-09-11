package com.DemoTest.demo.Testing;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.DemoTest.demo.Controllers.UserController;
import com.DemoTest.demo.Entity.User;
import com.DemoTest.demo.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class TestController {
	
	
	   @Autowired
	    private MockMvc mockMvc;
	   
	   @MockBean
	   private UserService userService;
	   
	   @Autowired
	   private ObjectMapper objectMapper;
	   
	   @Test
	 public void testSavedUser() throws JsonProcessingException, Exception {
		 
		 User user=new User("niteshsaini1296@gmail.com","This is Mockito Test");
		 
		 //specify behaviour
		 
		 when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
		 
		 //sending request to endpoint
		 
		 
		 mockMvc.perform(post("/mockito/test/v1/save").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andExpect(status().isCreated())
		 .andExpect(jsonPath("$.username", is("niteshsaini1296@gmail.com")))
		 .andExpect(jsonPath("$.description", is("This is Mockito Test")));
		 ;
			  
	 }
	   
	   //when the user is in the db
	   
	   
	 @Test
	 public void testFetchUser() throws Exception {
		 
		//create a user
		 
		 
		 User user =new User(1,"niteshsaini1296@gmail.com","This is Mockito Test");
		 when(userService.getUser(1)).thenReturn(user);
		 
		 //sending request
		 String expectedJson = objectMapper.writeValueAsString(user);
		 
		 mockMvc.perform(get("/mockito/test/v1/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isFound()).andExpect(content().json(expectedJson))
		 ;
		 
		 
	 }
	   
	
	 
	//if the user is not in the db
	 
	 @Test
	 public void testFetchUserifnotinDB() throws Exception {

	
		 when(userService.getUser(1)).thenReturn(null);
		 

		 //sending request
		 String expectedJson = objectMapper.writeValueAsString(null);
		 
		mockMvc.perform(get("/mockito/test/v1/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		 
		 
	 }

	 
	
	
	
	

}
