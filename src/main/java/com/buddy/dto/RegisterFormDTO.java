package com.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterFormDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private String repassword;

}
