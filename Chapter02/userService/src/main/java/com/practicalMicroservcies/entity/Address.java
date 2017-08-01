package com.practicalMicroservcies.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	/**
	 * Serial Id for serialization
	 */
	private static final long serialVersionUID = 1245490181445280741L;
	/**
	 * unique Id for each record.
	 */

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * User Id , unique for every user
	 */

	@Column(name = "user_id", nullable = false, unique = true, length = 36)
	private String userId;

	/**
	 * City, Staying city of user
	 */
	@Column(name = "city", nullable = false, length = 25)
	private String city;

	/**
	 * Address Line 1 for User Address.
	 */
	@Column(name = "address_line_1", nullable = false,  length = 250)
	private String addressLine1;

	/**
	 * 
	 * Address Line 2 for User Address.
	 */
	@Column(name = "address_line_2", nullable = false,  length = 250)
	private String addressLine2;

	/**
	 * Pincode for user City
	 */
	@Column(name = "pincode", nullable = false,  length = 36)
	private String pinCode;

	/**
	 * Date on which user is created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", length = 0)
	private Date createdOn= new Date();

	/**
	 * Date on which user is deleted.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_on", columnDefinition = "TIMESTAMP DEFAULT NULL", length = 0)
	private Date deletedOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", city=" + city + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", PinCode=" + pinCode + ", createdOn=" + createdOn
				+ ", deletedOn=" + deletedOn + "]";
	}

}
