package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngo.model.Cart;
import com.ngo.model.MyUser;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart getCartByUser(MyUser user);
}
