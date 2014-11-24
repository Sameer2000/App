package com.housee.app.json;

import java.util.ArrayList;
import java.util.List;

import com.housee.app.model.Room;

public class RoomsList {

	private List<Room> rooms = new ArrayList<Room>();
	public List<Room> getRooms(){
		return rooms;
	}
	
	public void addRoom(Room room){
		rooms.add(room);
	}
	
	public void setRooms(List<Room> rooms){
		this.rooms = rooms;
	}
	
}
