package com.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ngo.model.DonationType;
public interface DonationRepository extends JpaRepository<DonationType, Long> {

}
