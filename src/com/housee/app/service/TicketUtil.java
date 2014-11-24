package com.housee.app.service;

import java.util.Random;

import com.housee.app.model.Block;


public class TicketUtil {

	private Block[][] ticket = null;
	private int row1Count = 0;
	private int row2Count = 0;
	private int row3Count = 0;
	
	
	public void createTicket(){
		initTicket();
		// Example assumes these variables have been initialized
		// above, e.g. as method parameters, fields, or otherwise
		// such as: rand = new Random();
		Random rand = new Random();
		int min = 1, max = 99;

		do{
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = rand.nextInt(max - min + 1) + min;
			if(randomNum>0 && randomNum<100){
				addNum(randomNum);
			}

		}while(getTotalFilledCount()<=14);
		printTicket();
	}
	
	public Block[][] getTicket(){
		return ticket;
	}
	
	public void initTicket(){
		ticket = new Block[3][10];
		row1Count = 0;
		row2Count = 0;
		row3Count = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<10;j++){
				ticket[i][j] = new Block();
			}
		}
	}

	public void printTicket(){
		for(int i=0;i<3;i++){
			for(int j=0;j<10;j++){
				if(ticket[i][j].getNumber() == 0){
					System.out.print("   X   ");
				}else{
					System.out.print("   " + ticket[i][j].getNumber() + "   ");
				}
			}
			System.out.println("");
		}
		System.out.println( row1Count+  " , " + row2Count + " , " + row3Count);
	}
	
	public int getTotalFilledCount(){
		return row1Count+row2Count+row3Count;
	}
	public void addNum(int number){
		int rowNum = 0;
		int colNum = number / 10;
		int decimalValue = number % 10;
		
		int unfilledColumnCount = 0;
		if(ticket[0][colNum].getNumber()==0){
			unfilledColumnCount++;
		}
		if(ticket[1][colNum].getNumber()==0){
			unfilledColumnCount++;
		}
		if(ticket[2][colNum].getNumber()==0){
			unfilledColumnCount++;
		}
		if(unfilledColumnCount >= 2){
			if(decimalValue>=0 && decimalValue<=5){
				if(row1Count < 5 && ticket[0][colNum].getNumber() == 0 && (ticket[1][colNum].getNumber() == 0 || number < ticket[1][colNum].getNumber()) && (ticket[2][colNum].getNumber() == 0 || number < ticket[2][colNum].getNumber()) ){
					ticket[0][colNum].setNumber(number);
					row1Count++;
					System.out.println("NUM::"+number+" ,ROW1COUNT::"+row1Count);
					return;
				}else if(row2Count < 5 && ticket[1][colNum].getNumber() == 0 && (ticket[0][colNum].getNumber() == 0 || number > ticket[0][colNum].getNumber())){
					ticket[1][colNum].setNumber(number);
					row2Count++;
					System.out.println("NUM::"+number+" ,ROW2COUNT::"+row2Count);
					return;
				}
			}
			
			if(decimalValue>=4 && decimalValue<=8){
				if(row2Count < 5 && ticket[1][colNum].getNumber() == 0 && (ticket[2][colNum].getNumber() == 0 || number < ticket[2][colNum].getNumber()) && (ticket[0][colNum].getNumber() == 0 || number > ticket[0][colNum].getNumber()) ){
					ticket[1][colNum].setNumber(number);
					row2Count++;
					System.out.println("NUM::"+number+" ,ROW2COUNT::"+row2Count);
					return;
				}else if(row3Count < 5 && ticket[2][colNum].getNumber() == 0 && (ticket[1][colNum].getNumber() == 0 || number > ticket[1][colNum].getNumber())  && (ticket[0][colNum].getNumber() == 0 || number > ticket[0][colNum].getNumber()) ){
					ticket[2][colNum].setNumber(number);
					row3Count++;
					System.out.println("NUM::"+number+" ,ROW3COUNT::"+row3Count);
					return;
				}
			}
			if(row3Count < 5 && decimalValue>=7 && decimalValue<=9 && ticket[2][colNum].getNumber() == 0 && (ticket[1][colNum].getNumber() == 0 || number > ticket[1][colNum].getNumber()) && (ticket[0][colNum].getNumber() == 0 || number > ticket[0][colNum].getNumber()) ){
				ticket[2][colNum].setNumber(number);
				row3Count++;
				System.out.println("NUM::"+number+" ,ROW3COUNT::"+row3Count);
				return;
			}
			
		}
	}
	

	
}
