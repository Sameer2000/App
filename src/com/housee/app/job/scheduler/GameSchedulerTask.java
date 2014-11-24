package com.housee.app.job.scheduler;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.housee.app.model.Game;
import com.housee.app.service.GameServiceImpl;

public class GameSchedulerTask{
	
	@Autowired
	GameServiceImpl gameService;
	
	@Autowired
	TaskExecutorExample taskExecutorExample;
	
	public void checkScheduleGame(){
		System.out.println("Schecdule Game Started");
		
		int result = gameService.lockTodayGame();
		gameService.checkGameReadyToStartPlay();
		List<Game> readyGameList = gameService.getAllGamesReadyToPlay();
		if(readyGameList!=null){
			for(Game readyGame : readyGameList){
				taskExecutorExample.startGame(readyGame);
			}
		}
		
	}


}
