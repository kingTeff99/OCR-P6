package com.buddy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private Double amount;
    
//	@ManyToOne(fetch=FetchType.EAGER)
//    private Users userSenderId;
//    
//	@ManyToOne(fetch=FetchType.EAGER)
//    private Users userReceiverId;
//	
//	@ManyToOne(fetch=FetchType.EAGER)
//    private BankAccount bankSenderId;
//    
//	@ManyToOne(fetch=FetchType.EAGER)
//    private BankAccount bankReceiverId;
	
    private Long userSenderId;
    
    private Long userReceiverId;
	
    private Long bankSenderId;
    
    private Long bankReceiverId;
    
    private Double fees;
    
    private String description;

}
