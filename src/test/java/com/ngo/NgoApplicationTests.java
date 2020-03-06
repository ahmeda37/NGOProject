package com.ngo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngo.model.Address;
import com.ngo.model.User;
import com.ngo.service.CartService;
import com.ngo.service.UserService;

@SpringBootTest
class NgoApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void autoWiredCorrect() {
		assertThat(userService != null);
		assertThat(cartService != null);
	}
	
	@Test
	void insertUser() {
		User user1 = createUser();
		
		userService.addUser(user1);
		
		assertThat((Long)user1.getUserId() != null);
		assertThat((Long)user1.getAddress().getId() != null);
	}
	
	
	@Test
	void getAndUpdateUser() {
		User user1 = createUser();
		
		userService.addUser(user1);
		
		user1.setEmail("somethingelse@something.com");
		
		userService.updateUser(user1);
		
		user1 = userService.getUserById(user1.getUserId());
		
		assertThat(user1.getEmail().equalsIgnoreCase("somethingelse@something.com"));
		
	}
	
	@Test
	void getListOfUser() {
		User user1 = createUser();
		userService.addUser(user1);
		
		User user2 = createUser();
		userService.addUser(user2);
		
		Set<User> users = userService.getUsers();
		
		assertThat(users.size() == 2);
		
	}
	
	@Test
	void deleteUser() {
		User user1 = createUser();
		userService.addUser(user1);
		userService.deleteUser(user1.getUserId());
		assertThat(user1 == null);
	}
	
	User createUser() {
		User user1 = new User();
		Address address1 = new Address();
		
		address1.setAddress1("12345 123 ST NW");
		address1.setCity("Edmonton");
		address1.setCountry("Canada");
		address1.setZip("T1T 1T1");
		
		user1.setFirstName("Abdurahman");
		user1.setLast_name("Ahmed");
		user1.setEmail("abdahmed@live.ca");
		user1.setHashedPassword("SomethingSomething");
		user1.setAdmin(true);
		user1.setAddress(address1);
		
		return user1;
	}
}
