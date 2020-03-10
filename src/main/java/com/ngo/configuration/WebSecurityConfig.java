package com.ngo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ngo.bean.UserDetailServiceImpl;
import com.ngo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/","/index","/home").permitAll();
		http.authorizeRequests().antMatchers("/users/**","/donations/**").access("hasAuthority('ADMIN')");
		http.authorizeRequests().antMatchers("/gifts/**").access("hasAnyAuthority('USER','ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	  }
}
