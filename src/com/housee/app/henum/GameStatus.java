package com.housee.app.henum;

public enum GameStatus {

	NOT_STARTED(0,"Not Started"),LOCKED(3,"LOCKED"),READYTOSTART(5,"READY TO START"), STARTED(7,"Started"), COMPLETED(10,"Completed"), ERROR(17,"ERROR"), SUSPENDED(19,"Not Sufficient Player"), RESTARTED(21,"Game Restarted"); 
	
	private int values;
	private String message;
	
	private GameStatus(int values,String message){
		this.values = values;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getValue(){
		return values;
	}
	
	
	public static void main(String[] args) {
		System.out.println(GameStatus.LOCKED.getValue());
	}
}
