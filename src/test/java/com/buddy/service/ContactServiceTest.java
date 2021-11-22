package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.Contact;
import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;
import com.buddy.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactServiceTest {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private ContactRepository contactRepository;
	 
	 
	 @Test
	 public void findByIdTest() {
		 
		 Users user1 = new Users(null, "Anna", "Ali", "AnnaAli@gmail.com", "1234");
		 userRepository.save(user1);
		 
		 Users user2 = new Users(null, "Annie", "Dupon", "AnnieDupon@gmail.com","1234");
		 userRepository.save(user2);
		 
		 Optional<Contact> optContact = Optional.ofNullable(new Contact(null, user1, user2));
		 
		 contactRepository.save(optContact.get());
		 
		 assertThat(contactRepository.findById(optContact.get().getId())).isEqualTo(optContact);
		 
		 
	 }
	 
	 @Test
	 public void findByUserRelatedIdTest() {
		 
		 Users user1 = new Users(null, "Paul", "Remy", "paulremy@gmail.com", "1234");
		 userRepository.save(user1);
		 
		 Users user2 = new Users(null, "Pierre", "Dupond", "PierreDupond@gmail.com","1234");
		 userRepository.save(user2);
		 
		 
		 Contact contact1 = new Contact(1L, user1, user2);
		 contactRepository.save(contact1);
		 
		 List<Contact> contactList = Arrays.asList(contact1);
		 
		 assertThat(contactRepository.findByUserRelatedId(user2)).isEqualTo(contactList);
		 
	 }
	 
	 @Test
	 public void findByUserRelatingIdTest() {
		 
		 Users user1 = new Users(null, "Paul", "Remy", "paulremy@gmail.com", "1234");
		 userRepository.save(user1);
		 
		 Users user2 = new Users(null, "Pierre", "Dupond", "PierreDupond@gmail.com","1234");
		 userRepository.save(user2);
		 
		 
		 Contact contact1 = new Contact(1L, user1, user2);
		 contactRepository.save(contact1);
		 
		 Contact contact2 = new Contact(2L, user2, user1);
		 contactRepository.save(contact2);
		 
		 List<Contact> contactList = Arrays.asList(contact2);
		 
		 assertThat(contactRepository.findByUserRelatedId(user1)).isEqualTo(contactList);
		 
	 }

}
