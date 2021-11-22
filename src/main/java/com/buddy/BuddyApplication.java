package com.buddy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.buddy.dto.BankDTO;
import com.buddy.dto.ContactFormulaDTO;
import com.buddy.dto.FullTransactionDTO;
import com.buddy.model.Users;
import com.buddy.repository.BankRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;

@SpringBootApplication(scanBasePackages={"com.buddy.service","com.buddy" })
public class BuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyApplication.class, args);
	}
	
// This code helps us to test our database and 
// make sure that all features created in our service layer works
// And we use it for the front-end part
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
//	@Bean
//	CommandLineRunner run() {
//		return args -> {
//			
//			usersService.saveUser(new Users(null, "Hayley", "Dupont", "hayleydupont@gmail.com","1234"));
//			usersService.saveUser(new Users(null, "Clara", "Kata", "clarakata@gmail.com", "1234"));
//			usersService.saveUser(new Users(null, "Smith", "Wesson", "smithwesson@gmail.com", "1234"));
//			
//			bankService.createBankAccount(new BankDTO(null, 120.50, 1L, "hayleydupont@gmail.com"));
//			bankService.createBankAccount(new BankDTO(null, 500.00, 2L, "clarakata@gmail.com"));
//			bankService.createBankAccount(new BankDTO(null, 1500.00, 3L, "smithwesson@gmail.com"));
//
//			contactService.addContact(new ContactFormulaDTO("hayleydupont@gmail.com", "clarakata@gmail.com"));
//			contactService.addContact(new ContactFormulaDTO("clarakata@gmail.com", "hayleydupont@gmail.com"));
//			contactService.addContact(new ContactFormulaDTO("hayleydupont@gmail.com", "smithwesson@gmail.com"));
//			contactService.addContact(new ContactFormulaDTO("smithwesson@gmail.com", "hayleydupont@gmail.com"));
//			contactService.addContact(new ContactFormulaDTO("clarakata@gmail.com", "smithwesson@gmail.com"));
//			
//			transactionService.makeTransactionWithInputVerification(
//					new FullTransactionDTO(1L
//							, 10.00
//							, usersService.getUser("hayleydupont@gmail.com").getId()
//							, usersService.getUser("clarakata@gmail.com").getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId()).getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("clarakata@gmail.com").getId()).getId() 
//							, null
//							,"Restaurant bill share"
//					));
//			
//			transactionService.makeTransactionWithInputVerification(
//					new FullTransactionDTO(2L
//							, 25.00
//							, usersService.getUser("clarakata@gmail.com").getId()
//							, usersService.getUser("hayleydupont@gmail.com").getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("clarakata@gmail.com").getId()).getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId()).getId() 
//							, null
//							,"Trip money"
//					));
//			
//			transactionService.makeTransactionWithInputVerification(
//					new FullTransactionDTO(3L
//							, 8.00
//							, usersService.getUser("smithwesson@gmail.com").getId()
//							, usersService.getUser("hayleydupont@gmail.com").getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("smithwesson@gmail.com").getId()).getId()
//							, bankService.getBankAccountByUserId(usersService.getUser("hayleydupont@gmail.com").getId()).getId() 
//							, null
//							,"Movie tickets"
//					));
//			
//		};
//	}
}
