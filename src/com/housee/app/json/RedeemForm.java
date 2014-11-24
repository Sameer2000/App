package com.housee.app.json;

public class RedeemForm {

	private double amount;
	private int redeemType;
	private int transStatus;
	private String message;
	private String transationId;
	private String payerId;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getRedeemType() {
		return redeemType;
	}
	public void setRedeemType(int redeemType) {
		this.redeemType = redeemType;
	}
	public int getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(int transStatus) {
		this.transStatus = transStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTransationId() {
		return transationId;
	}
	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

}
