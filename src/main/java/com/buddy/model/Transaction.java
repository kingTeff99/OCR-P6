package com.buddy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private Double amount;
    
	@ManyToOne
	@JoinColumn(name = "userSender_id")
    private Users userSenderId;
    
	@ManyToOne
	@JoinColumn(name = "userReceiver_id")
    private Users userReceiverId;
	
	@ManyToOne
	@JoinColumn(name = "bankSender_id")
    private BankAccount bankSenderId;
    
	@ManyToOne
	@JoinColumn(name = "bankReceiver_id")
    private BankAccount bankReceiverId;
    
    private Double fees;
    
    private String description;
    
}
