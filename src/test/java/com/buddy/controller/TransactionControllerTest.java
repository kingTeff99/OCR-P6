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

import com.buddy.dto.BankDTO;
import com.buddy.dto.FullTransactionDTO;
import com.buddy.dto.TransactionDTO;
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

//		TransactionDTO transaction = transactionService.makeTransactionWithInputVerification(transaction1);
		
		TransactionDTO transactionDTO = TransactionDTO.builder()
				.id(transaction1.getId())
				.amount(transaction1.getAmount())
				.description(transaction1.getDescription()).build();
//			
//		//WHEN
//		when(transactionService.makeTransactionWithInputVerification(transaction1)).thenReturn(transactionDTO);

		mockMvc.perform(post("/transaction/make")
//			   .accept(MediaType.APPLICATION_JSON)
		       .contentType(MediaType.APPLICATION_JSON)
//		       .content("{\"id\":2,\"balance\":500.0,\"userId\":1}"))
	       	   .content(objectMapper.writeValueAsString(transaction1)))
//	       	   .andDo(print())
		       .andExpect(status().isOk());
//		       .andExpect(jsonPath("$.id", is(transaction1.getId())))
//		       .andExpect(jsonPath("$.amount", is(transaction1.getAmount())))
//		       .andExpect(jsonPath("$.description", is(transaction1.getDescription())));
				    
//		verify(bankService, times(1)).getBankAccountDTOByUserId(1L);
			
	}

}
