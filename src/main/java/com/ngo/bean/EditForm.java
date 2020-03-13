package com.ngo.bean;

import com.ngo.model.Address;
import com.ngo.model.MyUser;

public class EditForm extends UserForm{

	private long userId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setForm(MyUser u) {
		Address a = u.getAddress();
		this.address1=a.getAddress1();
		this.address2=a.getAddress2();
		this.city=a.getCity();
		this.state=a.getState();
		this.country=a.getCountry();
		this.zip=a.getZip();
		this.urbanization=a.getUrbanization();
		
		this.userId=u.getUserId();
		this.admin=u.getAdmin();
		this.email=u.getEmail();
		this.password=u.getHashedPassword();
		this.firstName=u.getFirstName();
		this.lastName=u.getLastName();
	}
	
	public MyUser getUser(MyUser u) {
		Address a = u.getAddress();
		a.setAddress1(this.address1);
		a.setAddress2(this.address2);
		a.setCity(this.city);
		a.setCountry(this.country);
		a.setZip(this.zip);
		a.setUrbanization(this.urbanization);

		u.setAddress(a);
		u.setAdmin(this.admin);
		u.setEmail(this.email);
		u.setFirstName(this.firstName);
		u.setLastName(this.lastName);
		u.setHashedPassword(this.password);
		return u;
	}
	public String toString() {
		return this.userId+this.firstName + this.lastName + this.email + this.admin + this.address1 + this.address2 + this.city + this.state + this.country + this.urbanization + this.zip;
	}
}
