package com.buddy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.buddy.dto.BankDTO;
import com.buddy.model.BankAccount;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@RunWith(SpringRunner.class)
public class BankControllerTest {
	
	@Autowired
 	private MockMvc mockMvc;
	
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
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void getBankAccountByUserIdTest() throws Exception {
		
		//Arrange
		Users user2 = new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234");
		usersService.saveUser(user2);
		
		BankAccount bankAccount1 = new BankAccount(1L, 500.0, user2);
		
		BankDTO bankDTO = new BankDTO(bankAccount1.getId()
				, bankAccount1.getBalance()
				, bankAccount1.getUserId().getId()
				, bankAccount1.getUserId().getUsername());
		
	    //Act
	    when(bankService.getBankAccountDTOByUserId(1L)).thenReturn(bankDTO);

	    mockMvc.perform(get("/bank-account/{userId}", 1L)
			       .contentType(MediaType.APPLICATION_JSON))
			       .andExpect(status().is2xxSuccessful())
			       .andExpect(jsonPath("$.id", is(1)))
			       .andExpect(jsonPath("$.balance", is(bankAccount1.getBalance())))
			       .andExpect(jsonPath("$.userId", is(bankAccount1.getUserId().getId())))
			       .andExpect(jsonPath("$.username", is(bankAccount1.getUserId().getUsername())));
		//Assert    
	    verify(bankService, times(1)).getBankAccountDTOByUserId(1L);
		
	}
	
	@Test
	public void createBankAccountTest() throws Exception {
		
		//Arrange
		Users user1 = new Users(null, "Jojo", "affreux", "jojoaffreux@gmail.com", "1234");
		usersService.saveUser(user1);
		
		BankDTO bankAccount1 = new BankDTO(2L, 500.0, 2L, "jojoaffreux@gmail.com");
		bankService.createBankAccount(bankAccount1);
		
		//Act
	    mockMvc.perform(post("/bank-account/create")
			       .contentType(MediaType.APPLICATION_JSON)
    	   		   .content(objectMapper.writeValueAsString(bankAccount1)))
			       .andExpect(status().isCreated());
			    
		
	}

}
