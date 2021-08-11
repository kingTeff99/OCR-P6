package com.buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.BankDTO;
import com.buddy.model.BankAccount;
import com.buddy.repository.BankRepo;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class BankService {
	
	@Autowired
	private BankRepo bankRepo;
	
	/**
	 * Get a bank account with the user id
	 * @param userId
	 * @return BankAccount
	 */
	public BankAccount getBankAccountByUserId(Long userId) {

		log.info("Bank Account {} found", userId);
		
		return bankRepo.getById(userId);
		
	}
	
	
	
	/**
	 * Get a bank account with the user id
	 * @param userId
	 * @return BankAccount
	 */
	public BankDTO getBankAccountDTOByUserId(Long userId) {

		BankAccount bankAccount = bankRepo.getById(userId);
		
		BankDTO bankDTO = new BankDTO();
		
		bankDTO.setId(bankAccount.getId());
		
		bankDTO.setBalance(bankAccount.getBalance());

		bankDTO.setUserId(bankAccount.getUserId().getId());
		
		bankDTO.setUsername(bankAccount.getUserId().getUsername());

		return bankDTO;
		
	}
	
	
	/**
	 * Get the bank account id by the id
	 * @param userId
	 * @return Long
	 */
	public Long getBankIdByUserId(Long userId) {
		
		return getBankAccountByUserId(userId).getId();
		
	}
	
	/**
	 * Create a new bank account
	 * @param bankAccount
	 * @return BankAccount
	 */
	public BankAccount createBankAccount(BankAccount bankAccount) {
		
		log.info("New Bank Account Created");
		
		return bankRepo.save(bankAccount);
		
	}

	
}
