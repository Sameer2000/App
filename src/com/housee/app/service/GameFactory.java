package com.housee.app.service;

import java.util.Hashtable;

import com.housee.app.model.Game;
import com.housee.app.model.Player;
import com.housee.app.model.Room;
import com.housee.app.model.Ticket;


public class GameFactory {

	private static GameFactory gameFactory = null;
	private static Hashtable<Room,Game> gameSession = new Hashtable<Room,Game>();
	private GameFactory(){
		
	}
	
	public static GameFactory getInstance(){
		if(gameFactory == null){
			gameFactory = new GameFactory();
		}
		return gameFactory;
	}
	
	
	public void addGame(Room room,Game game){
		if(gameSession.get(room)  == null)
			gameSession.put(room, new Game());
	}
	
	public Room getRoom(long roomId){
		for(Room room : gameSession.keySet()){
			if(room.getId() == roomId){
				return room;
			}
		}
		return null;
	}
	
	public Game getGame(Room room){
		if(gameSession.get(room)  == null){
			Game game = new Game();
			game.setRoom(room);
			gameSession.put(room, game);
		}
		return gameSession.get(room);
	}
	
	public void addPlayerToRoomGame(Room room,String deviceId,Ticket ticket){
		Game game = getGame(room);
//		game.createPlayer(deviceId,ticket);
	}
	
	public void confirmPlayerToRoomGame(Room room,String deviceId){
		Game game = getGame(room);
//		game.confirmPlayer(deviceId);
	}
	
	public Player getPlayerToRoomGame(Room room,String deviceId){
		System.out.println("GF get player room = " + room);
		Game game = getGame(room);
		return null;//game.getPlayer(deviceId);
	}
	

}
