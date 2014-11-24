package com.housee.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.CollectionOfElements;

import com.housee.app.henum.TransStatus;
import com.housee.app.henum.WinCategory;
import com.housee.app.util.NumberFormatUtil;


@Entity
public class Player {

	@Id
	@GeneratedValue
	private long id;
	
	 @OneToOne(cascade = CascadeType.ALL)
	private UserProfile user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket confirmTicket;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
	private NewTicket newTicket;
	
	private boolean confirmed;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Game game;
	
	private long winCount;
	
	private double winAmount;
	
	public double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(double winAmount) {
		this.winAmount = winAmount;
	}

	public long getWinCount() {
		return winCount;
	}

	public void setWinCount(long winCount) {
		this.winCount = winCount;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public UserProfile getUser() {
		return user;
	}
	
	public void setUser(UserProfile user) {
		this.user = user;
	}
	
	public Ticket getConfirmTicket() {
		return confirmTicket;
	}
	
	public void setConfirmTicket(Ticket ticket) {
		this.confirmTicket = ticket;
	}
	
	public NewTicket getNewTicket() {
		return newTicket;
	}

	public void setNewTicket(Ticket newTicket) {
		if(this.newTicket == null){
			this.newTicket = new NewTicket();
		}
		this.newTicket.setTicket(newTicket.getTicket());
	}

	public void removeNewTicket() {
		this.newTicket = null;
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	
	public void setConfirmed(boolean confirmed) {
		if(!this.confirmed && confirmed){
			game.setPlayerCount(game.getPlayerCount()+1); 
		}
		this.confirmed = confirmed;
	}
	
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Winner updateOpenNumber(int openNumber){
		confirmTicket.updateOpenNumber(openNumber);
		if(confirmTicket.getRow1FillCount()>=5 && !game.isFirstRowStatus() ){

			
			Winner winner = new Winner();
			winner.setRowWinner(WinCategory.FIRST_ROW_WINNER);
			winner.setPlayerName(user.getName());
			
			winner.setWinAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningDistributionAmount()));
			winner.setWinFeeAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningServiceFeeAmount()));
			winner.setUsername(user.getUsername());
			winner.setTransStatus(TransStatus.PENDING);
			winner.setGame(game);
//			game.getWinners().add(winner);
			game.addWinner(user.getName()+", First Row Winner.");
			game.setStatus(openNumber, 2);
			game.setFirstRowStatus(true);
			winCount++;
			winAmount+=winner.getWinAmount();
			return winner;
		}
		if(confirmTicket.getRow2FillCount()>=5 && !game.isSecondRowStatus() ){
			Winner winner = new Winner();
			winner.setRowWinner(WinCategory.SECOND_ROW_WINNER);
			winner.setPlayerName(user.getName());
			winner.setWinAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningDistributionAmount()));
			winner.setWinFeeAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningServiceFeeAmount()));
			winner.setUsername(user.getUsername());
			winner.setTransStatus(TransStatus.PENDING);
			winner.setGame(game);
//			game.getWinners().add(winner);
			game.addWinner(user.getName()+", Second Row Winner.");
			game.setStatus(openNumber, 2);
			game.setSecondRowStatus(true);
			winCount++;
			winAmount+=winner.getWinAmount();
			return winner;
		}
		if(confirmTicket.getRow3FillCount()>=5 && !game.isThirdRowStatus() ){
			Winner winner = new Winner();
			winner.setRowWinner(WinCategory.THIRD_ROW_WINNER);
			winner.setPlayerName(user.getName());
			winner.setWinAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningDistributionAmount()));
			winner.setWinFeeAmount(NumberFormatUtil.twoDecimalDigits(game.getWinningServiceFeeAmount()));
			winner.setUsername(user.getUsername());
			winner.setTransStatus(TransStatus.PENDING);
			winner.setGame(game);
//			game.getWinners().add(winner);
			game.addWinner(user.getName()+", Third Row Winner.");
			game.setStatus(openNumber, 2);
			game.setThirdRowStatus(true);
			winCount++;
			winAmount+=winner.getWinAmount();
			return winner;
		}
		return null;
	}
	
}
