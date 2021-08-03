package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Users;
import com.buddy.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody RegisterForm userForm) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("api/register").toUriString());
		
		if(!userForm.getPassword().equals(userForm.getRepassword())) 
			throw new RuntimeException("You must confirm your password !");
		
		Users user = usersService.getUser(userForm.getUsername());
		
		if(user != null) throw new RuntimeException("This user already exist");
		
		Users users = new Users();
		users.setUsername(userForm.getUsername());
		users.setPassword(userForm.getPassword());
		users.setFirstName(userForm.getFirstName());
		users.setLastName(userForm.getLastName());
		
		return ResponseEntity.created(uri).body(usersService.saveUser(users));
		
	}
	
	/**
	 * Class Qui Permet de faire la confirmation du MDP du user
	 * @author kingteff
	 *
	 */
	@Data @AllArgsConstructor @NoArgsConstructor
	public static class RegisterForm {
		
		private String firstName;
		private String lastName;
		private String username;
		private String password;
		private String repassword;

	}
}
