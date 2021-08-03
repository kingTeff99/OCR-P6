package com.buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.BankAccount;
import com.buddy.repository.BankRepo;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class BankService {
	
	@Autowired
	private BankRepo bankRepo;
	
	public BankAccount getBankAccountByUserId(Long userId) {

		log.info("Bank Account{} found", userId);
		
		return bankRepo.findBankAccountByUserId(userId);
		
	}
	
	public Long getBankIdByUserId(Long userId) {
		
		return getBankAccountByUserId(userId).getId();
		
	}
	
	public BankAccount createBankAccount(BankAccount bankAccount) {
		
		log.info("New Bank Account Created");
		
		return bankRepo.save(bankAccount);
		
	}
	
}
