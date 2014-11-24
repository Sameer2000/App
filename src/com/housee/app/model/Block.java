package com.housee.app.model;

import java.io.Serializable;


public class Block implements Serializable{
	// Range[1-99]
	private int number = 0;
	//0-Not Opened,1-Opened
	private int status = 0;

	public Block(){
		this.number = 0;
		this.status = 0;
	}
	
	public Block(int number){
		this.number = number;
	}
	public Block(int number,int status){
		this.number = number;
		this.status = status;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
