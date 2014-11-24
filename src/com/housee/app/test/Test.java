package com.housee.app.test;

import java.util.Date;
import java.util.TimeZone;

import com.housee.app.contants.Constants;

public class Test {

	public static void main(String[] args) {
		int val = 1;
		System.out.println(1%10);
		System.out.println(1/10);
		//TicketServiceImpl ticket = new TicketServiceImpl();
		//ticket.createTicket();
		//ticket.printTicket();
		Constants.roomStartTimeSDF.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println(Constants.roomStartTimeSDF.format(new Date()));
		
//		System.out.println();
		
	}
}
