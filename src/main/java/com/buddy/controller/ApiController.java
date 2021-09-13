package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.dto.RegisterFormDTO;
import com.buddy.model.Users;
import com.buddy.service.UsersService;

@RestController
public class ApiController {
	
	@Autowired
	private UsersService usersService;
	
	/**
	 * POST : Method That Permit To Register a person in the Database
	 * @param userForm
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody RegisterFormDTO registerFormDTO) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/register").toUriString());
		
		if(!registerFormDTO.getPassword().equals(registerFormDTO.getRepassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		Users user = usersService.getUser(registerFormDTO.getUsername());
		
		if(user != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.build();
		}
		
		Users users = Users.builder().username(registerFormDTO.getUsername())
				.password(registerFormDTO.getPassword())
				.firstName(registerFormDTO.getFirstName())
				.lastName(registerFormDTO.getLastName()).build();
		
		return ResponseEntity.created(uri).body(usersService.saveUser(users));
		
	}
	
}
