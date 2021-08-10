package com.buddy.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JwtResponse {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String token;
	
	private String type = "Bearer";
	
	public JwtResponse(String accessToken, Long id, String username, String email) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
	}

}
