package com.ngo.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.ngo.service.CartService;
import com.ngo.service.GiftService;

public class ControllerBeans {

	
	@Autowired
	CartService cartService;
	
	@Autowired
	GiftService giftService;
}
