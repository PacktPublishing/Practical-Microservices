package com.practicalMircorservices.eventProducer;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class CrispyBunOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6572547218488352566L;

	private UUID orderId;
	private Integer itemId;
	private Date orderPlacedTime;
	private String userName;
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Date getOrderPlacedTime() {
		return orderPlacedTime;
	}
	public void setOrderPlacedTime(Date orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
