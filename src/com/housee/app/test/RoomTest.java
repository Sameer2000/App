package com.housee.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.json.RoomsList;
import com.housee.app.model.Room;
import com.housee.app.test.data.TestRoomData;

public class RoomTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/test/testApplicationContext.xml");
		CommonDaoImpl commonDao = (CommonDaoImpl)applicationContext.getBean("commonDao");
		RoomsList roomsList = TestRoomData.getInstance().getRoomList();
		for(Room room : roomsList.getRooms()){
			commonDao.createRoom(room);
		}
	}
}
