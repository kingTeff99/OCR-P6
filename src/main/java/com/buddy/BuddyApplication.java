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
			usersService.saveUser(new Users(null, "Pierre", "Sankara", "pierresankara@gmail.com", "1234"));
			
			bankService.createBankAccount(new BankAccount(null, 120.50, usersService.getUser("johnali@gmail.com")));
			bankService.createBankAccount(new BankAccount(null, 500.00, usersService.getUser("kuntakinte@gmail.com")));
			bankService.createBankAccount(new BankAccount(null, 1500.00, usersService.getUser("pierresankara@gmail.com")));

			contactService.addContact("johnali@gmail.com", "kuntakinte@gmail.com");
			contactService.addContact("kuntakinte@gmail.com", "johnali@gmail.com");
			contactService.addContact("johnali@gmail.com", "pierresankara@gmail.com");
			contactService.addContact("pierresankara@gmail.com", "kuntakinte@gmail.com");
			contactService.addContact("kuntakinte@gmail.com", "pierresankara@gmail.com");
			
			transactionService.makeTransaction(
					new Transaction(null
							, 10.50
							, usersService.getUser("johnali@gmail.com")
							, usersService.getUser("kuntakinte@gmail.com")
							, bankService.getBankAccountByUserId(usersService.getUser("johnali@gmail.com").getId())
							, bankService.getBankAccountByUserId(usersService.getUser("kuntakinte@gmail.com").getId()) 
							, null
							,"Refund the lunch"
					));
			
			transactionService.makeTransaction(
					new Transaction(null
							, 250.45
							, usersService.getUser("kuntakinte@gmail.com")
							, usersService.getUser("johnali@gmail.com")
							, bankService.getBankAccountByUserId(usersService.getUser("kuntakinte@gmail.com").getId())
							, bankService.getBankAccountByUserId(usersService.getUser("johnali@gmail.com").getId()) 
							, null
							,"Buy an playstation"
					));
			
			transactionService.makeTransaction(
					new Transaction(null
							, 200.00
							, usersService.getUser("pierresankara@gmail.com")
							, usersService.getUser("kuntakinte@gmail.com")
							, bankService.getBankAccountByUserId(usersService.getUser("pierresankara@gmail.com").getId())
							, bankService.getBankAccountByUserId(usersService.getUser("kuntakinte@gmail.com").getId()) 
							, null
							,"Road trip"
					));
			
		};
	}
}
