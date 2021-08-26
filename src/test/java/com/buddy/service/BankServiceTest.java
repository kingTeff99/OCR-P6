package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.buddy.model.Users;
import com.buddy.repository.BankRepo;
import com.buddy.repository.UserRepo;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class BankServiceTest {
	
	 @BeforeEach
	 public void setUp() {
		 
	 }

	 @Autowired
	 private UserRepo userRepo;
	 
	 @Autowired
	 private BankRepo bankRepo;

//	 @Autowired
//	 private TestEntityManager entityManager;
	 
	 @Test
	 public void findBankAccountTest() {
		 
		 Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com", "1234");
		 userRepo.save(user1);
		 
		 BankAccount bankAccount1 = new BankAccount(1L, 500.0, user1);
		 bankRepo.save(bankAccount1);
		 
		 Optional<BankAccount> optBank = Optional.ofNullable(bankAccount1);
		 
		 Optional<BankAccount> bank1 = bankRepo.findById(1L);
		 
		 assertThat(bank1).isEqualTo(optBank);
		 
	 }
	 
//	 @Test
//	 public void findBankAccountByUserIdTest() {
//		 
//		 Users user2 = new Users(null, "Paul", "Wall", "paulwall@gmail.com", "1234");
//		 entityManager.persist(user2);
////		 userRepo.save(user2);
//		 
//		 BankAccount bankAccount2 = new BankAccount(null, 500.0, user2);
//		 entityManager.persist(bankAccount2);
////		 bankRepo.save(bankAccount2);
//		 
//		 List<BankAccount> bankList = Arrays.asList(bankAccount2);
//		 
//		 List<BankAccount> findBankList = bankRepo.findByUserId(user2);
//		 
//		 assertThat(findBankList).isEqualTo(bankList);
//		 
//	 }
	

}
