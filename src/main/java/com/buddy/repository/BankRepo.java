package com.buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.BankAccount;

@Repository
@Transactional
public interface BankRepo extends JpaRepository<BankAccount, Long> {
	
	BankAccount findBankAccountById(Long id);
	
	Double findBalanceById(Long id);
	
	BankAccount findBankAccountByUserId(Long userId);
	
	BankAccount findByUserId(Long userId);
	
//	List<BankAccount> findByUserId(Users userId);
	
	
}
