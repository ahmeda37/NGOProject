package com.ngo.model;

import java.util.Date;
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
	
	@Column(name="cart_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@NotNull
	private Date date;
	
	@Column(name="cart_total")
	private float total;
	
	@Column(name="cart_processed")
	@NotNull
	private boolean processed;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(Set<Gift> gifts) {
		this.gifts = gifts;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	//compareTo
	
}
