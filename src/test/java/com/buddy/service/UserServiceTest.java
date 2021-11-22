package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.Users;
import com.buddy.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {
	
	 @Autowired
	 private UserRepository userRepository;

	 @Autowired
	 private TestEntityManager entityManager;
	 
	 @Test
	 public void givenStudent_whenSave_thenGetOk() {
		Users user1 = new Users(1L, "John", "Ali", "johnali@gmail.com", "1234");
	   	userRepository.save(user1);
	    	
	   	Users student2 = userRepository.getById(1L);
	    assertEquals("John", student2.getFirstName());
	    
	 }

	 @Test
	 public void findByIdEmailTest() {
	    	
	   Users newUser = new Users(null, "John", "Ali", "johnali@gmail.com","1234");
	    
	   entityManager.persist(newUser);
	    
	   entityManager.flush();
	    
	   assertThat(userRepository.findByUsername("johnali@gmail.com")).isEqualTo(newUser);
	    
	 }

	 @Test
	 public void shouldFindTheSizeEmailTest() {
	    	
		Users user1 = new Users(null, "John", "Ali", "johnali@gmail.com","1234");
	   	entityManager.persist(user1);

	   	Users user2 = new Users(null, "Jo", "A", "joa@gmail.com","1234");
	   	entityManager.persist(user2);
	    	 
	    Iterable<Users> users = userRepository.findAll();

	    assertThat(users).hasSize(2).contains(user1, user2);
	        
    }

}
