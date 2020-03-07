package com.ngo.service;

import java.util.Set;

import com.ngo.model.Gift;

public interface GiftService {
	public Gift getGiftById(long id);
	public Set<Gift> getGifts();
	public void addGift(Gift g);
	public void updateGift(Gift g);
	public void deleteGift(long id);
}
