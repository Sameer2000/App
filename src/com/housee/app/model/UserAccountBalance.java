package com.housee.app.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserAccountBalance")
public class UserAccountBalance {

	@Id
    @GeneratedValue
	private long id;
	
	private double totalAvailableBalance;
	
	private double rechargeAmount;
	
	private double redeemAmount;
	
	private double playedGameAmount;
	
	private double winAmount;
	
	private double winFeeAmount;
	
	private double kylAmount;
	
	private double serviceFeeAmount;
	
	private String timestamp;
	
	private long gameId;
	
	private String username;
	
	private Timestamp lastUpdatedTime; 
	
	private String timeZone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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

	public double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public double getRedeemAmount() {
		return redeemAmount;
	}

	public void setRedeemAmount(double redeemAmount) {
		this.redeemAmount = redeemAmount;
	}

	public double getPlayedGameAmount() {
		return playedGameAmount;
	}

	public void setPlayedGameAmount(double playedGameAmount) {
		this.playedGameAmount = playedGameAmount;
	}

	public double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(double winAmount) {
		this.winAmount = winAmount;
	}

	public double getServiceFeeAmount() {
		return serviceFeeAmount;
	}

	public void setServiceFeeAmount(double serviceFeeAmount) {
		this.serviceFeeAmount = serviceFeeAmount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public double getKylAmount() {
		return kylAmount;
	}

	public void setKylAmount(double kylAmount) {
		this.kylAmount = kylAmount;
	}

	public double getWinFeeAmount() {
		return winFeeAmount;
	}

	public void setWinFeeAmount(double winFeeAmount) {
		this.winFeeAmount = winFeeAmount;
	}
	
}
