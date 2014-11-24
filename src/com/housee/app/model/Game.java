package com.housee.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.IndexColumn;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameLevel;

@Entity
//@Index(members={"addresses.city,addresses.street"})
public class Game {

	@Id
	@GeneratedValue
	private long id;
	
	private String status;
	private String message;
	private String startTime;
	private Date scheduledOn;
	private int playerCount;
	private String timeZone;
	private boolean transStatus = false;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Room room;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="GAME_ID")
	private List<Player> players;

	@Column(length=555)
	private String openNumbers ;
	
	private String winningNumbers;
	
	private ArrayList<String> winner = new ArrayList<String>();
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="GAME_ID")
	private Set<Winner> winners = new HashSet<Winner>();

	private boolean firstRowStatus,secondRowStatus,thirdRowStatus;
	
	
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

	public Set<Winner> getWinners() {
		return winners;
	}

	public void setWinners(HashSet<Winner> winners) {
		this.winners = winners;
	}

	public void addWinners(Winner winner) {
		this.winners.add(winner);
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public Date getScheduledOn() {
		return scheduledOn;
	}

	public void setScheduledOn(Date scheduledOn) {
		this.scheduledOn = scheduledOn;
	}

	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	
	public double getWinningDistributionAmount(){
		double totalMoney = playerCount * room.getPrice();
		double serviceFee = (room.getLevel1().compareTo(GameLevel.TRIAL) == 0 ? SystemSettingConstants.TRIAL_GAME_SERVICE_FEE_PERCENT : SystemSettingConstants.PAYED_GAME_SERVICE_FEE_PERCENT);
		double distributionWinAmount = (totalMoney - (totalMoney * serviceFee / 100)) / 3;
		return distributionWinAmount;
	}
	
	public double getWinningServiceFeeAmount(){
		double totalMoney = playerCount * room.getPrice();
		double serviceFee = (room.getLevel1().compareTo(GameLevel.TRIAL) == 0 ? SystemSettingConstants.TRIAL_GAME_SERVICE_FEE_PERCENT : SystemSettingConstants.PAYED_GAME_SERVICE_FEE_PERCENT);
		double distributionWinAmount =  ((totalMoney * serviceFee / 100)/3);
		return distributionWinAmount;
	}
	
	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	@JsonIgnore
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.playerCount = players.size();
		this.players = players;
	}
	
	public List<Block> getOpenNumbers() {
		List<Block> openBlockNumbers = new ArrayList<Block>();
		if(openNumbers!=null){
			String[] splitOpenNumber = openNumbers.split(",");
			for(int index = 1 ; index < splitOpenNumber.length ; index++){
				if(winningNumbers!= null && winningNumbers.contains("#"+splitOpenNumber[index]+"#")){
					openBlockNumbers.add(new Block(Integer.valueOf(splitOpenNumber[index]),2));
				}else{
					openBlockNumbers.add(new Block(Integer.valueOf(splitOpenNumber[index])));	
				}
				
			}
		}
		return openBlockNumbers;
	}
	
	public void setOpenNumbers(String openNumbers) {
		this.openNumbers = openNumbers;
	}
	
	public void addOpenNumbers(int openNumber,CommonDaoImpl commonDao) {
		openNumbers = openNumbers + "," + openNumber ;
		for(Player player : players){
			if(player.isConfirmed()){
				Winner winner = player.updateOpenNumber(openNumber);
				if(winner != null){
					commonDao.createWinner(winner);
				}
			}
		}
	}

	public List<String> getWinner() {
		return winner;
	}
	
	public void setWinner(ArrayList<String> winner) {
		this.winner = winner;
	}
	
	public void addWinner(String winner) {
		this.winner.add(winner);
	}

	public boolean isFirstRowStatus() {
		return firstRowStatus;
	}
	
	public void setFirstRowStatus(boolean firstRowStatus) {
		this.firstRowStatus = firstRowStatus;
	}
	
	public boolean isSecondRowStatus() {
		return secondRowStatus;
	}
	
	public void setSecondRowStatus(boolean secondRowStatus) {
		this.secondRowStatus = secondRowStatus;
	}
	
	public boolean isThirdRowStatus() {
		return thirdRowStatus;
	}
	
	public void setThirdRowStatus(boolean thirdRowStatus) {
		this.thirdRowStatus = thirdRowStatus;
	}

	public String getWinningNumbers() {
		return winningNumbers;
	}

	public void setWinningNumbers(String winningNumbers) {
		this.winningNumbers = winningNumbers;
	}

	public void setStatus(int number,int status){
		winningNumbers = winningNumbers + "#" + number +"#";
//		for(Integer openNumber : openNumbers){
//			if(block.getNumber() == number){
//				block.setStatus(status);
//			}
//		}
	}

	public boolean isTransStatus() {
		return transStatus;
	}

	public void setTransStatus(boolean transStatus) {
		this.transStatus = transStatus;
	}
	
	

}
