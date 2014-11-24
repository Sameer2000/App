package com.housee.app.job.scheduler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameStatus;
import com.housee.app.mail.WinnerEmailPDF;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.Game;
import com.housee.app.model.Player;
import com.housee.app.pn.AndroidGCMPNService;
import com.housee.app.pn.iOSPNService;
import com.housee.app.service.GameServiceImpl;

public class TaskExecutorExample { 
	@Autowired
	private CommonDaoImpl commonDao;

	@Autowired
	private GameServiceImpl gameService;

	private class GameRunner1 implements Runnable{
	
	
		private Game game;
		private Hashtable<Integer,Integer> openNumber = new Hashtable<Integer,Integer>();
		
		public GameRunner1(Game game){
			this.game = game;
		}
		
	
		public void run(){
			
			List<String> playerUsernameList = new ArrayList<String>();
			for (Player player : game.getPlayers()){
				playerUsernameList.add(player.getUser().getUsername());
			}
			List<DeviceMapping> mappingList = commonDao.getDeviceMapping(playerUsernameList);
			List<String> androidUserList = new ArrayList<String>();
			List<String> iPhoneUserList = new ArrayList<String>();
			for(DeviceMapping mapping : mappingList){
				if(mapping.getDeviceType().equalsIgnoreCase("android")){
					androidUserList.add(mapping.getDeviceToken());
				}else{
					iPhoneUserList.add(mapping.getDeviceToken());
				}
			}
			String payloadMsg = "Housee Game Started(Game-"+game.getId()+")";
			if(androidUserList.size() > 0){
				AndroidGCMPNService.sendPush(androidUserList,payloadMsg);
			}
			if(iPhoneUserList.size() > 0){
				for(String deviceToken : iPhoneUserList){
					try {
						iOSPNService.push(payloadMsg,deviceToken);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
			}
			
			try {
				Thread.sleep(1000 * 60 * 1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			game.setStatus(String.valueOf(GameStatus.STARTED.getValue()));
			game.setMessage(GameStatus.STARTED.getMessage());
			commonDao.updateGame(game);
			Random rand = new Random();
			int min = 1, max = 99;
			try{
				do{
					// nextInt is normally exclusive of the top value,
					// so add 1 to make it inclusive
					int randomNum = rand.nextInt(max - min + 1) + min;
					System.out.println("Open Number :: " + randomNum);
					if(!openNumber.contains(randomNum)){
					
						openNumber.put(randomNum, randomNum);
						game.addOpenNumbers(randomNum,commonDao);
						commonDao.updateGame(game);
						try {
							Thread.sleep(1000*2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		
				}while(game.getWinner() == null || game.getWinner().size()<3);
				game.setStatus(String.valueOf(GameStatus.COMPLETED.getValue()));
				game.setMessage(GameStatus.COMPLETED.getMessage());
				commonDao.updateGame(game);
				
				gameService.updateCompletedGameWinnerTransaction(game);

				WinnerEmailPDF winnerMail = new WinnerEmailPDF();
				try{
					for(Player player : game.getPlayers()){
						winnerMail.emailOnly(player.getUser().getName(), player.getUser().getEmail());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				payloadMsg = "Housee! Game Completed(Game-"+game.getId()+")";
				if(androidUserList.size() > 0){
					AndroidGCMPNService.sendPush(androidUserList,payloadMsg);
				}
				if(iPhoneUserList.size() > 0){
					for(String deviceToken : iPhoneUserList){
						try {
							iOSPNService.push(payloadMsg,deviceToken);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
					
				}

				
			}catch (Exception e) {
				game.setStatus(String.valueOf(GameStatus.ERROR.getValue()));
				game.setMessage(GameStatus.ERROR.getMessage());
				commonDao.updateGame(game);
			}
		}
	}
	
	private TaskExecutor taskExecutor;

	  public TaskExecutorExample(TaskExecutor taskExecutor) {
	    this.taskExecutor = taskExecutor;
	  }

	  public void startGame(Game game) {
	      taskExecutor.execute(new GameRunner1(game));
	  }
}