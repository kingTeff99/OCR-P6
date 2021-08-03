package com.buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.Contact;
import com.buddy.repository.ContactRepo;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class ContactService {
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private UsersService usersService;
	
	
	public Contact verifyRelationship(Long userRelatingId, Long userRelatedId) {
		 
		 log.info("Relationship verified");
		 
		 return contactRepo
				 .findByUserRelatingIdAndUserRelatedId(userRelatingId, userRelatedId);
		 
	 }
	 
	 public Contact addContact(String username, Long ownUserId) {
		 
		 Contact newContact = new Contact();
		 
		 log.info("New Contact added");
		 
		  Long contactToAdd = usersService.getUser(username).getId();
		  
		  newContact.setUserRelatedId(contactToAdd);
		  
		  newContact.setUserRelatingId(ownUserId);
		  
		  contactRepo.save(newContact);
		 
		 return newContact;
	 }
	 
}
