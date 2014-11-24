package com.housee.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FavourateTicket {

	@Id
	@GeneratedValue
	private long id;
	
	private int[][] ticketNumber = new int[3][10];
	private int[][] ticketNumberStatus = new int[3][10];
	
	 private int row1FillCount = 0;
	private int row2FillCount = 0;
	private int row3FillCount = 0;
	
	
	public void setTicket(Block[][] ticket){
		for(int i=0; i < 3 ;i++){
			for (int j = 0; j < 10 ; j++){
				ticketNumber[i][j] = ticket[i][j].getNumber();
				ticketNumberStatus[i][j] = ticket[i][j].getStatus();
				System.out.println("NUM ::" + ticketNumber[i][j] + " , OTN :: " + ticket[i][j].getNumber() + ", OTNS :: " + ticket[i][j].getStatus());
			}
		}
	}
	
	public Block[][] getTicket(){
		Block[][] ticket = new Block[3][10];
		for(int i=0; i < 3 ;i++){
			for (int j = 0; j < 10 ; j++){
				ticket[i][j] = new Block();
				ticket[i][j].setNumber(ticketNumber[i][j]);
				ticket[i][j].setStatus(ticketNumberStatus[i][j]);
			}
		}
		return ticket;
	}

	public void updateOpenNumber(int openNumber){
		for(int i = 0; i < 3 ; i++){
			for(int j = 0; j < 10;j++){
				if(ticketNumber[i][j] == openNumber){
					ticketNumberStatus[i][j] = 1;
					if(i == 0)
						row1FillCount++;
					if(i == 1)
						row2FillCount++;
					if(i == 2)
						row3FillCount++;
				}
			}
		}
	}
	

	
	public int getRow1FillCount() {
		return row1FillCount;
	}

	public void setRow1FillCount(int row1FillCount) {
		this.row1FillCount = row1FillCount;
	}

	public int getRow2FillCount() {
		return row2FillCount;
	}

	public void setRow2FillCount(int row2FillCount) {
		this.row2FillCount = row2FillCount;
	}

	public int getRow3FillCount() {
		return row3FillCount;
	}

	public void setRow3FillCount(int row3FillCount) {
		this.row3FillCount = row3FillCount;
	}
	
	
}
