package com.buddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity @Configuration @RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		 
		return new BCryptPasswordEncoder();
	    
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CustomAuthenticationFilter customAuthenticationFilter 
		=	new CustomAuthenticationFilter(authenticationManagerBean());
			
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
			
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		.and().formLogin().loginProcessingUrl("/api/login")
//		.loginPage("/api/login")
//		.defaultSuccessUrl("/homepage.html", true)
		.and().authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**"
		,"/users/**", "/register/**","/bank-account/**","/transaction/**"
		,"/contact/add").permitAll()
		.and().authorizeRequests().antMatchers("/user/save/**").permitAll()
		
		.and().authorizeRequests().antMatchers("/user/**").permitAll()
		
		.and().authorizeRequests().anyRequest().authenticated();
		
		http.addFilter(customAuthenticationFilter);
		
	}
	
}
