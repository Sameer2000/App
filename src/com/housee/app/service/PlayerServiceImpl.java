package com.housee.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameLevel;
import com.housee.app.model.Block;
import com.housee.app.model.Game;
import com.housee.app.model.Player;
import com.housee.app.model.Ticket;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserProfile;

public class PlayerServiceImpl {

	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	private PaymentTransactionServiceImpl paymentService;

	public Player getPlayerInfo(String gameId, String roomId, String userId){
		Player player = null;
		try{
			player = commonDao.getPlayer(userId , Long.valueOf(gameId));
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return player;
	}
	
	public List<Game> getCompletedGameByUserId(String userId){
		List<Game> game = null;
		try{
			game = commonDao.getCompletedGameByUserId(userId);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return game;
	}
	
	public Player getDummyConfirmPlayer(String gameId, String roomId, String userId, boolean confirmTicket){
		Player player = null;
		try{
			player = commonDao.getPlayer(userId , Long.valueOf(gameId));
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		if(player == null){
			player = createPlayer(gameId,userId);
			confirmPlayer(player);
		}
		return player;
	}

	public Player getPlayer(String gameId, String roomId, String userId, boolean confirmTicket){
		Player player = null;
		try{
			player = commonDao.getPlayer(userId , Long.valueOf(gameId));
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		if(player == null){
			player = createPlayer(gameId,userId);
		}else if(!confirmTicket){
			updatePlayerTicket(player);
		}
		return player;
	}
	

	public Player createPlayer(String gameId, String userId){
		TicketUtil tcUtil = new TicketUtil();
		tcUtil.createTicket();
		Ticket ticket = new Ticket();
		ticket.setTicket(tcUtil.getTicket());

		Player player = new Player();
		UserProfile user = commonDao.getUserProfileById(userId);
		Game game = commonDao.getGamesById(Long.valueOf(gameId));

		player.setGame(game);
		player.setUser(user);
		player.setNewTicket(ticket);
		player = savePlayer(player);
		
		commonDao.updateGame(game);
		
		return player;
	}
	
	public Player updatePlayerTicket(Player player){
		TicketUtil tcUtil = new TicketUtil();
		tcUtil.createTicket();
		printTicket(tcUtil.getTicket());
		Ticket ticket = new Ticket();
		
		ticket.setTicket(tcUtil.getTicket());
		player.setNewTicket(ticket);
		updatePlayer(player);
		return player;
	}

	public Player testUpdatePlayer(String gameId, String userId){
		Player player = commonDao.getPlayer(userId , Long.valueOf(gameId));
		TicketUtil tcUtil = new TicketUtil();
		tcUtil.createTicket();
		printTicket(tcUtil.getTicket());
		Ticket ticket = new Ticket();
		
		ticket.setTicket(tcUtil.getTicket());
		player.getNewTicket().setTicket(ticket.getTicket());
		updatePlayer(player);
		return player;
	}

	public String confirmPlayer(Player player){
		player.setConfirmed(true);
		Ticket ticket = new Ticket();
		ticket.setTicket(player.getNewTicket().getTicket());
		player.setConfirmTicket(ticket);
		player.removeNewTicket();
		confirmPlayerTicket(player);
		return SystemSettingConstants.CONFIRM_TICKET_SUCCESS;
	}
	
	public String confirmPlayer(String gameId, String userId){
		Player player = commonDao.getPlayer(userId , Long.valueOf(gameId));
		if(GameLevel.PayedGame.equals(player.getGame().getRoom().getLevel1())){
			if(!player.isConfirmed()){
				UserAccountBalance userAccBal = paymentService.getUserAvailableBalance(userId);
				if(userAccBal.getTotalAvailableBalance() >= player.getGame().getRoom().getPrice()){
					paymentService.updateUserAccountOnConfirmTicket(player);
					confirmPlayer(player);
					return SystemSettingConstants.FIRST_CONFIRM_TICKET_SUCCESS_FOR_PAYED_GAME;
				}else{
					return SystemSettingConstants.INSUFFICIENT_BALANCE;
				}
			}else{
				return confirmPlayer(player);
			}
		}else{
			return confirmPlayer(player);
		}
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

	
	public Player savePlayer(Player player){
		return commonDao.createPlayer(player);
	}
	
	public void updatePlayer(Player player){
		commonDao.updatePlayer(player);
	}
	
	public void confirmPlayerTicket(Player player){
		commonDao.confirmPlayerTicket(player);
	}
	
}
