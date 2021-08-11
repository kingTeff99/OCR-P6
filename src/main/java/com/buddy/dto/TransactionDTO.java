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
public class TransactionDTO {
	
	private Long id;
		
	private Double amount;
	
//    private Long user1;
//    
//    private Long user2;
//	
//    private Long bank1;
//    
//    private Long bank2;
	
	private String description;
	
}
