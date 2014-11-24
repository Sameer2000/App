package com.housee.app.henum;

public enum UserType {
	ADMIN(0,"Admin"), SUPER_ADMIN(2,"Super Admin"),SUPER_USER(5,"Super User"), USER(7,"User"), DUMMY(10,"DUMMY");
	
	private int code;
	private String desc;
	
	UserType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return desc;
	}
	
	
	public static UserType getAdminType(int code){
		UserType[] values = UserType.values();
		for(UserType admintype : values){
			if(admintype.code == code){
				return admintype;
			}
		}
		return null;
	}

	public static void main(String[] args){
	
		
	}
}


