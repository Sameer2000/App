package com.housee.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.housee.app.model.Block;
import com.housee.app.model.Player;
import com.housee.app.service.PlayerServiceImpl;

public class PlayerTest {

	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/test/testApplicationContext.xml");
		PlayerServiceImpl playerService = (PlayerServiceImpl)applicationContext.getBean("playerService");
		Player player = playerService.testUpdatePlayer("1", "1");
		printTicket(player.getConfirmTicket().getTicket());
	}

	
	public static void printTicket(Block[][] ticket){
		for(int i=0;i<3;i++){
			for(int j=0;j<10;j++){
				if(ticket[i][j].getNumber() == 0){
					System.out.print("   X   ");
				}else{
					System.out.print("   " + ticket[i][j].getNumber() + "   ");
				}
			}
			System.out.println("");
		}
	}

}
