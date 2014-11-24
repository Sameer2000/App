package com.housee.app.test.data;

import com.housee.app.contants.Constants;
import com.housee.app.henum.GameLevel;
import com.housee.app.henum.GameStatus;
import com.housee.app.henum.RoomCategory;
import com.housee.app.json.RoomsList;
import com.housee.app.model.Room;

public class TestRoomData {
	
	private static TestRoomData testData = null;
	private static RoomsList rooms = new RoomsList(); 
	private TestRoomData(){
		Room room = new Room();
		room.setName("Room-$5");
		room.setPrice(5.00);
		room.getStartTime().add(Constants.startTime);
		room.setCategory(RoomCategory.Public_Room);
		room.setActive(true);
		room.setKeywork("test");
		room.setAllowUserCount(50);
		room.setCountryCode("999");
		room.setLevel1(GameLevel.TRIAL);
//		room.setScheduleOn(Constants.SCHEDULE_DAILY);
		rooms.addRoom(room);
	}
	
	public static TestRoomData getInstance(){
		if(testData == null){
			testData = new TestRoomData();
		}
		return testData;
	}

	public RoomsList getRoomList(){
		return rooms;
	}
	
	public void updateUserCount(long roomId){
		RoomsList roomsList = getRoomList();
		for(Room room : roomsList.getRooms()){
			if(room.getId() == roomId){
//				room.setUserCount(room.getUserCount()+1);
			}
		}
	}
	
	public Room getRoom(long roomId){
		RoomsList roomsList = getRoomList();
		for(Room room : roomsList.getRooms()){
			if(room.getId() == roomId){
				return room;
			}
		}
		return null;
	}
	
	public void updateRoomStatus(GameStatus gameStatus){
		
	}
	
}
