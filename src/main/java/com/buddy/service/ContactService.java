package com.buddy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepo;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class ContactService {
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private UsersService usersService;
	
	
	/**
	 * 
	 * @param userRelatingId
	 * @param userRelatedId
	 * @return
	 */
	public Contact verifyRelationship(Users userRelatingId, Users userRelatedId) {
		 
		 log.info("Relationship verified");
		 
		 return contactRepo
				 .findByUserRelatingIdAndUserRelatedId(userRelatingId, userRelatedId);
		 
	 }
	 
	/**
	 * 
	 * @param username
	 * @param ownUserId
	 * @return
	 */
	public Contact addContact(String itsUsername, String myUsername) {
	 
		 Contact newContact = new Contact();
		 
		 log.info("New Contact added");
		 
		 Users contactToAdd = usersService.getUser(itsUsername);
		 
		 Users mycontact = usersService.getUser(myUsername);
		 
		 newContact.setUserRelatedId(contactToAdd);
		  
		 newContact.setUserRelatingId(mycontact);
		  
		 contactRepo.save(newContact);
		 
		 return newContact;
	 }
	 
}
