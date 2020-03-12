package com.ngo.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ngo.model.MyUser;
import com.ngo.model.Profile;
import com.ngo.service.UserService;


public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MyUser user = userService.getUserByEmail(username);
		Profile profile = null;
		List authorities = new ArrayList();
		
		if (user != null) {
			authorities.add(new SimpleGrantedAuthority(user.getAdmin() ? "ADMIN" : "USER"));
			profile = new Profile(user.getEmail(), user.getHashedPassword(), true, true, true, true,authorities);
			profile.setProfile(user);
			return profile;
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}
}
