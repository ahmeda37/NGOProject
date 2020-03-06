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

@Entity(name="gifts")
@Table(name="gifts")
public class Gift {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gift_id")
	private long giftId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="donation_type_id")
	@NotNull
	private DonationType donationType;
	
	@Column(name="gift_amount")
	@NotNull
	private float giftAmount;
	
	@Column(name="recurring")
	private boolean recurring;
	
	@Column(name="quantity")
	@NotNull
	private int quantity;
}
