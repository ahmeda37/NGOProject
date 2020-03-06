package com.ngo.service;

import java.util.Set;

import com.ngo.model.Cart;

public interface CartService {
	public Cart getCartById(long id);
	public Set<Cart> getCarts();
	public void addCart(Cart c);
	public void updateCart(Cart c);
	public void deleteCart(long id);
}
