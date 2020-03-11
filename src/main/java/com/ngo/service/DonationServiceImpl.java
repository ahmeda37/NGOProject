package com.ngo.service;

import java.util.TreeSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.DonationType;
import com.ngo.repository.DonationRepository;

@Service("donationType")
public class DonationServiceImpl implements DonationService {

	@Autowired
	DonationRepository repo;
	
	@Override
	public Set<DonationType> getDonationTypes() {
		// TODO Auto-generated method stub
		return new TreeSet<DonationType>(repo.findAll());
	}

	@Override
	public DonationType getDonationTypeById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	
}
