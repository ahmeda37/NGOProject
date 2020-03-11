package com.ngo.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.Gift;
import com.ngo.repository.GiftRepository;

@Service("giftService")
public class GiftServiceImpl implements GiftService {

	@Autowired
	GiftRepository repo;
	
	@Override
	public Gift getGiftById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Set<Gift> getGifts() {
		// TODO Auto-generated method stub
		return new TreeSet<Gift>(repo.findAll());
	}

	@Override
	public void addGift(Gift g) {
		// TODO Auto-generated method stub
		repo.save(g);
	}

	@Override
	public void updateGift(Gift g) {
		// TODO Auto-generated method stub
		repo.save(g);
	}

	@Override
	public void deleteGift(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public void saveAll(Set<Gift> gifts) {
		// TODO Auto-generated method stub
		repo.saveAll(gifts);
	}

}
