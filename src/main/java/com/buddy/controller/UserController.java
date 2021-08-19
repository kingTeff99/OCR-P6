package com.buddy.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepo;
import com.buddy.service.UsersService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ContactRepo contactRepo;
	
	/**
	 * GET : Get all personal informations for an user
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:5500")
	@GetMapping("/users/{id}/infos")
	public ResponseEntity<Optional<Users>> getPersonalInfosById(@PathVariable Long id) {
  	
      return ResponseEntity.ok().body(usersService.getUserById(id));
      
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:5500")
	@GetMapping("/users/{id}/contact")
	public ResponseEntity<List<Contact>> getContact(@PathVariable Long id) {
  	
		Optional<Users> user = usersService.getUserById(id);
		
		Users users = user.get();
    
		return ResponseEntity.ok().body(contactRepo.findByUserRelatedId(users));
      
	}
	
	
	
	/**
	 * GET : Get all users
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:5500")
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() {
		
		return ResponseEntity.ok().body(usersService.getUsers());
		
	}
	
	/**
	 * POST : Save A New User
	 * @param users
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:5500")
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
	@CrossOrigin(origins = "http://localhost:5500")
	@DeleteMapping("/users/{id}/delete")
	public ResponseEntity<?> deleteUserByiD(@PathVariable Long id) {
		
		usersService.deleteUserById(id);
		
		return ResponseEntity.ok().build();
		
	}

}
