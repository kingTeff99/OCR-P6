package com.buddy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private Double balance;
    
	@OneToOne
	@JoinColumn(name = "user_id")
	private Users userId;
		
}
