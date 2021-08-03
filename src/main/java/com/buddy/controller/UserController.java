package com.buddy.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Users;
import com.buddy.service.UsersService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/users/{id}/infos")
	public ResponseEntity<Optional<Users>> getPersonalInfosById(@PathVariable Long id) {
  	
      return ResponseEntity.ok().body(usersService.getUserById(id));
      
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		
		return ResponseEntity.ok().body(usersService.getUsers());
		
	}
	
	@PostMapping("/users/save")
	public ResponseEntity<Users> saveUser(@RequestBody Users users) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/users/save")
				.toUriString());
		
		return ResponseEntity.created(uri).body(usersService.saveUser(users));
		
	}
	
	@DeleteMapping("/users/delete")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
		
		usersService.deleteByUsername(username);
		
		return ResponseEntity.ok().build();
		
	}

}
