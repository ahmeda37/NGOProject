package com.ngo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngo.model.Address;
import com.ngo.model.Cart;
import com.ngo.model.Gift;
import com.ngo.model.MyUser;
import com.ngo.service.CartService;
import com.ngo.service.GiftService;
import com.ngo.service.UserService;

@SpringBootTest
class NgoApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	GiftService giftService;
	
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
		MyUser user1 = createUser();
		
		userService.addUser(user1);
		
		assertThat((Long)user1.getUserId() != null);
		assertThat((Long)user1.getAddress().getId() != null);
	}
	
	
	@Test
	void getAndUpdateUser() {
		MyUser user1 = createUser();
		
		userService.addUser(user1);
		
		user1.setEmail("somethingelse@something.com");
		
		userService.updateUser(user1);
		
		user1 = userService.getUserById(user1.getUserId());
		
		assertThat(user1.getEmail().equalsIgnoreCase("somethingelse@something.com"));
		
	}
	
	@Test
	void getListOfUser() {
		MyUser user1 = createUser();
		userService.addUser(user1);
		
		MyUser user2 = createUser();
		userService.addUser(user2);
		
		Set<MyUser> users = userService.getUsers();
		
		assertThat(users.size() == 2);
		
	}
	
	@Test
	void deleteUser() {
		MyUser user1 = createUser();
		userService.addUser(user1);
		userService.deleteUser(user1.getUserId());
		assertThat(user1 == null);
	}
	
	MyUser createUser() {
		MyUser user1 = new MyUser();
		Address address1 = new Address();
		
		address1.setAddress1("12345 123 ST NW");
		address1.setCity("Edmonton");
		address1.setCountry("Canada");
		address1.setZip("T1T 1T1");
		
		user1.setFirstName("Abdurahman");
		user1.setLastName("Ahmed");
		user1.setEmail("abdahmed@live.ca");
		user1.setHashedPassword("SomethingSomething");
		user1.setAdmin(true);
		user1.setAddress(address1);
		
		return user1;
	}
	
	@Test
	void insertCart() {
		Cart c1 = createCart();
		
		cartService.addCart(c1);
		
		assertThat((Long)c1.getCartId() != null);
		assertThat((Long)c1.getUser().getUserId() != null);
		assertThat(c1.getGifts().size() != 0);
	}
	
	@Test
	void getCart() {
		Cart c1 = createCart();
		cartService.addCart(c1);
		
		c1 = cartService.getCartById(1);
		
		assertThat((Long)c1.getCartId() != null);
		assertThat((Long)c1.getUser().getUserId() != null);
		assertThat(c1.getGifts().size() != 0);
		assertThat(c1.getGifts().iterator().next().getGiftAmount() == 100);
	}
	
	@Test
	void listOfCarts() {
		Cart c1 = createCart();
		cartService.addCart(c1);
		
		Cart c2 = createCart();
		cartService.addCart(c2);
		
		Set<Cart> sc = cartService.getCarts();
		assertThat(sc.iterator().next().compareTo(c1) == 0);
		assertThat(sc.size() == 2);
	}
	
	@Test
	void deleteCart() {
		Cart c1 = createCart();
		cartService.addCart(c1);
		cartService.deleteCart(1);
		assertThat(c1 == null);
	}
	
	@Test
	void insertGift(){
		Gift g = createGift();
		giftService.addGift(g);
		assertThat((Long)g.getGiftId() != null);
	}
	
	@Test
	void getGift() {
		Gift g = createGift();
		giftService.addGift(g);
		Gift g2 = giftService.getGiftById(1);
		assertThat(g.compareTo(g2) == 0);
	}
	@Test
	void deleteGift() {
		Gift g = createGift();
		giftService.addGift(g);
		giftService.deleteGift(1);
		assertThat(g == null);
	}
	Cart createCart() {
		MyUser user1 = createUser();
		userService.addUser(user1);
		Gift g = createGift();
		giftService.addGift(g);
		Cart c = new Cart();
		Set<Gift> gifts = new TreeSet<Gift>();
		gifts.add(g);
		c.setGifts(gifts);
		c.setProcessed(true);
		c.setTotal(100);
		c.setUser(user1);
		return c;
	}
	
	Gift createGift() {
		Gift g = new Gift();
		g.setGiftAmount(100);
		g.setQuantity(1);
		g.setRecurring(true);
		return g;
	}
}
