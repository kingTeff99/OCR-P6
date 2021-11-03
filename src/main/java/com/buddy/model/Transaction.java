package com.buddy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  resolver = EntityIdResolver.class,
	      scope = Transaction.class)
@Entity @Data @NoArgsConstructor @AllArgsConstructor@Builder
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private Double amount;
    
	@ManyToOne
	@JoinColumn(name = "user_sender_id")
    private Users userSenderId;
    
	@ManyToOne
	@JoinColumn(name = "user_receiver_id")
    private Users userReceiverId;
	
	@ManyToOne
	@JoinColumn(name = "bank_sender_id")
    private BankAccount bankSenderId;
    
	@ManyToOne
	@JoinColumn(name = "bank_receiver_id")
    private BankAccount bankReceiverId;
    
    private Double fees;
    
    private String description;
    
    
}
