package com.buddy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.buddy.dto.TransactionDTO;
import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.model.Users;
import com.buddy.repository.TransactionRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = TransactionController.class)
@RunWith(SpringRunner.class)
public class TransactionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	TransactionService transactionService;
	
	@MockBean
	ContactService contactService;
	
	@MockBean
	BankService bankService;
	
	@MockBean
	UsersService usersService;
	
	@MockBean
	TransactionRepository transactionRepo;
	
	
	@Test
	public void makeTransactionTest() throws Exception {
		
		//GIVEN
		Users user1 = new Users(1L, "John", "Ali", "johnali@gmail.com", "1234");
		usersService.saveUser(user1);
		
		Users user2 = new Users(2L, "Smith", "Wesson", "smithwesson@gmail.com", "1234");
		usersService.saveUser(user2);
			
		BankAccount bankAccount1 = new BankAccount(1L, 500.0, user1);
		bankService.createBankAccount(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount(2L, 400.0, user2);
		bankService.createBankAccount(bankAccount2);
		
		Transaction transaction1 = new Transaction(1L, 100.0, user1, user2, bankAccount1, bankAccount2, 5.0 , "buy a ice cream");
		
		TransactionDTO transaction = transactionService.makeTransactionWithInputVerification(transaction1);
		
		TransactionDTO transactionDTO = TransactionDTO.builder()
				.id(transaction1.getId())
				.amount(transaction1.getAmount())
				.description(transaction1.getDescription()).build();
			
		//WHEN
		when(transactionService.makeTransactionWithInputVerification(transaction1)).thenReturn(transactionDTO);

		mockMvc.perform(post("/transaction/make")
			   .accept(MediaType.APPLICATION_JSON)
		       .contentType(MediaType.APPLICATION_JSON))
//		       .content("{\"id\":2,\"balance\":500.0,\"userId\":1}"))
//	       	   .content(objectMapper.writeValueAsString(transaction1)))
	       	   .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id", is(transaction1.getId())))
		       .andExpect(jsonPath("$.amount", is(transaction1.getAmount())))
		       .andExpect(jsonPath("$.description", is(transaction1.getDescription())));
				    
		verify(bankService, times(1)).getBankAccountDTOByUserId(1L);
			
	}

}
