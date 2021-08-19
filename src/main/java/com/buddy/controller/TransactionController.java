package com.buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.dto.ContactDTO;
import com.buddy.dto.FullTransactionDTO;
import com.buddy.dto.TransactionDTO;
import com.buddy.model.Transaction;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
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
  @CrossOrigin(origins = "http://localhost:5500")
  public ContactDTO addContact(@RequestParam String username, @RequestParam String myUsername) {
	  
	  return contactService.addContact(username, myUsername);
		
  }

  /**
   * POST : Make a transaction
   * @param transaction
   * @return
   */
  @CrossOrigin(origins = "http://localhost:5500")
  @PostMapping(value = "/transaction/make")
  public TransactionDTO makeTransaction(@RequestBody Transaction transaction) {
	  
    return transactionService.makeTransaction(transaction);
 
  	
  }
  
  /**
   * GET : get all transactions for an user
   * @param id
   * @return
   */
  @CrossOrigin(origins = "http://localhost:5500")
  @GetMapping(value = "/transaction/{id}")
  public FullTransactionDTO getTransactionById(@PathVariable Long id){
	  
	  Transaction transaction = transactionService.getTransac(id);
	  
	  FullTransactionDTO fullTransactionDTO = new FullTransactionDTO();
   
	  fullTransactionDTO.setId(transaction.getId());
	  
	  fullTransactionDTO.setAmount(transaction.getAmount());
	  
	  fullTransactionDTO.setUserSender(transaction.getUserSenderId().getId());

	  fullTransactionDTO.setUserReceiver(transaction.getUserReceiverId().getId());

	  fullTransactionDTO.setBankSender(transaction.getBankSenderId().getId());

	  fullTransactionDTO.setBankReceiver(transaction.getBankReceiverId().getId());
	  
	  fullTransactionDTO.setDescription(transaction.getDescription());

	  return fullTransactionDTO;

  }
  
}
