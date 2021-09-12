package com.buddy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	
// This code helps us to test our database and 
// make sure that all features created in our service layer works
// And You use it for the front end part
//	@Autowired
//	private UsersService usersService;
//	
//	@Autowired
//	private BankService bankService;
//	
//	@Autowired
//	private ContactService contactService;
//	
//	@Autowired
//	private TransactionService transactionService;
//	
//	
//	@Bean
//	CommandLineRunner run() {
//		return args -> {
//			
//			usersService.saveUser(new Users(null, "Hayley", "Dupont", "hayleydupont@gmail.com","1234"));
//			usersService.saveUser(new Users(null, "Clara", "Kata", "clarakata@gmail.com", "1234"));
//			usersService.saveUser(new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234"));
//			
//			bankService.createBankAccount(new BankAccount(null, 120.50, usersService.getUser("hayleydupont@gmail.com")));
//			bankService.createBankAccount(new BankAccount(null, 500.00, usersService.getUser("clarakata@gmail.com")));
//			bankService.createBankAccount(new BankAccount(null, 1500.00, usersService.getUser("smithwesson@gmail.com")));
//
//			contactService.addContact("hayleydupont@gmail.com", "clarakata@gmail.com");
//			contactService.addContact("clarakata@gmail.com", "hayleydupont@gmail.com");
//			contactService.addContact("hayleydupont@gmail.com", "smithwesson@gmail.com");
//			contactService.addContact("smithwesson@gmail.com", "hayleydupont@gmail.com");
//			contactService.addContact("clarakata@gmail.com", "smithwesson@gmail.com");
//			
//			transactionService.makeTransactionWithInputVerification(
//					new Transaction(null
//							, 10.00
//							, usersService.getUser("hayleydupont@gmail.com")
//							, usersService.getUser("clarakata@gmail.com")
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId())
//							, bankService.getBankAccountByUserId(usersService.getUser("clarakata@gmail.com").getId()) 
//							, null
//							,"Restaurant bill share"
//					));
//			
//			transactionService.makeTransactionWithInputVerification(
//					new Transaction(null
//							, 25.00
//							, usersService.getUser("clarakata@gmail.com")
//							, usersService.getUser("hayleydupont@gmail.com")
//							, bankService.getBankAccountByUserId(usersService.getUser("clarakata@gmail.com").getId())
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId()) 
//							, null
//							,"Trip money"
//					));
//			
//			transactionService.makeTransactionWithInputVerification(
//					new Transaction(null
//							, 8.00
//							, usersService.getUser("smithwesson@gmail.com")
//							, usersService.getUser("hayleydupont@gmail.com")
//							, bankService.getBankAccountByUserId(usersService.getUser("smithwesson@gmail.com").getId())
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId()) 
//							, null
//							,"Movie tickets"
//					));
//			
//		};
//	}
}
