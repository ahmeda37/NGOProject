package com.ngo.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.Cart;
import com.ngo.model.MyUser;
import com.ngo.repository.CartRepository;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository repo;
	
	@Override
	public Cart getCartById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Cart getCartByUser(MyUser user) {
		return repo.getCartByUser(user);
	}
	@Override
	public Set<Cart> getCarts() {
		// TODO Auto-generated method stub
		return new TreeSet<Cart>(repo.findAll());
	}

	@Override
	public void addCart(Cart c) {
		// TODO Auto-generated method stub
		repo.save(c);
	}

	@Override
	public void updateCart(Cart c) {
		// TODO Auto-generated method stub
		repo.save(c);
	}

	@Override
	public void deleteCart(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
