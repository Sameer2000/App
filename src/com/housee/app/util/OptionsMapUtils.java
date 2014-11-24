package com.housee.app.util;

import java.util.LinkedHashMap;

import com.housee.app.henum.GameLevel;
import com.housee.app.henum.RoomCategory;
import com.housee.app.henum.TransStatus;
import com.housee.app.henum.WeekDays;

public class OptionsMapUtils {

	
	public static LinkedHashMap<String, String> getRedeemTransCategory(){
		LinkedHashMap<String, String> redeemTransCategoryMap = new LinkedHashMap<String, String>();
		for(TransStatus category : TransStatus.values()){
			redeemTransCategoryMap.put(String.valueOf(category.getValue()), category.getMessage());
		}
		return redeemTransCategoryMap;
	}

	public static LinkedHashMap<String, String> getRoomCategory(){
		LinkedHashMap<String, String> roomCategoryMap = new LinkedHashMap<String, String>();
		for(RoomCategory category : RoomCategory.values()){
			roomCategoryMap.put(String.valueOf(category.getCode()), category.getMessage());
		}
		return roomCategoryMap;
	}
	
	public static LinkedHashMap<String, String> getGameLevel(){
		LinkedHashMap<String, String> gameLevelMap = new LinkedHashMap<String, String>();
		for(GameLevel level : GameLevel.values()){
			gameLevelMap.put(String.valueOf(level.getCode()), level.getMessage());
		}
		return gameLevelMap;
	}
	
	public static LinkedHashMap<String, String> getHours(){
		LinkedHashMap<String, String> hoursMap = new LinkedHashMap<String, String>();
		for(int i = 0; i < 24; i++){
			hoursMap.put(String.valueOf(i), String.format("%02d", i));
		}
		return hoursMap;
	}
	
	public static LinkedHashMap<String, String> getMinutes(){
		LinkedHashMap<String, String> minutesMap = new LinkedHashMap<String, String>();
		for(int i = 0; i < 60; i++){
			minutesMap.put(String.valueOf(i), String.format("%02d", i));
		}
		return minutesMap;
	}
	
	public static LinkedHashMap<Long, String> getMaxAllowedUserCount(){
		LinkedHashMap<Long, String> allowedUserCountMap = new LinkedHashMap<Long, String>();
		allowedUserCountMap.put(20l, "20");
		allowedUserCountMap.put(30l, "30");
		allowedUserCountMap.put(40l, "40");
		allowedUserCountMap.put(50l, "50");
		return allowedUserCountMap;
	}
	
	public static LinkedHashMap<String, String> getAllowedCountryCode(){
		LinkedHashMap<String, String> allowCountryCodeMap = new LinkedHashMap<String, String>();
		allowCountryCodeMap.put("0", "Open for all");
		allowCountryCodeMap.put("1", "U.S.");
		allowCountryCodeMap.put("2", "U.K.");
		allowCountryCodeMap.put("3", "Asia");
		return allowCountryCodeMap;
	}
	
	public static LinkedHashMap<String, String> getAllWeekDays(){
		LinkedHashMap<String, String> weekDaysMap = new LinkedHashMap<String, String>();
		for(WeekDays weekDay : WeekDays.values()){
			weekDaysMap.put(weekDay.getMessage(), weekDay.getMessage());
		}
		return weekDaysMap;
	}
	
}                             	
