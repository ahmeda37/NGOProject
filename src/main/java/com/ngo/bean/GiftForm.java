package com.ngo.bean;

import java.util.ArrayList;
import java.util.Set;

import com.ngo.model.DonationType;
import com.ngo.model.Gift;

public class GiftForm {
	
	private ArrayList<Gift> giftList;

	public ArrayList<Gift> getGiftList() {
		return giftList;
	}
	public void setGiftList(ArrayList<Gift> giftList) {
		this.giftList = giftList;
	}
	
	public void setDonationTypes(Set<DonationType> dt) {
		this.giftList = new ArrayList<Gift>();
		Gift g;
		for(DonationType d : dt) {
			g = new Gift();
			g.setDonationType(d);
			giftList.add(g);
		}
	}
}
