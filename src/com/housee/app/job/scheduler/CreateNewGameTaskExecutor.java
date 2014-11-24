package com.housee.app.job.scheduler;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameLevel;
import com.housee.app.henum.UserType;
import com.housee.app.model.Game;
import com.housee.app.model.Room;
import com.housee.app.model.UserProfile;
import com.housee.app.service.GameServiceImpl;
import com.housee.app.service.PlayerServiceImpl;
import com.housee.app.service.RoomServiceImpl;
import com.housee.app.service.TicketServiceImpl;

public class CreateNewGameTaskExecutor { 
	@Autowired
	private CommonDaoImpl commonDao;

	@Autowired
	GameServiceImpl gameService;

	@Autowired
	RoomServiceImpl roomService;
	
	@Autowired
	TicketServiceImpl ticketService;
	
	@Autowired 
	private PlayerServiceImpl playerService;

	private class NewGameRunner1 implements Runnable{
	
	
		private Calendar cal;
		
		public NewGameRunner1(Calendar cal){
			this.cal = cal;
		}
		
	
		public void run(){

			List<UserProfile> dummyUserList = commonDao.getAllUsersByType(UserType.DUMMY);
			for(UserProfile dummyUser : dummyUserList){
				System.out.println("User:: Name --> " + dummyUser.getName() + " , username --> " + dummyUser.getUsername());
			}
			int dummyUserSize = (dummyUserList != null ? dummyUserList.size():0);
			List<Room> roomList = commonDao.getAllActiveRoomsByLevel(String.valueOf(GameLevel.TRIAL.getCode()));
			int dummyUserIndex = 0;
			for(Room room : roomList){
				List<Game> gameList = commonDao.getAllGamesByDate(room.getId(),cal);
				if(gameList==null || gameList.size()==0){
					gameService.createGame(room, cal);
					gameList = commonDao.getAllGamesByDate(room.getId(),cal);
					System.out.println("Check Dummy User ---> ");
	
					for(Game game : gameList){
	
						if(dummyUserList != null && dummyUserSize > 0 && SystemSettingConstants.CHOOSE_DUMMY_USER_FOR_GAME > 0 ){
							Random rand = new Random();
							int min = 1, max = dummyUserList.size();
	
							Set<Integer> addedDummyUser = new HashSet<Integer>();
							int loopIndex = 0;
							if(dummyUserIndex >= dummyUserList.size()){
								dummyUserIndex = 0;
							}
							while(loopIndex < SystemSettingConstants.CHOOSE_DUMMY_USER_FOR_GAME){
								System.out.println("Create Dummy USER ---> " + String.valueOf(game.getId()) + " , " + String.valueOf(room.getId()) + " , " + String.valueOf(dummyUserList.get(dummyUserIndex).getId()));
								playerService.getDummyConfirmPlayer(String.valueOf(game.getId()), String.valueOf(room.getId()), String.valueOf(dummyUserList.get(dummyUserIndex).getUsername()),true);
								loopIndex++;
								dummyUserIndex++;
							}
							
						}
					}
				}
			}
			
		}
	}
	
	private TaskExecutor taskExecutor;

	  public CreateNewGameTaskExecutor(TaskExecutor taskExecutor) {
	    this.taskExecutor = taskExecutor;
	  }

	  public void checkAndCreateNewGame(Calendar cal) {
		  System.out.println("Check and Create New Game -->");
	      taskExecutor.execute(new NewGameRunner1(cal));
	  }
}