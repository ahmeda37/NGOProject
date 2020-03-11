package com.ngo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="donation_types")
@Table(name="donation_types")
public class DonationType implements Comparable<DonationType>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="donation_type_id")
	private long donationTypeId;
	
	@Column(name="type_name")
	@NotNull
	private String typeName;
	
	@Column(name="has_recurring_option")
	@NotNull
	private boolean hasRecurringOption;

	public long getDonationTypeId() {
		return donationTypeId;
	}

	public void setDonationTypeId(long donationTypeId) {
		this.donationTypeId = donationTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isHasRecurringOption() {
		return hasRecurringOption;
	}

	public void setHasRecurringOption(boolean hasRecurringOption) {
		this.hasRecurringOption = hasRecurringOption;
	}
	
	@Override
	public int compareTo(DonationType d) {
		if (d.getDonationTypeId() == this.donationTypeId) {
			return 0;
		} else if (d.getDonationTypeId() <= this.donationTypeId) {
			return 1000;
		} else {
			return -1000;
		}
	}
	
	public String toString() {
		String result = "";
		result += this.typeName + "\n";
		return result;
	}
}
