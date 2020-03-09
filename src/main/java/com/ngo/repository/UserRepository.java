package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngo.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long>{
	MyUser findByEmail(String email);
}
