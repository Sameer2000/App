package com.housee.app.henum;

public enum RoomCategory {

	Public_Room(1, "Public Room"), PRIVATE_ROOM(2,"Private Room");
	private int code;
	private String desc;
	
	RoomCategory(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static RoomCategory getCategory(int code){
		return (code == Public_Room.code ? Public_Room : PRIVATE_ROOM);
	}
}
