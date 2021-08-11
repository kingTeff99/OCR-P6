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
public class FullTransactionDTO {
	
	private Long id;
	
	private Double amount;
	
    private Long userSender;
    
    private Long userReceiver;
	
    private Long bankSender;
    
    private Long bankReceiver;
	
	private String description;

}
