package com.housee.app.henum;
/**
 * User Account Transaction Status 
 * @author inveera
 *
 */
public enum TransStatus {

	PENDING(0, "PENDING"), COMPLETED(1, "Completed"),Cancelled(2, "Cancelled");
	
	private int code;
	private String message;
	
	private TransStatus(int values,String message){
		this.code = values;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getValue(){
		return code;
	}
	
	public static TransStatus getTransStatus(int code){
		TransStatus[] values = TransStatus.values();
		for(TransStatus type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}	
	public static void main(String[] args) {
		System.out.println(TransStatus.PENDING.getValue());
	}
}
