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
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public BankAccount getBankAccountByUserId(Long userId) {

		log.info("Bank Account {} found", userId);
		
		return bankRepo.getById(userId);
		
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Long getBankIdByUserId(Long userId) {
		
		return getBankAccountByUserId(userId).getId();
		
	}
	
	/**
	 * 
	 * @param bankAccount
	 * @return
	 */
	public BankAccount createBankAccount(BankAccount bankAccount) {
		
		log.info("New Bank Account Created");
		
		return bankRepo.save(bankAccount);
		
	}

	
}
