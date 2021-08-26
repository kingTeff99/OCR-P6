package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.model.Users;
import com.buddy.repository.BankRepo;
import com.buddy.repository.TransactionRepo;
import com.buddy.repository.UserRepo;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BankRepo bankRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@BeforeEach
	public void setup() {

	}
	
	@Test
	public void findATransactionByIdTest() {
		//GIVEN
		Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com", "1234");
		userRepo.save(user1);
		
		Users user2 = new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234");
		userRepo.save(user2);
			
		BankAccount bankAccount1 = new BankAccount(1L, 500.0, user1);
		bankRepo.save(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount(2L, 400.0, user2);
		bankRepo.save(bankAccount2);
		
		Optional<Transaction> optTransaction = Optional.ofNullable(new Transaction(1L, 100.0, user1, user2, bankAccount1, bankAccount2, 5.0 , "buy a ice cream"));
		transactionRepo.save(optTransaction.get());
		
		//THEN
		assertThat(transactionRepo.findById(optTransaction.get().getId())).isEqualTo(optTransaction);				
		
	}
	
	@Test
	public void findATransactionByReceiverIdTest() {
		//GIVEN
		Users user1 = new Users(null, "Anna", "Anna", "johnali@gmail.com", "1234");
		userRepo.save(user1);
		
		Users user2 = new Users(null, "Frank", "Wesson", "FrankWesson@gmail.com", "1234");
		userRepo.save(user2);
			
		BankAccount bankAccount1 = new BankAccount(null, 600.0, user1);
		bankRepo.save(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount(null, 500.0, user2);
		bankRepo.save(bankAccount2);
		
		Transaction transaction1 = new Transaction(null, 100.0, user1, user2
				, bankAccount1, bankAccount2, 5.0 , "buy a soda");
		transactionRepo.save(transaction1);
		
		List<Transaction> transactionList = Arrays.asList(transaction1);
		
		
		//THEN
		assertEquals(transactionList, transactionRepo.findByUserReceiverId(user2));
		
	}
	
	@Test
	public void findATransactionBySenderIdTest() {
		//GIVEN
		Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com", "1234");
		userRepo.save(user1);
		
		Users user2 = new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234");
		userRepo.save(user2);
			
		BankAccount bankAccount1 = new BankAccount(null, 500.0, user1);
		bankRepo.save(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount(null, 400.0, user2);
		bankRepo.save(bankAccount2);
		
		Transaction transaction1 = new Transaction(null, 100.0, user1, user2
				, bankAccount1, bankAccount2, 5.0 , "buy a ice cream");
		transactionRepo.save(transaction1);
		
		List<Transaction> transactionList = Arrays.asList(transaction1);
		
		
		//THEN
		assertEquals(transactionList, transactionRepo.findByUserSenderId(user1));
		
	}

}
