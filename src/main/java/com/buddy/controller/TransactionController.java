package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.dto.ContactDTO;
import com.buddy.dto.FullTransactionDTO;
import com.buddy.dto.TransactionDTO;
import com.buddy.model.Transaction;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;

@RestController
public class TransactionController {
	
  @Autowired
  private TransactionService transactionService;
  
  @Autowired
  private ContactService contactService;
  
  /**
   * POST : Add a contact in order to make a transaction
   * @param username
   * @param ownUserId
   * @return contact
   */
  @PostMapping(value = "/contact/add")
  public ContactDTO addContact(@RequestParam String username, @RequestParam String myUsername) {
	  
	  return contactService.addContact(username, myUsername);
		
  }

  /**
   * POST : Make a transaction
   * @param transaction
   * @return
   */
  @RequestMapping(value = "/transaction/make", method = RequestMethod.POST , produces="application/json", consumes="application/json")
  public TransactionDTO makeTransaction(@RequestBody Transaction transaction) {
	  
//	  URI uri = URI.create(ServletUriComponentsBuilder
//				.fromCurrentContextPath()
//				.path("/transaction/make")
//				.toUriString());
//	  
//	  return ResponseEntity.created(uri).body(transactionService.makeTransactionWithInputVerification(transaction));
	  
	  return transactionService.makeTransactionWithInputVerification(transaction);
 
  }
  
  /**
   * GET : get all transactions for an user
   * @param id
   * @return
   */
  @GetMapping(value = "/transaction/{id}")
  public FullTransactionDTO getTransactionById(@PathVariable Long id){
	  
	  if(id == null) {
		  return null;
	  }
	  
	  Transaction transaction = transactionService.getTransac(id);
	  
	  FullTransactionDTO fullTransactionDTO = FullTransactionDTO.builder()
			  .id(transaction.getId())
			  .amount(transaction.getAmount())
			  .userSender(transaction.getBankSenderId().getUserId().getId())
			  .userReceiver(transaction.getBankReceiverId().getUserId().getId())
			  .bankSender(transaction.getBankSenderId().getUserId().getId())
			  .bankReceiver(transaction.getBankReceiverId().getUserId().getId())
			  .description(transaction.getDescription()).build();

	  return fullTransactionDTO;

  }
  
}
