package com.housee.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.housee.app.henum.TransStatus;
import com.housee.app.henum.WinCategory;

@Entity
public class Winner implements Serializable{

	@Id
	@GeneratedValue
	private long id;
	
	private WinCategory rowWinner;
	
	private double winAmount;
	
	private double winFeeAmount;
	
	private String playerName;

	private String username;
	
	private TransStatus transStatus;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Game game;

	@JsonIgnore
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRowWinner() {
		return rowWinner.getMessage();
	}

	public void setRowWinner(WinCategory rowWinner) {
		this.rowWinner = rowWinner;
	}

	public double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(double winAmount) {
		this.winAmount = winAmount;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TransStatus getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(TransStatus transStatus) {
		this.transStatus = transStatus;
	}

	public double getWinFeeAmount() {
		return winFeeAmount;
	}

	public void setWinFeeAmount(double winFeeAmount) {
		this.winFeeAmount = winFeeAmount;
	}

}
