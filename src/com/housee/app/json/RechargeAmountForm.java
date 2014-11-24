package com.housee.app.json;

public class RechargeAmountForm {

	private String username;
	
	private double amount;
	
	private double tax;
	
	private String paymentgateway;
	
	private String currencycode;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getPaymentgateway() {
		return paymentgateway;
	}

	public void setPaymentgateway(String paymentgateway) {
		this.paymentgateway = paymentgateway;
	}
	
}
