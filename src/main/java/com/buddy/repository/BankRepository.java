package com.buddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.BankAccount;
import com.buddy.model.Users;

@Repository
@Transactional
public interface BankRepository extends JpaRepository<BankAccount, Long> {
	
	Optional<BankAccount> findById(Long id);
	
	List<BankAccount> findByUserId(Users userId);
	
}
