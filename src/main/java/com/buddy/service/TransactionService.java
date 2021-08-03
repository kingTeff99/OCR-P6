package com.buddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.repository.BankRepo;
import com.buddy.repository.TransactionRepo;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class TransactionService {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private BankRepo bankRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	public Long makeTransaction(Transaction transaction) {
		
        try {

            if(contactService.verifyRelationship(transaction.getUserSenderId(), transaction.getUserReceiverId()) == null)
                throw new Exception();
            
            BankAccount sender = bankService.getBankAccountByUserId(transaction.getUserSenderId());
            
            BankAccount receiver = bankService.getBankAccountByUserId(transaction.getUserReceiverId());

            Double fees = Math.round((transaction.getAmount() * 0.05) * 100.0)/100.0;
            
            sender.setBalance( Math.round((sender.getBalance() - transaction.getAmount() - fees) * 100.0) / 100.0);
            
            receiver.setBalance( Math.round((receiver.getBalance() + transaction.getAmount()) * 100.0) / 100.0);
            
            transaction.setFees(fees);
            
            bankRepo.save(sender);
            
            bankRepo.save(receiver);
            
            transactionRepo.save(transaction);
            
            log.info("Transaction {} made", transaction.getId());
            
            return transaction.getId();
            
        } catch (Exception e){
        	
            e.printStackTrace();
            
            return null;
        }
    }

    public List<Transaction> getTransactions(Long userIdSender, Long userIdReceiver) {
    	
    	return transactionRepo.findByUserReceiverIdAndUserSenderId(userIdSender, userIdReceiver);
    	
    }
    
    public List<Transaction> getAllTransactionsForAnUser(Long id) {
    	
    	List<Transaction> allTransaction = new ArrayList<>();
    	
    	allTransaction.addAll(transactionRepo.findByUserReceiverId(id));
    	
    	allTransaction.addAll(transactionRepo.findByUserSenderId(id));
    	
    	return allTransaction;
    			
    }


}
