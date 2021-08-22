package com.buddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.buddy.security.WebSecurityConfig;
import com.buddy.service.UsersService;

@Configuration
@ComponentScan(basePackages = {"com.buddy"})
@Import({WebSecurityConfig.class})
public class Config {
	
	@Bean
	public UsersService usersService() {
		
		return new UsersService();
		
	}

}
