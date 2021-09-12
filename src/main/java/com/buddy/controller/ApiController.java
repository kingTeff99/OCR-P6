package com.buddy.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buddy.dto.RegisterFormDTO;
import com.buddy.model.Users;
import com.buddy.service.UsersService;

@RestController
public class ApiController {
	
	@Autowired
	private UsersService usersService;
	
	/**
	 * POST : Method That Permit To Register a person in the Database
	 * @param userForm
	 * @return
	 */
	@PostMapping("/register")
	//TODO-Guillaume: RegisterForm -> RegisterFormDto, c'est plus "clean" dans le sens où on indique clairement que cette classe est utilisée en tant que DTO
	//TODO-Guillaume: typiquement, on peut avoir: HumainEntity (MySQL), HumainDto (HTTP), et Humain tout court, qui serait une classe utilisée dans les DTO par exemple
	public ResponseEntity<Users> register(@RequestBody RegisterFormDTO registerFormDTO) {
		
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/register").toUriString());
		
		if(!registerFormDTO.getPassword().equals(registerFormDTO.getRepassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
			//TODO-Guillaume: not a RunTimeException, ça sera une BadRequest ! Tout simplement parce que l'utilisateur a envoyé une requête, qui ne remplis pas les conditions du endpoint
		Users user = usersService.getUser(registerFormDTO.getUsername());
		
		//TODO-Guillaume: idem ici, ça sera plutôt une HTTP 409 Conflict, car l'user à envoyé un username qui existe déjà donc on ne peut pas considérer ça comme une "BadRequest"
		//TODO-Guillaume: en soit, il a bon, c'est juste qu'il a manqué de chance sur la sélection de son username
		if(user != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.build();
		}
		
		//TODO-Guillaume: utiliser @Builder de Lombok (ajout de @Builder sur la classe Users, et ensuite: "Users.builder().username(.userForm.getUsername()).build();")
		Users users = Users.builder().username(registerFormDTO.getUsername())
				.password(registerFormDTO.getPassword())
				.firstName(registerFormDTO.getFirstName())
				.lastName(registerFormDTO.getLastName()).build();
		
		return ResponseEntity.created(uri).body(usersService.saveUser(users));
		
	}
	
	//TODO-Guillaume: on préfèrera faire un package: com.buddy.dto dans lequel on mettra les classes DTO
}
