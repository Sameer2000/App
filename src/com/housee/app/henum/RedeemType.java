package com.housee.app.henum;
/**
 * User Account Transaction Status 
 * @author inveera
 *
 */
public enum RedeemType {

	PARTIAL(0, "Partial"),FULL(1, "ALL");
	
	private int code;
	private String message;
	
	private RedeemType(int values,String message){
		this.code = values;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getValue(){
		return code;
	}
	
	public static RedeemType getRedeemType(int code){
		RedeemType[] values = RedeemType.values();
		for(RedeemType type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(RedeemType.PARTIAL.getValue());
	}
}
