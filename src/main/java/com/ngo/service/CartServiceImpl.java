package com.ngo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.Cart;
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
	public Set<Cart> getCarts() {
		// TODO Auto-generated method stub
		return new HashSet<Cart>(repo.findAll());
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
