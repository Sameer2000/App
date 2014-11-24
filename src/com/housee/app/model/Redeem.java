package com.housee.app.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.housee.app.henum.RedeemType;
import com.housee.app.henum.TransStatus;


@Entity
@Table(name="redeem")
public class Redeem {

	@Id
	@GeneratedValue
	private long id;
	
	private String payerId;
	private RedeemType redeemType;
	private String requestedTime;
	private String completedTime;
	private TransStatus transStatus;
	private String transationId;
	private String username;
	private String message;
	private double amount;
	private String timeZone;
	

	public String getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCompletedTime() {
		return completedTime;
	}
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}
	
	public TransStatus getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(TransStatus transStatus) {
		this.transStatus = transStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public RedeemType getRedeemType() {
		return redeemType;
	}

	public void setRedeemType(RedeemType redeemType) {
		this.redeemType = redeemType;
	}

	public String getTransationId() {
		return transationId;
	}

	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
