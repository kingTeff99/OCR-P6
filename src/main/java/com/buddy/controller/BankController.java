package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.BankAccount;
import com.buddy.service.BankService;

@RestController
@RequestMapping("/api")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PostMapping("/bankaccount/create")
	public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/bankaccount/create")
				.toUriString());
		
		return ResponseEntity.created(uri)
				.body(bankService.createBankAccount(bankAccount));

	}

	@GetMapping("/bankaccount/{id}")
	public ResponseEntity<BankAccount> getBankAccountByUserId(@PathVariable Long userId){
  	
      return ResponseEntity.ok().body(bankService.getBankAccountByUserId(userId));
      
	}

}
