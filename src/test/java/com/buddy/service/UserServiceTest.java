package com.buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.buddy.model.Users;
import com.buddy.repository.UserRepo;

@ActiveProfiles("test")
@TestPropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest
public class UserServiceTest {
	
	 @BeforeEach
	 public void setUp() {
		 
	 }

	 @MockBean
	 private UserRepo userRepo;

	 @MockBean
	 private TestEntityManager entityManager;
	    
	    
	 @Test
	 public void givenStudent_whenSave_thenGetOk() {
		Users user1 = new Users(1L, "John", "Ali", "johnali@gmail.com", "1234");
	   	userRepo.save(user1);
	    	
	   	Users student2 = userRepo.getById(1L);
	    assertEquals("John", student2.getFirstName());
	    
    }

	    @Test
	    public void findByIdEmailTest() {
	    	
	    	Users newUser = new Users(null, "John", "Ali", "johnali@gmail.com","1234");
	    
	    	entityManager.persist(newUser );
	    
	    	entityManager.flush();
	    
	    	assertThat(userRepo.findByUsername("johnali@gmail.com")).isEqualTo(newUser);
	    
	    }

//	    @Test
//	    void shouldFindTheSizeEmailTest() {
//	    	
//	    	Users user1 = new Users(1L, "John", "Ali", "johnali@gmail.com","1234");
//	    	entityManager.persist(user1);
//
//	    	Users user2 = new Users(2L, "Jo", "A", "joa@gmail.com","1234");
//	    	entityManager.persist(user2);
//	    	 
//	        Iterable<Users> tutorials = userRepo.findAll();
//
//	        assertThat(tutorials).isNotEmpty();
//	        
//	    }



}
