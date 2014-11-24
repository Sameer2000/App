package com.housee.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.json.RoomsList;
import com.housee.app.json.Root;
import com.housee.app.model.Room;
import com.housee.app.test.data.TestRoomData;

public class RoomServiceImpl {

	@Autowired
	private CommonDaoImpl commonDao;
	
	public RoomsList getRoomsList(Root root){
		RoomsList roomsList = new RoomsList();
		roomsList.setRooms(commonDao.getAllActiveRoomsByLevel(root.getSession().getLevel1()));
		return roomsList;
	}
	
	public Room getRoom(long roomId){
		return commonDao.getRoomById(roomId);
	}

	
	public void createRoom(){
		RoomsList roomsList = TestRoomData.getInstance().getRoomList();
		for(Room room : roomsList.getRooms()){
			commonDao.createRoom(room);
		}
	}
	
}
