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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;
import com.buddy.service.UsersService;

@RestController
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ContactRepository contactRepo;
	
	/**
	 * GET : Get all users
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		
		return ResponseEntity.ok().body(usersService.getUsers());
		
	}
	
	/**
	 * GET : Get all personal informations for an user
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}/infos")
	public ResponseEntity<Users> getPersonalInfosById(@PathVariable Long id) {
		
		Optional<Users> optUsers = usersService.getUserById(id);
		
		if(optUsers == null || optUsers.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(optUsers.get());
      
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}/contact")
	public ResponseEntity<List<Contact>> getContact(@PathVariable Long id) {
  	
		if(id == null) {
			return null;
		}
		
		Optional<Users> user = usersService.getUserById(id);
		
		if(user.isPresent()) {
			
			return ResponseEntity.ok()
					.body(contactRepo.findByUserRelatedId(user.get()));

		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	/**
	 * POST : Save A New User
	 * @param users
	 * @return
	 */
	@PostMapping("/users/save")
	public ResponseEntity<Users> saveUser(@RequestBody Users users) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("api/users/save")
				.toUriString());
		
		return ResponseEntity.created(uri).body(usersService.saveUser(users));
		
	}
	
	/**
	 * DELETE : Delete An User
	 * @param username
	 * @return
	 */
	@DeleteMapping("/users/{id}/delete")
	public ResponseEntity<?> deleteUserByiD(@PathVariable Long id) {
		
		usersService.deleteUserById(id);
		
		return ResponseEntity.ok().build();
		
	}
	
}
