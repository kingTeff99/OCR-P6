package com.buddy.controller;

import java.net.URI;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.BankAccount;
import com.buddy.service.BankService;

@RestController
@RequestMapping("/api")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	/**
	 * POST : Method to create a bank account for a registered user in the database
	 * @param bankAccount
	 * @return
	 */
	@PostMapping("/bankaccount/create")
	public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/bankaccount/create")
				.toUriString());
		
		return ResponseEntity.created(uri)
				.body(bankService.createBankAccount(bankAccount));

	}
	
	/**
	 * GET : Get a bank account by the id
	 * @param userId
	 * @return
	 */
//	@GetMapping("/bankaccount/{userId}")
	@RequestMapping(value = "/bankaccount/{userId}", method = RequestMethod.GET)
	public BankAccount getBankAccountByUserId(@PathParam(value = "userId") Long userId){
  	
      return bankService.getBankAccountByUserId(userId);
      
	}

}
