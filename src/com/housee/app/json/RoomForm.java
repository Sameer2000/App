package com.housee.app.json;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.housee.app.contants.SystemSettingConstants;

public class RoomForm {
	
	@Size(min=2, max=30) 
	@NotNull(message="Please enter Room Name")
	private String name;
	
	@NotNull
	private String price;
	
	@NotNull
	private Integer level1;
	
	@NotNull
	private Integer category;
	
	private Integer allowCountryCode;
	
	private double winningChargeInPercentage=SystemSettingConstants.ROOM_CHARGES;
	
	@NotNull
	private Integer maxUserCount;
	
	@NotEmpty
	private ArrayList<String> startTime;
	
	@NotNull
	private Integer scheduleType;
	
	@NotEmpty
	private ArrayList<String> scheduleOn;
	
	private boolean active = false;
	
	private String HH;
	
	private String MM;
	
	public String getHH() {
		return HH;
	}

	public void setHH(String hH) {
		HH = hH;
	}

	public String getMM() {
		return MM;
	}

	public void setMM(String mM) {
		MM = mM;
	}

	public Integer getScheduleType() {
		return scheduleType;
	}
	
	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getLevel1() {
		return level1;
	}
	public void setLevel1(Integer level1) {
		this.level1 = level1;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getAllowCountryCode() {
		return allowCountryCode;
	}
	public void setAllowCountryCode(int allowCountryCode) {
		this.allowCountryCode = allowCountryCode;
	}
	public Integer getMaxUserCount() {
		return maxUserCount;
	}
	public void setMaxUserCount(Integer maxUserCount) {
		this.maxUserCount = maxUserCount;
	}
	public ArrayList<String> getStartTime() {
		return startTime;
	}
	public void setStartTime(ArrayList<String> startTime) {
		this.startTime = startTime;
	}
	public ArrayList<String> getScheduleOn() {
		return scheduleOn;
	}
	public void setScheduleOn(ArrayList<String> scheduleOn) {
		this.scheduleOn = scheduleOn;
	}

	public double getWinningChargeInPercentage() {
		return winningChargeInPercentage;
	}

	public void setWinningChargeInPercentage(double winningChargeInPercentage) {
		this.winningChargeInPercentage = winningChargeInPercentage;
	}
	
	
	
}
