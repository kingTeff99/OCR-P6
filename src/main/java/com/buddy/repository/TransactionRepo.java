package com.buddy.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.Transaction;

@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByUserSenderId(Long id);
	
	List<Transaction> findByUserReceiverId(Long id);
	
	List<Transaction> findByUserReceiverIdAndUserSenderId(Long userSenderId, Long userReceiverId);
	
	List<Transaction> findAll();
	
	Optional<Transaction> findById(Long id);
	
}
