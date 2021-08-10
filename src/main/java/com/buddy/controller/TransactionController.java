package com.buddy.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Contact;
import com.buddy.model.Transaction;
import com.buddy.model.Users;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
  @Autowired
  private TransactionService transactionService;
  
  @Autowired
  private ContactService contactService;
  
  /**
   * POST : Add a contact in order to make a transaction
   * @param username
   * @param ownUserId
   * @return
   */
  @PostMapping("/contact/add")
  public ResponseEntity<Contact> addContact(@RequestParam("username") String username, @RequestParam("myUsername") String myUsername) {
	  
		return new ResponseEntity<Contact>(contactService.addContact(username, myUsername), HttpStatus.CREATED);
		
  }

  /**
   * POST : Make a transaction
   * @param transaction
   * @return
   */
  @PostMapping("/transaction/make")
  public ResponseEntity<?> makeTransaction(@RequestBody Transaction transaction) {
	  
//	    Transaction transactionId = transactionService.makeTransaction(transaction);
	  
	  URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/transaction/make")
				.toUriString());
		
//		return ResponseEntity.created(uri)
//				.body(transactionService.getAllTransactionsForAnUser(transactionId));
	  
		return ResponseEntity.created(uri)
		.body(transactionService.makeTransaction(transaction));
  	
  }
  
  /**
   * GET : get all transactions for an user
   * @param id
   * @return
   */
  @GetMapping("/transaction/{id}")
  public List<Transaction> getTransactionById(@RequestBody Users user ){
  	
     return transactionService.getAllTransactionsForAnUser(user);
      
  }

}
