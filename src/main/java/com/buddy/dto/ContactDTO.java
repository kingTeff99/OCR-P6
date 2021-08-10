package com.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ContactDTO {
	
	 private String username;
	 
	 private Long ownUserId;
	 
	 public ContactDTO() {
		 
	 }
	 
	 

}
