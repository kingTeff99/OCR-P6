package com.buddy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.ContactDTO;
import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private UsersService usersService;
	
	
	/**
	 * Verify the link between two users Id 
	 * @param userRelatingId
	 * @param userRelatedId
	 * @return Contact
	 */
	public ContactDTO verifyRelationship(Long userRelatingId, Long userRelatedId) {
		
		if((userRelatingId == null) || (userRelatedId == null)) {
			return null;
		}
		
		log.info("Relationship verified");
		
		Users userRelatingIdContact = contactRepo.getById(userRelatingId).getUserRelatingId();
		
		Users userRelatedIdContact = contactRepo.getById(userRelatedId).getUserRelatingId();
		 
		Contact contact1 = contactRepo.findByUserRelatingIdAndUserRelatedId(userRelatingIdContact
				, userRelatedIdContact);
		
		contactRepo.save(contact1);
		
		return ContactDTO.builder().id(contact1.getId())
				.userRelatingId(contact1.getUserRelatingId().getId())
				.userRelatedId(contact1.getUserRelatedId().getId())
				.build();
	 }
	
	 
	/**
	 * Add a new contact in order to make a transaction
	 * @param itsUsername
	 * @param myUsername
	 * @return Contact
	 */
	public ContactDTO addContact(String username, String myUsername) {
	 
		if((username == null) || (myUsername == null)) {
			return null;
		}
		
		log.info("New Contact added");
		 
		Users contactToAdd = usersService.getUser(username);
		 
		Users mycontact = usersService.getUser(myUsername);
		
		Contact newContact = Contact.builder().userRelatedId(contactToAdd)
				.userRelatingId(mycontact).build();
		
		contactRepo.save(newContact);
		 
		return ContactDTO.builder().id(newContact.getId())
		.userRelatedId(contactToAdd.getId())
		.userRelatingId(mycontact.getId()).build();
		
	}
	
}
