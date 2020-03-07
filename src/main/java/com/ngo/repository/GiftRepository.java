package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngo.model.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {

}
