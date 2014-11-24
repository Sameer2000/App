package com.housee.app.henum;

public enum KYLStatus {
	PENDING(0,"PENDING"), REVIEW(1,"REVIEW"), COMPLETED(2,"COMPLETED");
	
	private int code;
	private String desc;
	
	KYLStatus(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return desc;
	}
	
	
	public KYLStatus getKYLStatus(int code){
		KYLStatus[] values = KYLStatus.values();
		for(KYLStatus kylStatus : values){
			if(kylStatus.code == code){
				return kylStatus;
			}
		}
		return null;
	}

	public static void main(String[] args){
	
		
	}
}


