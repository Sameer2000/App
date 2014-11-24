package com.housee.app.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.housee.app.model.Game;
import com.housee.app.service.GameServiceImpl;

public class GameTest {

	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/test/testApplicationContext.xml");
		GameServiceImpl gameService = (GameServiceImpl)applicationContext.getBean("gameService");
		List<Game> gameList = gameService.getAllGamesPlayedByUserInTimeRange(1,1);
		System.out.println(gameList.size());
	}

}
