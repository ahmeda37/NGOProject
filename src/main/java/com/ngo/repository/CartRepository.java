package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngo.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
