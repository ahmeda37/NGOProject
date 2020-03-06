package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
