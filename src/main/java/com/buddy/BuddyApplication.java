package com.buddy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.model.Users;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;


@SpringBootApplication
public class BuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyApplication.class, args);
	}
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run() {
		return args -> {
			
			usersService.saveUser(new Users(null, "John", "Ali", "johnali@gmail.com","1234"));
			usersService.saveUser(new Users(null, "Kunta", "Kinte", "kuntakinte@gmail.com", "1234"));
			
			bankService.createBankAccount(new BankAccount(null, 120.50, (long) 1));
			bankService.createBankAccount(new BankAccount(null, 500.00, (long) 2));
			
			contactService.addContact("johnali@gmail.com", (long) 2);
			contactService.addContact("kuntakinte@gmail.com", (long) 1);
			
			transactionService.makeTransaction(
				new Transaction(null
						, 10.0
						, usersService.getUser("johnali@gmail.com").getId()
						, (long) 2 
						, bankService
						.getBankIdByUserId(
								usersService.getUser("johnali@gmail.com").getId())
						, bankService.getBankIdByUserId((long) 2) 
						, null
						,"Refund the meal"
				));
			
		};
	}
}
