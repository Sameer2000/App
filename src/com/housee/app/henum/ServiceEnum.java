package com.housee.app.henum;

public enum ServiceEnum {

	USER(0, "USER"), ROOM(1, "USER"), CONTACTUS(2, "CONTACTUS"), KYL(3,"KYL"),GAMES(4,"GAMES");
	
	private int code;
	private String message;
	
	private ServiceEnum(int values,String message){
		this.code = values;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getValue(){
		return code;
	}
	
	public static ServiceEnum getTransStatus(int code){
		ServiceEnum[] values = ServiceEnum.values();
		for(ServiceEnum type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}	
	public static void main(String[] args) {
		System.out.println(ServiceEnum.CONTACTUS);
	}
}
