package com.buddy.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Data @AllArgsConstructor @NoArgsConstructor
public class BankDTO {
	
	private Long id;
	
    private Double balance;
    
	private Long userId;
	
	private String username;
	
}