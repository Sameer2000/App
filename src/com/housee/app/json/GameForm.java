package com.housee.app.json;

public class GameForm {

	long roomId;
	String userId;
	String userEmailId;
	long gameId;
	int fetchGameType;
	
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public int getFetchGameType() {
		return fetchGameType;
	}
	public void setFetchGameType(int fetchGameType) {
		this.fetchGameType = fetchGameType;
	}
	
}
