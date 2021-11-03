package com.buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  @PostMapping(value = "/transaction/make", produces="application/json")
  public TransactionDTO makeTransaction(@RequestBody FullTransactionDTO transaction) {
	  
	  System.out.println(transaction);

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
	  
	  return FullTransactionDTO.builder()
			  .id(transaction.getId())
			  .amount(transaction.getAmount())
			  .userSenderId(transaction.getBankSenderId().getUserId().getId())
			  .userReceiverId(transaction.getBankReceiverId().getUserId().getId())
			  .bankSenderId(transaction.getBankSenderId().getUserId().getId())
			  .bankReceiverId(transaction.getBankReceiverId().getUserId().getId())
			  .fees(transaction.getFees())
			  .description(transaction.getDescription()).build();

  }
  
}
