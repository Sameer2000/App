package com.housee.app.contants;

import java.text.SimpleDateFormat;

public class Constants {

	public static final int LOCK_TIME = 20;
	
	public static final String startTime = "20:55:00";

	public static final SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat gameStartTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat roomStartTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	public static final SimpleDateFormat utcSDFormat = new SimpleDateFormat("dd/MM/yy");
	
	public static final String SCHEDULE_DAILY = "Sunday,Monday,Tuesday,Wednesday,Thrusday,Friday,Saturday";
	
	public static final String PUBLIC_ROOM = "PUBLIC";
	
	public static final String PRIVATE_ROOM = "PRIVATE";
	
	
}
