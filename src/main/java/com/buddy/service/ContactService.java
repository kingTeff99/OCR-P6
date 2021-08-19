package com.buddy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.ContactDTO;
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
	 * Verify the link between two users Id 
	 * @param userRelatingId
	 * @param userRelatedId
	 * @return Contact
	 */
	public Contact verifyRelationship(Users userRelatingId, Users userRelatedId) {
		 
		 log.info("Relationship verified");
		 
		 return contactRepo
				 .findByUserRelatingIdAndUserRelatedId(userRelatingId, userRelatedId);
		 
	 }
	
	 
	/**
	 * Add a new contact in order to make a transaction
	 * @param itsUsername
	 * @param myUsername
	 * @return Contact
	 */
	public ContactDTO addContact(String username, String myUsername) {
	 
		 Contact newContact = new Contact();
		 
		 ContactDTO newLink = new ContactDTO();
		 
		 log.info("New Contact added");
		 
		 Users contactToAdd = usersService.getUser(username);
		 
		 Users mycontact = usersService.getUser(myUsername);
		 
		 newContact.setUserRelatedId(contactToAdd);
		  
		 newContact.setUserRelatingId(mycontact);
		  
		 contactRepo.save(newContact);
		 
		 newLink.setId(newContact.getId());
		 
		 newLink.setUserRelatedId(contactToAdd.getId());
		 
		 newLink.setUserRelatingId(mycontact.getId());

		 return newLink;
	}
	
}
