package com.buddy.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Contact;
import com.buddy.model.Transaction;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
  @Autowired
  private TransactionService transactionService;
  
  @Autowired
  private ContactService contactService;
  
  @PostMapping("/contact/add")
  public ResponseEntity<Contact> addContact(@PathVariable String username, @PathVariable Long ownUserId) {
	  
	  URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/contact/add")
				.toUriString());
	  
	  return ResponseEntity.created(uri)
				.body(contactService.addContact(username, ownUserId));
	  
  }
	
  @PostMapping("/transaction/make")
  public ResponseEntity<Long> makeTransaction(@RequestBody Transaction transaction) {
	  
	  URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/transaction/make")
				.toUriString());
		
		return ResponseEntity.created(uri)
				.body(transactionService.makeTransaction(transaction));
  	
  }

  @GetMapping("/transaction/{id}")
  public ResponseEntity<List<Transaction>> getTransactionById(@PathVariable Long id){
  	
      transactionService.getAllTransactionsForAnUser(id);
      
      return ResponseEntity.ok().build();
      
  }

}
