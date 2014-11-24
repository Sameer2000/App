package com.housee.app.henum;

public enum KYLType {
	KYL(0,"Know Your Luck"), OTHER(1,"Other");
	
	private int code;
	private String desc;
	
	KYLType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return desc;
	}
	
	
	public static KYLType getKYLType(int code){
		KYLType[] values = KYLType.values();
		for(KYLType kyltype : values){
			if(kyltype.code == code){
				return kyltype;
			}
		}
		return null;
	}

	public static void main(String[] args){
	
		
	}
}


