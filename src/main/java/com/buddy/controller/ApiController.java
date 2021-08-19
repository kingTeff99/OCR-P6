package com.buddy.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Users;
import com.buddy.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class ApiController {
	
	@Autowired
	private UsersService usersService;
	
	
//	@RequestMapping(value= "/api/**", method=RequestMethod.OPTIONS)
//	public void corsHeaders(HttpServletResponse response) {
//	    response.addHeader("Access-Control-Allow-Origin", "*");
//	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
//	    response.addHeader("Access-Control-Max-Age", "3600");
//	}
//	
	
	/**
	 * POST : Method That Permit To Register a person in the Database
	 * @param userForm
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:5500")
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
	 * Class To permit the confirmation of password's User
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
	
	@Data @AllArgsConstructor @NoArgsConstructor
	public static class ConnexionFrom {
		
		private String username;
		private String password;
		
	}
}
