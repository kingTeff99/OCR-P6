package com.buddy.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.Transaction;
import com.buddy.model.Users;

@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByUserReceiverIdAndUserSenderId(Users userIdSender, Users userIdReceiver);
	
	List<Transaction> findAll();
	
	Optional<Transaction> findById(Long id);
	
	List<Transaction> findByUserReceiverId(Users userReceiverId);
	
	List<Transaction> findByUserSenderId(Users userSenderId);
	
}
