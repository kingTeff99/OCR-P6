package com.buddy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private Double balance;
    
//	@OneToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name = "userId")
//    private Users userId;
	
    private Long userId;
	
}
