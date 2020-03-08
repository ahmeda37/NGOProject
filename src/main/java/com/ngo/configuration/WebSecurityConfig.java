package com.ngo.configuration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ngo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/","/index","/home").permitAll();
		http.authorizeRequests().antMatchers("/users","/donations").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		Set<com.ngo.model.User> users = userService.getUsers();
		Set<UserDetails> usersInMem = new HashSet<UserDetails>();
		String role;
		Iterator<com.ngo.model.User> i = users.iterator();
		while(i.hasNext()) {
			com.ngo.model.User curUser = i.next();
			role = curUser.getAdmin() ? "ADMIN" : "USER";
			UserDetails user = 
					User.withDefaultPasswordEncoder().username(curUser.getEmail()).password(curUser.getHashedPassword()).roles(role).build();
			usersInMem.add(user);
		}
		return new InMemoryUserDetailsManager(usersInMem);
	}
}
