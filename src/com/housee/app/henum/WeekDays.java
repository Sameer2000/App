package com.housee.app.henum;

public enum WeekDays {

	MONDAY("MONDAY"),TUESDAY("TUESDAY"),WEDNESDAY("WEDNESDAY"),THURSDAY("THURSDAY"),
		FRIDAY("FRIDAY"),SATURDAY("SATURDAY"),SUNDAY("SUNDAY");
	private String desc;
	
	WeekDays(String desc){
		this.desc = desc;
	}
	
	
	public String getMessage(){
		return desc;
	}
	
}
