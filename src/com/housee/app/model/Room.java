package com.housee.app.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.housee.app.henum.GameLevel;
import com.housee.app.henum.RoomCategory;

@Entity
@Table(name="Room", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name") })
public class Room {

	@Id
    @GeneratedValue
	private long id;

	private String name;
	private String description;
	private double price;
	private ArrayList<String> scheduleOn;
	private ArrayList<String> startTime = new ArrayList<String>();
	
	private long allowUserCount;
	
	private RoomCategory category;
	
	private String password;
	
	private boolean active;
	
	private String country;
	
	private String countryCode;
	
	private String keywork;
	
	private GameLevel level1;
	
	private double winningChargeInPercentage;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public ArrayList<String> getScheduleOn() {
		return scheduleOn;
	}

	public void setScheduleOn(ArrayList<String> scheduleOn) {
		this.scheduleOn = scheduleOn;
	}
	
	public ArrayList<String> getStartTime() {
		return startTime;
	}
	
	public void setStartTime(ArrayList<String> startTime) {
		this.startTime = startTime;
	}

	public long getAllowUserCount() {
		return allowUserCount;
	}

	public void setAllowUserCount(long allowUserCount) {
		this.allowUserCount = allowUserCount;
	}
	
	public RoomCategory getCategory() {
		return category;
	}
	
	public void setCategory(RoomCategory category) {
		this.category = category;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getKeywork() {
		return keywork;
	}
	
	public void setKeywork(String keywork) {
		this.keywork = keywork;
	}
	
	public GameLevel getLevel1() {
		return level1;
	}

	public void setLevel1(GameLevel level1) {
		this.level1 = level1;
	}
	public double getWinningChargeInPercentage() {
		return winningChargeInPercentage;
	}
	public void setWinningChargeInPercentage(double winningChargeInPercentage) {
		this.winningChargeInPercentage = winningChargeInPercentage;
	}
	
	
}
