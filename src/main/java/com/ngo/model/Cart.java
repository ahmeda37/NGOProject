package com.ngo.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="carts")
@Table(name="carts")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long cartId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@NotNull
	private User user;
	
	@OneToMany
	@JoinColumn(name="cart_id")
	@NotNull
	private Set<Gift> gifts;
	
	@Column(name="cart_date")
	@NotNull
	private Date date;
	
	@Column(name="cart_total")
	private float total;
	
	@Column(name="cart_processed")
	@NotNull
	private boolean processed;
}
