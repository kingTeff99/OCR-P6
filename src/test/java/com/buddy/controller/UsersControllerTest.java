package com.buddy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.buddy.model.Users;
import com.buddy.repository.ContactRepository;
import com.buddy.service.BankService;
import com.buddy.service.ContactService;
import com.buddy.service.TransactionService;
import com.buddy.service.UsersService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class UsersControllerTest {
	
	@Autowired
 	private MockMvc mvc;
	
	@MockBean
	private UsersService usersService;
	
	@MockBean 
	private BankService bankService;
	
	@MockBean 
	private ContactService contactService;
	
	@MockBean 
	private ContactRepository contactRepository;
	
	@MockBean 
	private TransactionService transactionService;
	
	
	@Test
	public void getAllUsersTest() throws Exception {
	    
		//Arrange
	    List<Users> allUsers = Arrays.asList( new Users(2L, "John", "Ali", "johnali@gmail.com", "1234"));
	    
	    System.out.println(usersService);
	    
	    //Act
	    when(usersService.getUsers()).thenReturn(allUsers);
	    
	    mvc.perform(get("/users")
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().is2xxSuccessful())
	       .andExpect(jsonPath("$[0].id", is(2)))
	       .andExpect(jsonPath("$[0].firstName", is("John")))
	       .andExpect(jsonPath("$[0].lastName", is("Ali")))
	       .andExpect(jsonPath("$[0].username", is("johnali@gmail.com")));
	    
	    //Assert
	    verify(usersService, times(1)).getUsers();
	    
	}
	
	@Test
	public void shouldReturnPersonalInformationsByIdTest() throws Exception {
	    
		//Arrange
		Optional<Users> user1 = Optional.ofNullable(
				new Users(1L, "Pierre", "Paul", "pierrepaul@gmail.com", "1234"));
	    
	    //Act
	    when(usersService.getUserById(1L)).thenReturn(user1);

	    mvc.perform(get("/users/{id}/infos", 1)
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().is2xxSuccessful())
	       .andExpect(jsonPath("$.id", is(1)))
	       .andExpect(jsonPath("$.firstName", is("Pierre")))
	       .andExpect(jsonPath("$.lastName", is("Paul")))
	       .andExpect(jsonPath("$.username", is("pierrepaul@gmail.com")));
	    
	    //Assert
	    verify(usersService, times(1)).getUserById(1L);
	    
	}
	
	@Test
	public void deleteIfUserExistTest() throws Exception {
		
	    //Arrange  
		String userJSON = "{\n" +
				"        \"id\": \"1L\",\n" +
                "        \"firstName\": \"Steve\",\n" +
                "        \"lastName\": \"Biko\",\n" +
                "        \"username\": \"stevebiko@gmail.com\",\n" +
                "        \"password\": \"1234\"\n" +
                "    }";
		
		//Act
	    doNothing().when(usersService).deleteUserById(1L);
	    
	    mvc.perform(delete("/users/{id}/delete", 1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(userJSON))
	                .andExpect(status().is2xxSuccessful());

	}
	
}
	

