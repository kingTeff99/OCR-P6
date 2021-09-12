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
	  //TODO-Guillaume: aucune vérification ici ?
	  
	  return contactService.addContact(username, myUsername);
		
  }

  /**
   * POST : Make a transaction
   * @param transaction
   * @return
   */
  @PostMapping(value = "/transaction/make")
  public TransactionDTO makeTransaction(@RequestBody Transaction transaction) {
	  
	  //TODO-Guillaume: aucune vérification ici ?
	  //TODO-Guillaume: j'ai vu que la méthode makeTransaction vérifiait bien les choses, mais attention le nom de la méthode ne le stipule pas
	  //TODO-Guillaume: je me suis fais avoir au début, avant de cliquer sur la méthode je pensais qu'elle était exécuté sans vérifications
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
	  
	  //TODO-Guillaume: vérification du NotNull ? qui nous dit que l'id donné est bien existant en db ?
	  
	  //TODO-Guillaume: utilisation du @Builder Lombok sera préférable 
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
