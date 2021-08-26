package com.buddy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
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
import com.buddy.repository.ContactRepo;
import com.buddy.service.UsersService;

@WebMvcTest(controllers = UserController.class)
@RunWith(SpringRunner.class)
public class UsersControllerTest {
	
	@Autowired
 	private MockMvc mvc;
	
	@MockBean
	private UsersService usersService;
	
	@MockBean
	private ContactRepo contactRepo;
	
	
	@Test
	public void getUsersTest() throws Exception {
	    
		//GIVEN
	    List<Users> allUsers = Arrays.asList( new Users(1L, "John", "Ali", "johnali@gmail.com", "1234"));
	    
	    //WHEN
	    when(usersService.getUsers()).thenReturn(allUsers);

	    mvc.perform(get("/api/users")
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().isOk())
	       .andExpect(jsonPath("$", hasSize(1)))
	       .andExpect(jsonPath("$[0].id", is(1)))
	       .andExpect(jsonPath("$[0].firstName", is("John")))
	       .andExpect(jsonPath("$[0].lastName", is("Ali")))
	       .andExpect(jsonPath("$[0].username", is("johnali@gmail.com")));
	    
	    verify(usersService, times(1)).getUsers();
	    
	}
	
	@Test
	public void shouldReturnPersonalInformationsByIdTest() throws Exception {
	    
		//GIVEN
		Optional<Users> user1 = Optional.ofNullable(new Users(1L, "Pierre", "Paul", "pierrepaul@gmail.com", "1234"));
	    
	    //WHEN
	    when(usersService.getUserById(1L)).thenReturn(user1);

	    mvc.perform(get("/api/users/{id}/infos", 1)
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().isOk())
	       .andExpect(jsonPath("$.id", is(1)))
	       .andExpect(jsonPath("$.firstName", is("Pierre")))
	       .andExpect(jsonPath("$.lastName", is("Paul")))
	       .andExpect(jsonPath("$.username", is("pierrepaul@gmail.com")));
	    
	    verify(usersService, times(1)).getUserById(1L);
	    
	}
	
	@Test
	public void deleteIfFirestationExistTest() throws Exception {
	      
		String userJSON = "{\n" +
				"        \"id\": \"1L\",\n" +
                "        \"firstName\": \"Steve\",\n" +
                "        \"lastName\": \"Biko\",\n" +
                "        \"username\": \"stevebiko@gmail.com\",\n" +
                "        \"password\": \"1234\"\n" +
                "    }";

	    doNothing().when(usersService).deleteUserById(1L);

	    mvc.perform(delete("/users/{id}/delete", 1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(userJSON))
	                .andExpect(status().is3xxRedirection());

//	    verify(usersService, times(1)).deleteUserById(1L);
	}
	
}
	

