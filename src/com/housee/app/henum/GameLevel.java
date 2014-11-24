package com.housee.app.henum;

public enum GameLevel {
	TRIAL(0,"Trial Game"), PayedGame(1,"Payed Game");
	
	private int code;
	private String desc;
	
	GameLevel(int code,String desc){
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

	public static GameLevel getGameLevel(int code){
		return (code == TRIAL.code ? TRIAL : PayedGame );
	}
	
}
