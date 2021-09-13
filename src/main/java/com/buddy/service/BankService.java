package com.buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.BankDTO;
import com.buddy.model.BankAccount;
import com.buddy.repository.BankRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class BankService {
	
	@Autowired
	private BankRepository bankRepo;
	
	/**
	 * Get a bank account with the user id
	 * @param userId
	 * @return BankAccount
	 */
	public BankAccount getBankAccountByUserId(Long userId) {
		
		if(userId == null) {
			return null;
		}

		log.info("Bank Account {} found", userId);
		
		return bankRepo.getById(userId);
		
	}
	
	
	/**
	 * Get a bank account with the user id
	 * @param userId
	 * @return BankAccount
	 */
	public BankDTO getBankAccountDTOByUserId(Long userId) {

		if(userId == null) {
			return null;
		}
		
		BankAccount bankAccount = bankRepo.getById(userId);
		
		BankDTO bankDTO = BankDTO.builder().id(bankAccount.getId())
				.balance(bankAccount.getBalance()).userId(bankAccount.getUserId().getId())
				.username(bankAccount.getUserId().getUsername()).build();

		return bankDTO;
		
	}
	
	
	/**
	 * Get the bank account id by the id
	 * @param userId
	 * @return Long
	 */
	public Long getBankIdByUserId(Long userId) {
		
		if(userId == null) {
			return null;
		}
		
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
