package com.housee.app.job.scheduler;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.housee.app.model.Game;
import com.housee.app.service.GameServiceImpl;
import com.housee.app.util.DateTimeUtils;

public class CreateNewGameTask {

	@Autowired
	GameServiceImpl gameService;
	
	@Autowired
	CreateNewGameTaskExecutor createNewGameTaskExecutor;
	
	public void checkNewGameToCreate(){
		System.out.println("Check New Game To Create ---> ");
		
		//Check Player Account to update Winning Amount
		
		
		
		//Check Today Game
		Calendar today =  DateTimeUtils.getGMTCalendar();
		createNewGameTaskExecutor.checkAndCreateNewGame(today);
		
		//Check Tomorrow Game
		Calendar tomorrow = DateTimeUtils.getGMTCalendar();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		createNewGameTaskExecutor.checkAndCreateNewGame(tomorrow);
		
		
	}


}
