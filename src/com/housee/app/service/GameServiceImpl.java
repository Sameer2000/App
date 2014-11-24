package com.housee.app.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.housee.app.contants.Constants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameLevel;
import com.housee.app.henum.GameStatus;
import com.housee.app.json.GameForm;
import com.housee.app.json.GameList;
import com.housee.app.mail.RedeemEmailPDF;
import com.housee.app.model.Game;
import com.housee.app.model.Player;
import com.housee.app.model.Room;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserProfile;
import com.housee.app.model.Winner;
import com.housee.app.session.UserSession;
import com.housee.app.util.DateTimeUtils;

public class GameServiceImpl {

	@Autowired
	private CommonDaoImpl commonDao;

	@Autowired
	private PaymentTransactionServiceImpl paymentService;

	public GameList getAllGames(GameForm form){
		GameList gamesList = new GameList();
		List<Game> games = null;
		switch(form.getFetchGameType()){
			case 0 : {
				games = getAllGamesPlayedByUserInTimeRange(form.getGameId(), form.getUserId());
				break;
			}
			case 1 : {
				games = getTodayGameByRoomId(form.getRoomId());
				break;
			}
			case 2 : {
				games = getNextDayGameByRoomId(form.getRoomId());
				break;
			}
		}
		gamesList.setGames(games);
		return gamesList;
	}
	
	public List<Game> getTodayGameByRoomId(long roomId){
		
		Calendar todayDate = DateTimeUtils.getGMTCalendar();
		Room room = commonDao.getRoomById(roomId);
		
		List<Game> gameList = commonDao.getAllGamesByDate(roomId,todayDate);
		if(gameList==null || gameList.size()==0){
			createGame(room, todayDate);
			gameList = commonDao.getAllGamesByDate(roomId,todayDate);
		}
		return gameList;
	}
	
	
	public void createGame(Room room,Calendar date){
		for(String startTime:room.getStartTime()){
			Game game = new Game();
			try{
				String splitStartTime[] = startTime.split(" ");
				game.setStartTime(DateTimeUtils.getGMTDateTimeString(date.getTime(), splitStartTime[0], splitStartTime[1], Constants.gameStartTimeSDF));
				game.setTimeZone("GMT");
				game.setScheduledOn(new Date(DateTimeUtils.getGMTDate(date.getTime(),Constants.dateSDF).getTime()));
			 	game.setRoom(room);
			 	game.setStatus(String.valueOf(GameStatus.NOT_STARTED.getValue()));
			 	game.setMessage(GameStatus.NOT_STARTED.getMessage());
			 	commonDao.createGame(game);
			}catch(Exception exp){exp.printStackTrace();}
		}
		
	}
	
	
	public List<Game> getNextDayGameByRoomId(long roomId){

		Calendar nextDate = DateTimeUtils.getGMTCalendar();
		nextDate.add(Calendar.DATE, 1);
		Room room = commonDao.getRoomById(roomId);
		
		List<Game> gameList = commonDao.getAllGamesByDate(roomId,nextDate);
		if(gameList==null || gameList.size()==0){
			createGame(room, nextDate);
			gameList = commonDao.getAllGamesByDate(roomId,nextDate);
		}
		return gameList;
	}

	public List<Game> getPreviousPlayedGameByRoomId(long roomId){

		Calendar nextDate = DateTimeUtils.getGMTCalendar();
		nextDate.add(Calendar.DATE, -7);
		Room room = commonDao.getRoomById(roomId);
		
		List<Game> gameList = commonDao.getAllGamesByDateRange(roomId, DateTimeUtils.getGMTCalendar(), nextDate);
		if(gameList==null || gameList.size()==0){
			createGame(room, nextDate);
			gameList = commonDao.getAllGamesByDate(roomId,nextDate);
		}
		return gameList;
	}
	
	public List<Game> getAllGamesPlayedByUserInTimeRange(long roomId,String userId){

		Calendar nextDate = DateTimeUtils.getGMTCalendar();
		nextDate.add(Calendar.DATE, -3);
		
		List<Game> gameList = commonDao.getAllGamesPlayedByUserInTimeRange(roomId,userId, DateTimeUtils.getGMTCalendar(), nextDate);
		return gameList;
	}

	public List<Game> getAllGamesPlayedByUserInTimeRange(long roomId,long userId){

		Calendar nextDate = DateTimeUtils.getGMTCalendar();
		nextDate.add(Calendar.DATE, -3);
		Room room = commonDao.getRoomById(roomId);
		
		List<Game> gameList = commonDao.getAllGamesPlayedByUserInTimeRange(roomId, DateTimeUtils.getGMTCalendar(), nextDate);
		if(gameList==null || gameList.size()==0){
			createGame(room, nextDate);
			gameList = commonDao.getAllGamesByDate(roomId,nextDate);
		}
		return gameList;
	}
	
	public int lockTodayGame(){
		Calendar todayDate = DateTimeUtils.getGMTCalendar();
		return commonDao.lockGamesByScheduledToday(todayDate);
	}
	
	public void checkGameReadyToStartPlay(){
		Calendar todayDate = DateTimeUtils.getGMTCalendar();
		commonDao.checkGameReadyToStartPlay(todayDate);
	}

	public List<Game> getAllGamesReadyToPlay(){
		return commonDao.getAllGamesReadyToStart();
	}
	
	public void updateCompletedGameWinnerTransaction(Game completedGame){

		if(GameLevel.PayedGame.equals(completedGame.getRoom().getLevel1())){
			for(Winner winner : completedGame.getWinners()){
				UserProfile user = commonDao.getUserProfile(winner.getUsername());
				paymentService.updateCompletedGameWinnerTransaction(completedGame, winner, user);
				List<UserAccountBalance> balance = paymentService.getUserTransactionListByUsername(winner.getUsername(), 0, 1);

				try{
					RedeemEmailPDF winnerMail = new RedeemEmailPDF();
					winnerMail.email(user.getName(), String.valueOf(balance.get(0).getWinAmount()),String.valueOf( balance.get(0).getTotalAvailableBalance()));
				}catch(Exception e){
					e.printStackTrace();
				}

			
			}
		}
	
	}
	
	public List<Game> getAllCompletedGamePendingWinnerTransaction(){
		return null;
	}
	
	public void deleteAllNotConfirmedPlayer(Game game){
		commonDao.removeNotConfirmedPlayer(game);
	}
}
