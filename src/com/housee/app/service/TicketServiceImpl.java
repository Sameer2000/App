package com.housee.app.service;

import com.housee.app.model.Player;
import com.housee.app.model.Room;
import com.housee.app.model.Ticket;
import com.housee.app.test.data.TestRoomData;

public class TicketServiceImpl {

	private TicketUtil ticketUtil; 
	private Ticket ticket;
	private Room room;
	
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Ticket getTicket(){
		return ticket;
	}

	public TicketUtil getTicketUtil() {
		return ticketUtil;
	}

	public void setTicketUtil(TicketUtil ticketUtil) {
		this.ticketUtil = ticketUtil;
	}

	public void createTicket(){
		ticketUtil.createTicket();
		ticket.setTicket(ticketUtil.getTicket());
	}
	
	public void printTicket(){
		for(int i=0;i<3;i++){
			for(int j=0;j<10;j++){
				if(ticket.getTicket()[i][j].getNumber() == 0){
					System.out.print("   X   ");
				}else{
					System.out.print("   " + ticket.getTicket()[i][j].getNumber() + "   ");
				}
			}
			System.out.println("");
		}
	}
	
	
	public void addPlayerToRoomGame(String roomId,String deviceId,Ticket ticket){
		room = TestRoomData.getInstance().getRoom(Long.valueOf(roomId));
		GameFactory.getInstance().addPlayerToRoomGame(room,deviceId, ticket);
	}
	
	public void confirmPlayerToRoomGame(String roomId,String deviceId){
		room = TestRoomData.getInstance().getRoom(Long.valueOf(roomId));
		GameFactory.getInstance().confirmPlayerToRoomGame(room,deviceId);
	}
	
	public Player getPlayerToRoomGame(String roomId,String deviceId){
		room = TestRoomData.getInstance().getRoom(Long.valueOf(roomId));
		return GameFactory.getInstance().getPlayerToRoomGame(room,deviceId);
	}

}
