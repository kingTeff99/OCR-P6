package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.dto.BankDTO;
import com.buddy.model.BankAccount;
import com.buddy.service.BankService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")

public class BankController {
	
	@Autowired
	private BankService bankService;
	
	/**
	 * POST : Method to create a bank account for a registered user in the database
	 * @param bankAccount
	 * @return Bank account
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
	 * @return bank account
	 */
	@CrossOrigin(origins = "http://localhost:5500")
	@GetMapping("/bankaccount/{userId}")
	public BankDTO getBankAccountByUserId(@PathVariable Long userId){
  	
      return bankService.getBankAccountDTOByUserId(userId);
      
	}

}
