package com.ngo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="addresses")
@Table(name="addresses")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private long id;
	
	@Column(name="address_1")
	@NotNull
	private String address1;
	
	@Column(name="address_2")
	private String address2;
	
	@Column(name="city")
	@NotNull
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="country")
	@NotNull
	private String country;
	
	@Column(name="urbanization")
	private String urbanization;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUrbanization() {
		return urbanization;
	}

	public void setUrbanization(String urbanization) {
		this.urbanization = urbanization;
	}
	
	@Override
	public boolean equals(Object o) {
		try {
			Address a2 = (Address)o;
			if(a2.getId() == this.id) {
				return true;
			} else {
				return false;
			}
		}catch(ClassCastException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	@Override
	public String toString() {
		String result = "";
		result += this.address1 + "\n";
		if(this.address2 != null) {result += this.address2 + "\n";}
		result += this.city + ", ";
		if(this.state != null) {result += this.state + " ";}
		result += this.country + "\n";
		if(this.zip != null) {result += this.zip + " ";}
		if(this.urbanization != null) {result += this.urbanization + " ";}
		return result;
	}
	
	
}
