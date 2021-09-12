package com.buddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.TransactionDTO;
import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.model.Users;
import com.buddy.repository.BankRepository;
import com.buddy.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class TransactionService {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	/**
	 * Make a transaction
	 * @param transaction
	 * @return Transaction
	 */
	public TransactionDTO makeTransactionWithInputVerification(Transaction transaction) {
		
        try {

            if(contactService.verifyRelationship(transaction.getUserSenderId(), transaction.getUserReceiverId()) == null)
                throw new Exception();
            
            BankAccount sender = bankService.getBankAccountByUserId(transaction.getUserSenderId().getId());
            
            BankAccount receiver = bankService.getBankAccountByUserId(transaction.getUserReceiverId().getId());

            Double fees = Math.round((transaction.getAmount() * 0.05) * 100.0)/100.0;
            
            sender.setBalance( Math.round((sender.getBalance() - transaction.getAmount() - fees) * 100.0) / 100.0);
            
            receiver.setBalance( Math.round((receiver.getBalance() + transaction.getAmount()) * 100.0) / 100.0);
            
            transaction.setFees(fees);
            
            bankRepo.save(sender);
            
            bankRepo.save(receiver);
            
            transactionRepo.save(transaction);
            
            log.info("Transaction {} made", transaction.getId());
            
            TransactionDTO transactionDTO = TransactionDTO.builder().id(transaction.getId())
            		.amount(transaction.getAmount()).description(transaction.getDescription()).build();
            
            return transactionDTO;
            
        } catch (Exception e){
        	
            e.printStackTrace();
            
            return null;
        }
    }

	/**
	 * Get a Transaction
	 * @param userIdSender
	 * @param userIdReceiver
	 * @return List of transaction
	 */
    public List<Transaction> getTransactions(Users userSenderId, Users userReceiverId) {
    	
    	if((userSenderId == null) ||(userReceiverId == null)) {
    		return null;
    	}
    	
    	return transactionRepo.findByUserReceiverIdAndUserSenderId(userSenderId, userReceiverId);
    	
    }
    
    /**
     * Get All Transactions By User Id
     * @param id
     * @return List of transaction
     */
    public List<Transaction> getAllTransactionsForAnUser(Users userId) {
    	
    	if(userId == null) {
    		return null;
    	}
    	
    	List<Transaction> allTransaction = new ArrayList<>();
    	
    	allTransaction.addAll(transactionRepo.findByUserReceiverId(userId));
    	
    	allTransaction.addAll(transactionRepo.findByUserSenderId(userId));
    	
    	return allTransaction;
    			
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public Transaction getTransac(Long id) {
    	
    	if(id == null) {
    		return null;
    	}
    	
        return transactionRepo.getById(id);
    	
    }
    
}
