package com.ngo.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ngo.model.Address;
import com.ngo.model.MyUser;

public class UserForm {
		
	@NotEmpty(message="{firstName.notempty}")
	protected String firstName;
	
	@NotEmpty(message="{lastName.notempty}")
	protected String lastName;
	
	@NotEmpty(message="{email.error}")
	@Email
	protected String email;
	
	@NotNull
	protected boolean admin;
	
	@NotEmpty
	@Size(min=4,max=16,message="{password.error}")
	protected String password;
	
	@NotEmpty
	@Size(min=2,max=30, message="{address.error}")
	protected String address1;
	
	protected String address2;
	
	@NotEmpty
	@Size(min=2,max=30,message="{city.error}")
	protected String city;
	
	protected String state;
	
	@NotEmpty
	@Size(min=2,max=30,message="{country.error}")
	protected String country;
	
	protected String zip;
	
	protected String urbanization;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUrbanization() {
		return urbanization;
	}

	public void setUrbanization(String urbanization) {
		this.urbanization = urbanization;
	}
	
	public void setUserForm(MyUser u, Address a) {
		this.firstName=u.getFirstName();
		this.lastName=u.getLastName();
		this.email=u.getEmail();
		this.admin=u.getAdmin();
		this.address1=a.getAddress1();
		this.address2=a.getAddress2();
		this.city=a.getCity();
		this.state=a.getState();
		this.country=a.getCountry();
		this.zip=a.getZip();
		this.urbanization=a.getUrbanization();
	}
	
	public MyUser setUser() {
		Address a = new Address();
		a.setAddress1(this.address1);
		a.setAddress2(this.address2);
		a.setCity(this.city);
		a.setCountry(this.country);
		a.setZip(this.zip);
		a.setUrbanization(this.urbanization);

		MyUser u = new MyUser();
		u.setAddress(a);
		u.setAdmin(this.admin);
		u.setEmail(this.email);
		u.setFirstName(this.firstName);
		u.setLastName(this.lastName);
		u.setHashedPassword(this.password);
		return u;
	}
	public String toString() {
		return this.firstName + this.lastName + this.email + this.admin + this.address1 + this.address2 + this.city + this.state + this.country + this.urbanization + this.zip;
	}

}
