package com.buddy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dto.ContactDTO;
import com.buddy.dto.ContactFormulaDTO;
import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
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
		
		Users userRelatingIdContact = contactRepository.getById(userRelatingId).getUserRelatingId();
		
		Users userRelatedIdContact = contactRepository.getById(userRelatedId).getUserRelatingId();
		 
		Contact contact1 = contactRepository.findByUserRelatingIdAndUserRelatedId(userRelatingIdContact
				, userRelatedIdContact);
		
		contactRepository.save(contact1);
		
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
	public ContactDTO addContact(ContactFormulaDTO contactFormulaDTO) {
	 
		if(contactFormulaDTO == null) {
			return null;
		}
		
		log.info("New Contact added");
		 
		Users contactToAdd = usersService.getUser(contactFormulaDTO.getUsername());
		 
		Users mycontact = usersService.getUser(contactFormulaDTO.getMyUsername());
		
		Contact newContact = Contact.builder().userRelatedId(contactToAdd)
				.userRelatingId(mycontact).build();
		
		contactRepository.save(newContact);
		 
		return ContactDTO.builder().id(newContact.getId())
		.userRelatedId(contactToAdd.getId())
		.userRelatingId(mycontact.getId()).build();
		
	}
	
}
