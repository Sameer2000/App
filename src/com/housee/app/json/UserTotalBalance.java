package com.housee.app.json;

public class UserTotalBalance {

	
	private String username;
	
	private double totalAvailableBalance;
	
	private double totalRechargeAmount;
	
	private double totalRedeemAmount;
	
	private double totalWinAmount;
	
	private double feeAmount;
	
	private double totalPlayeGameAmount;
	 
	private double kylAmount;
	public UserTotalBalance(){}
	
	public UserTotalBalance(double totAvailBal){
		this.totalAvailableBalance = totAvailBal;
	}
	
	public UserTotalBalance(double totAvailBal,double totRechargeAmt,double totRedeemAmt,double totWinAmt,double feeAmt,double totPlayedAmt,double kylAmount){
		this.totalAvailableBalance = totAvailBal;
		this.totalRechargeAmount = totRechargeAmt;
		this.totalPlayeGameAmount = totPlayedAmt;
		this.totalRedeemAmount = totRedeemAmt;
		this.feeAmount = feeAmt;
		this.totalWinAmount = totWinAmt;
		this.kylAmount=kylAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotalAvailableBalance() {
		return totalAvailableBalance;
	}

	public void setTotalAvailableBalance(double totalAvailableBalance) {
		this.totalAvailableBalance = totalAvailableBalance;
	}

	public double getTotalRechargeAmount() {
		return totalRechargeAmount;
	}

	public void setTotalRechargeAmount(double totalRechargeAmount) {
		this.totalRechargeAmount = totalRechargeAmount;
	}

	public double getTotalRedeemAmount() {
		return totalRedeemAmount;
	}

	public void setTotalRedeemAmount(double totalRedeemAmount) {
		this.totalRedeemAmount = totalRedeemAmount;
	}

	public double getTotalWinAmount() {
		return totalWinAmount;
	}

	public void setTotalWinAmount(double totalWinAmount) {
		this.totalWinAmount = totalWinAmount;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public double getTotalPlayeGameAmount() {
		return totalPlayeGameAmount;
	}

	public void setTotalPlayeGameAmount(double totalPlayeGameAmount) {
		this.totalPlayeGameAmount = totalPlayeGameAmount;
	}

	public double getKylAmount() {
		return kylAmount;
	}

	public void setKylAmount(double kylAmount) {
		this.kylAmount = kylAmount;
	}
	
}
