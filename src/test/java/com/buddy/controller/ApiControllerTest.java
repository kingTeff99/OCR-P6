package com.buddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.buddy.dto.RegisterFormDTO;
import com.buddy.repository.ContactRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@RunWith(SpringRunner.class)
public class ApiControllerTest {
	
		@Autowired
	    private MockMvc mockMvc;
		
		@Autowired
		private ObjectMapper objectMapper;
		
		@MockBean
		private UsersService usersService;
		
		@MockBean 
		private BankService bankService;
		
		@MockBean 
		private ContactService contactService;
		
		@MockBean 
		private ContactRepository contactRepository;
		
		@MockBean 
		private TransactionService transactionService;
		
	    @Test
	    public void loginWithCorrectCredentialsButIsUnauthorized() throws Exception {
	        mockMvc.perform(post("/api/login")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("username", "johnali@gmail.com")
	                .param("password", "1234"))
	                .andExpect(status().isUnauthorized());
	    }
	    
	    @Test
	    public void registerWithCorrectCredentials() throws Exception {
	    	
	    	RegisterFormDTO registerForm = new RegisterFormDTO("Cristiano", "Ronaldo","cr7@gmail.com", "123456", "123456");
	    	
	        this.mockMvc.perform(post("/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(registerForm)))
	                .andExpect(status().isCreated());
	    }

}
