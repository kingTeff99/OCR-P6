package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.dto.BankDTO;
import com.buddy.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	/**
	 * POST : Method to create a bank account for a registered user in the database
	 * @param bankAccount
	 * @return Bank account
	 */
	@PostMapping(value = "/bank-account/create", consumes="application/json", produces="application/json")
	public ResponseEntity<BankDTO> createBankAccount(@RequestBody BankDTO bankAccount ) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/bank-account/create")
				.toUriString());
		
		if(bankAccount == null) {
			return null;
		}
		
		return ResponseEntity.created(uri)
				.body(bankService.createBankAccount(bankAccount));

	}
	
	/**
	 * GET : Get a bank account by the id
	 * @param userId
	 * @return bank account
	 */
	@GetMapping("/bank-account/{userId}")
	public BankDTO getBankAccountByUserId(@PathVariable Long userId){
  	
		if(userId == null) {
			return null;
		}
		
		return bankService.getBankAccountDTOByUserId(userId);
      
	}

}
