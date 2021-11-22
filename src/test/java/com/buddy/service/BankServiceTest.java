package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.BankAccount;
import com.buddy.model.Users;
import com.buddy.repository.BankRepository;
import com.buddy.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class BankServiceTest {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private BankRepository bankRepository;

	 @Test
	 public void findBankAccountTest() {
		 
		 Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com", "1234");
		 userRepository.save(user1);
		 
		 BankAccount bankAccount1 = new BankAccount(1L, 500.0, user1);
		 bankRepository.save(bankAccount1);
		 
		 Optional<BankAccount> optBank = Optional.ofNullable(bankAccount1);
		 
		 Optional<BankAccount> bank1 = bankRepository.findById(1L);
		 
		 assertThat(bank1).isEqualTo(optBank);
		 
	 }
	 
}
