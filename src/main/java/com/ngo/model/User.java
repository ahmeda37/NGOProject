package com.ngo.model;

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

@Entity(name="users")
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	@NotNull
	private Address address;
	
	@Column(name="first_name")
	@NotNull
	private String firstName;
	
	@Column(name="last_name")
	@NotNull
	private String last_name;
	
	@Column(name="email")
	@NotNull
	private String email;
	
	@Column(name="admin")
	@NotNull
	private boolean isAdmin;
	
	@Column(name="hashed_password")
	@NotNull
	private String hashedPassword;
	
	
	
	
}
