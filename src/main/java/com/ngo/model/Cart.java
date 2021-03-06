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
public class Cart implements Comparable<Cart> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long cartId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private MyUser user;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Set<Gift> gifts;
	
	@Column(name="cart_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
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

	@Override
	public int compareTo(Cart c) {
		if (c.getCartId() == this.cartId) {
			return 0;
		} else if (c.getCartId() <= this.cartId) {
			return 1000;
		} else {
			return -1000;
		}
	}
	
	public String toString() {
		String result = "";
		result += "Cart ID: " + (Long)this.cartId != null ? this.cartId:"No ID" + "\n";
		result += "User: " + this.user != null ? this.user:"No User" + "\n";
		result += "Donations: ";
		if(gifts != null) {
			for(Gift g: this.gifts) {
				result += g;
			}
		}else {
			result += "No Donatons" + "\n";
		}
		result += "Date: " + this.date.toString() + "\n";
		result += "Total: " + (Float)this.total != null ? this.total:"No Total" + "\n";
		return result;
	}
	
}
