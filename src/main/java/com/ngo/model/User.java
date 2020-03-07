package com.ngo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "users")
@Table(name = "users")
public class User implements Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "first_name")
	@NotNull
	private String firstName;

	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Column(name = "email")
	@NotNull
	private String email;

	@Column(name = "admin")
	@NotNull
	private boolean admin;

	@Column(name = "hashed_password")
	@NotNull
	private String hashedPassword;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Override
	public int compareTo(User u) {
		// TODO Auto-generated method stub
		if (u.getUserId() == this.userId) {
			return 0;
		} else if (u.getUserId() <= this.userId) {
			return 1000;
		} else {
			return -1000;
		}
	}

	@Override
	public String toString() {
		String result = "";
		result += this.firstName + " " + this.lastName;
		if(this.admin) {result += " (ADMIN)\n";}
		else {result += " (USER)\n";}
		result += this.address + "\n";
		result += this.hashedPassword;
		return result;
	}
}
