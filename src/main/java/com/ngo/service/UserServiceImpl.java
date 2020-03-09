package com.ngo.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.Cart;
import com.ngo.model.MyUser;
import com.ngo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public MyUser getUserById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public MyUser getUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	@Override
	public Set<MyUser> getUsers() {
		// TODO Auto-generated method stub
		return new TreeSet<MyUser>(repo.findAll());
	}

	@Override
	public void addUser(MyUser u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}

	@Override
	public void updateUser(MyUser u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		MyUser u = repo.findById(id).get();
		Cart c = cartService.getCartByUser(u);
		c.setUser(null);
		cartService.updateCart(c);
		repo.deleteById(id);
	}

}
