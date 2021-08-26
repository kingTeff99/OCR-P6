package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.BankAccount;
import com.buddy.model.Users;
import com.buddy.repository.UserRepo;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {
	
	@Autowired
	private UserRepo userRepo;
	
	@MockBean
	private UsersService usersService;
	
	@MockBean
	private BankService bankService;
	
	@Before
	public void setup() {
		userRepo.save(new Users(null, "John", "Ali", "johnali@gmail.com","1234"));
		usersService.saveUser(new Users(null, "Kunta", "Kinte", "kuntakinte@gmail.com", "1234"));
		usersService.saveUser(new Users(null, "Pierre", "Sankara", "pierresankara@gmail.com", "1234"));
	
		bankService.createBankAccount(new BankAccount(null, 120.50, usersService.getUser("johnali@gmail.com")));
		bankService.createBankAccount(new BankAccount(null, 500.00, usersService.getUser("kuntakinte@gmail.com")));
		bankService.createBankAccount(new BankAccount(null, 1500.00, usersService.getUser("pierresankara@gmail.com")));

	}
	
	@Test
	public void findAnUserWithItsUsername() {
		
		userRepo.save(new Users(null, "John", "Ali", "johnali@gmail.com","1234"));
		
		assertThat(usersService.getUser("johnali@gmail.com")).isNotNull();
		
	}

}
