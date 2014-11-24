package com.housee.app.service;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.housee.app.job.scheduler.TaskExecutorExample;
import com.housee.app.model.Game;

public class GameScheduler{
	
	@Autowired
	GameServiceImpl gameService;
	
	@Autowired
	TaskExecutorExample taskExecutorExample;
	
//	@Scheduled(fixedRate=1000*60*3)
	public void checkScheduleGame(){
		System.out.println("Schecdule Game Started");
		
		gameService.lockTodayGame();
		
		gameService.checkGameReadyToStartPlay();
		
		List<Game> readyGameList = gameService.getAllGamesReadyToPlay();
		if(readyGameList!=null){
			for(Game readyGame : readyGameList){

				taskExecutorExample.startGame(readyGame);
			}
		}
		
	}


}
