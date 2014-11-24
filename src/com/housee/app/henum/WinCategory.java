package com.housee.app.henum;

public enum WinCategory {

	FIRST_ROW_WINNER(1, "First Row"), SECOND_ROW_WINNER(2,"Second Row"), THIRD_ROW_WINNER(3, "Third Row");
	private int code;
	private String desc;
	
	WinCategory(int code, String desc){
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
	
	
	
}
