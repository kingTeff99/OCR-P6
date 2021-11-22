package com.buddy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
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
import com.buddy.dto.FullTransactionDTO;
import com.buddy.dto.TransactionDTO;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@RunWith(SpringRunner.class)
public class TransactionControllerTest {
	
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
	public void makeTransactionTest() throws Exception {
		
		//Arrange
		Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com", "1234");
		usersService.saveUser(user1);
		
		Users user2 = new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234");
		usersService.saveUser(user2);
			
		BankDTO bankAccount1 = new BankDTO(1L, 500.0,1L, "johnali@gmail.com" );
		bankService.createBankAccount(bankAccount1);
		
		BankDTO bankAccount2 = new BankDTO(2L, 400.0, 2L,"smithwesson@gmail.com");
		bankService.createBankAccount(bankAccount2);
		
		FullTransactionDTO transaction1 = new FullTransactionDTO(1L, 100.0, 1L, 2L, 1L, 2L, 5.0 , "buy a ice cream");
		transactionService.makeTransactionWithInputVerification(transaction1);

		TransactionDTO transactionDTO = TransactionDTO.builder()
				.id(transaction1.getId())
				.amount(transaction1.getAmount())
				.description(transaction1.getDescription()).build();
			
		//Act
		when(transactionService.makeTransactionWithInputVerification(transaction1)).thenReturn(transactionDTO);

		mockMvc.perform(post("/transaction/make")
		       .contentType(MediaType.APPLICATION_JSON)
	       	   .content(objectMapper.writeValueAsString(transaction1)))
		       .andExpect(status().is2xxSuccessful())
		       .andExpect(jsonPath("$.id", is(1)))
		       .andExpect(jsonPath("$.amount", is(transaction1.getAmount())))
		       .andExpect(jsonPath("$.description", is(transaction1.getDescription())));
				    
	}

}
