package com.housee.app.service;

import java.util.Hashtable;
import java.util.Random;

import com.housee.app.henum.GameStatus;
import com.housee.app.model.Game;
import com.housee.app.model.Room;

public class GameRunner implements Runnable{

	private Room room;
	private Hashtable<Integer,Integer> openNumber = new Hashtable<Integer,Integer>();
	
	public GameRunner(Room room){
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void run(){
		Game game = GameFactory.getInstance().getGame(room);
//		game.getRoom().setGameStatus(GameStatus.STARTED.getValue());
//		game.getRoom().setMessage(GameStatus.STARTED.getMessage());
		Random rand = new Random();
		int min = 1, max = 99;

		do{
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = rand.nextInt(max - min + 1) + min;
			if(!openNumber.contains(randomNum)){
				openNumber.put(randomNum, randomNum);
//				game.addOpenNumbers(randomNum);
				try {
					Thread.sleep(1000*2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}while(game.getWinner().size()<3);
//		game.getRoom().setGameStatus(GameStatus.COMPLETED.getValue());
//		game.getRoom().setMessage(GameStatus.COMPLETED.getMessage());

	}
}
