package com.housee.app.controller;

import static com.housee.app.contants.PGConstants.iphost;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.json.Friend;
import com.housee.app.json.GameForm;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.json.GameForm;
import com.housee.app.json.GameList;
import com.housee.app.json.GameRoot;
import com.housee.app.json.InviteFriendRoot;
import com.housee.app.json.KYLList;
import com.housee.app.json.KYLRoot;
import com.housee.app.json.PaypalTrans;
import com.housee.app.json.RechargeAmountForm;
import com.housee.app.json.RechargeAmountRoot;
import com.housee.app.json.RegisterRoot;
import com.housee.app.json.RoomsList;
import com.housee.app.json.Root;
import com.housee.app.json.UserRegistrationForm;
import com.housee.app.json.UserTotalBalance;
import com.housee.app.mail.InviteFriendmail;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.Player;
import com.housee.app.model.Room;
import com.housee.app.model.Ticket;
import com.housee.app.service.GameFactory;
import com.housee.app.service.GameServiceImpl;
import com.housee.app.service.PaymentTransactionServiceImpl;
import com.housee.app.service.PlayerServiceImpl;
import com.housee.app.service.RoomServiceImpl;
import com.housee.app.service.TicketServiceImpl;
import com.housee.app.service.UserServiceImpl;

@Controller
@Scope("request")
@RequestMapping("/rest")
public class MobileRestControllerImpl {

	@Autowired
	private RoomServiceImpl roomService;
	
	@Autowired
	private TicketServiceImpl ticketService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private GameServiceImpl gameService;
	
	@Autowired 
	private PlayerServiceImpl playerService;
	
	@Autowired
	private PaymentTransactionServiceImpl paymentService;
	
	@Autowired
	private CommonDaoImpl commonDao;
	
	@RequestMapping(value = "reguser.json", method = RequestMethod.POST)
	public @ResponseBody Root registerUserProfile(@RequestBody RegisterRoot root){
		System.out.println("This is test application");
		UserRegistrationForm userForm = root.getRequest();
		return userService.registerUserProfile(userForm);
	}
	
	@RequestMapping(value = "authuser.json", method = RequestMethod.POST)
	public @ResponseBody RegisterRoot authenticateUserProfile(@RequestBody RegisterRoot root){
		System.out.println("This is test application");
		UserRegistrationForm userForm = root.getRequest();
		return userService.authenticateUserProfile(userForm);
	}
	
	@RequestMapping(value = "rooms.json", method = RequestMethod.POST)
	public @ResponseBody RoomsList getRoomsList(@RequestBody Root root){
		System.out.println("This is test application");
		return roomService.getRoomsList(root);
	}
	
	@RequestMapping(value = "games.json", method = RequestMethod.POST)
	public @ResponseBody GameList getGamesList(@RequestBody GameRoot root){
		System.out.println("This is test application");
		return gameService.getAllGames(root.getRequest());
	}

	@RequestMapping(value = "getPlayer.json", method =  RequestMethod.POST)
	public @ResponseBody Player getPlayer(@RequestBody GameRoot root){
		System.out.println("Game ID :: " + root.getRequest().getGameId());
		Player player = playerService.getPlayer(root.getRequest().getGameId()+"", root.getRequest().getRoomId()+"", root.getRequest().getUserId(),false);
		return player;
	}
	
	@RequestMapping(value = "getConfirmPlayer.json", method =  RequestMethod.POST)
	public @ResponseBody Player getConfirmPlayer(@RequestBody GameRoot root){
		System.out.println("Game ID :: " + root.getRequest().getGameId());
		Player player = playerService.getPlayer(root.getRequest().getGameId()+"", root.getRequest().getRoomId()+"", root.getRequest().getUserId(),true);
		return player;
	}

	@RequestMapping(value = "getRoom.json", method =  RequestMethod.POST)
	public @ResponseBody Room getRoom(@RequestBody Root root){
		System.out.println("RoomId :: " + root.getSession().getRoomId() +" , DeviceType :: " + root.getSession().getDeviceType());
		return GameFactory.getInstance().getRoom(Long.valueOf(root.getSession().getRoomId()));
	}

	@RequestMapping(value = "getTicket.json", method =  {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Player getPlayerTicket(@RequestBody GameRoot root){
		Player player = playerService.getPlayerInfo(root.getRequest().getGameId()+"", root.getRequest().getRoomId()+"", root.getRequest().getUserId());
		return player;
	}

	@RequestMapping(value = "getCompletedGame.json", method =  {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody GameList getCompletedGameList(@RequestBody Root root){
		GameList gameList = new GameList();
		gameList.setError(true);
		List<Game> game = null;
		try{
			game = playerService.getCompletedGameByUserId(root.getRequest().getUserId());
			System.out.println("Player ID ---> " + root.getRequest().getUserId());
			if(game != null){
				gameList.setError(false);
				gameList.setMessage("Played Game Winner!");
				System.out.println("Player Game ID --> " + game.get(0).getId());
			}else{
				System.out.println("No Player --> ");
				gameList.setMessage("No Played Game Available");
			}
			gameList.setGames(game);
			
		}catch(Exception e){
			game = null;
			gameList.setGames(game);
			gameList.setMessage("No Played Game Available");
		}
		return gameList;
	}

	@RequestMapping(value = "ticket.json", method =  {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Ticket getTicket(@RequestBody Root root){
		System.out.println("RoomId :: " + root.getSession().getRoomId() +" , DeviceId :: " + root.getSession().getDeviceType());
		ticketService.createTicket();
		ticketService.printTicket();
		ticketService.addPlayerToRoomGame(root.getSession().getRoomId(),root.getSession().getDeviceType(),ticketService.getTicket());
		Ticket ticket = ticketService.getTicket();
		return ticket;
	}
	
	@RequestMapping(value = "confirmTicket.json", method =  RequestMethod.POST)
	public @ResponseBody Root confirmTicket(@RequestBody GameRoot root){
		System.out.println("Game ID :: " + root.getRequest().getGameId());
		System.out.println("User ID :: " + root.getRequest().getUserId());
		String message = playerService.confirmPlayer(root.getRequest().getGameId()+"", root.getRequest().getUserId());
		Root root1 = new Root();
		root1.setErrFlag(false);
		root1.setErrMessage(message);
		return root1;
	}
	
	@RequestMapping(value = "getAccountInfo.json", method =  RequestMethod.POST)
	public @ResponseBody UserTotalBalance getAccountInfo(@RequestBody Root root){
		System.out.println("User Account  ---> " + root.getRequest().getUserId());
		UserTotalBalance userTotBal = userService.getAccountInfo(root.getRequest().getUserId());
		return userTotBal;
	}
	
	
	
	
	
	//Sameer
	@RequestMapping(value = "inviteFriend.json", method =  RequestMethod.POST)
	public String inviteFriend(@ModelAttribute GameForm gameForm, Root root,HttpServletRequest request, HttpServletResponse response){
		String receiverName = root.getRequest().getName();
		String to = root.getRequest().getEmailId();
		String recipient = gameForm.getUserEmailId();
		Long roomId = gameForm.getRoomId();
		//String roomId = commonDao.getAllGameRooms(gameForm.getRoomId()).toString();
		
			if(roomId!=null)
			{
				System.out.println("Room Id===>"+roomId);	
				
			}
		
		InviteFriendmail inviteFriend = new InviteFriendmail();
		inviteFriend.email(receiverName, roomId, to , recipient);
		return  "redirect:/happ/site/home";
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="savekyl.json", method = RequestMethod.POST)
	public @ResponseBody String saveKYLRequest(@RequestBody KYLRoot root){
		userService.kyLRequest(root.getRequest().getUserId(),root.getRequest());
		return "{status:1,message:\"Your Request has been successfully submitted\"}";
	}
	
	@RequestMapping(value="getkyl.json", method = RequestMethod.POST)
	public KYLList getkylList(@RequestBody Root root){
		List<KYLRequest> kylRequestList = userService.getAllKYLRequest(root.getRequest().getUserId());
		KYLList kylList = new KYLList();
		kylList.setKylList(kylRequestList);
		kylList.setKylFee(SystemSettingConstants.kylFee);
		return kylList;
	}
	
	@RequestMapping(value="saveDeviceToken.json", method = RequestMethod.POST)
	public @ResponseBody String saveDeviceToken(@RequestBody Root root){
		userService.saveDeviceToken(root);
		return "{status:1,message:\"Your Request has been successfully submitted\"}";
	}
	
	@RequestMapping(value="pptrans.json", method = RequestMethod.POST)
	public @ResponseBody PaypalTrans ppTrans(@RequestBody RechargeAmountRoot root){
		PaypalTrans pp = new PaypalTrans();
		RechargeAmountForm rechargeAmountForm = (RechargeAmountForm)root.getRequest();
		boolean status = paymentService.initPPTransactionMobile(rechargeAmountForm, pp,root.getRequest().getUsername());
		System.out.println("SESSION PP TOKEN ---> " + pp.getToken());
		String redirectStr = "";
		if(!status){
			redirectStr = "/happ/paypal/pg/failed";
		}else{
			redirectStr =  iphost + "/happ/paypal/pg/submit?token="+pp.getToken();//PGConstants.ppMobilePaymentGateway+pp.getToken();
		}
		pp.setStatus(status);
		pp.setUrl(redirectStr);
		return pp;
	}
	
	@RequestMapping(value="checkvaliduser.json", method = RequestMethod.POST)
	public @ResponseBody Root checkUserValidityForGame(@RequestBody GameRoot root){
		Player player = playerService.getPlayerInfo(root.getRequest().getGameId()+"", root.getRequest().getRoomId()+"", root.getRequest().getUserId());
		Root rootStatus = new Root();
		if(player == null || (player != null && !player.isConfirmed())){
			rootStatus.setErrFlag(true);
			rootStatus.setErrMessage("You have not confirmed Ticket, Please Select another game to play");
		}else{
			rootStatus.setErrFlag(false);
		}
		
		return rootStatus;
		
	}
	
	@RequestMapping(value="invitefrnd.json", method = RequestMethod.POST)
	public @ResponseBody Root sendInviteToFriend(@RequestBody InviteFriendRoot root){
		Root result = new Root();
		for(Friend friend : root.getRequest().getFrnddata()){
			System.out.println("Friend Daata -> " + friend.getName() + " , " + friend.getEmail());
		}
		
		return result;
		
	}
	
	
/*	//Sameer
	@RequestMapping(value = "inviteFriend.json", method =  RequestMethod.POST)
	public String inviteFriend(@ModelAttribute GameForm gameForm, Root root,HttpServletRequest request, HttpServletResponse response){
		String receiverName = root.getRequest().getName();
		String to = root.getRequest().getEmailId();
		String recipient = gameForm.getUserEmailId();
		Long roomId = gameForm.getRoomId();
		//String roomId = commonDao.getAllGameRooms(gameForm.getRoomId()).toString();
		
			if(roomId!=null)
			{
				System.out.println("Room Id===>"+roomId);	
				
			}
		
		InviteFriendmail inviteFriend = new InviteFriendmail();
		inviteFriend.email(receiverName, roomId, to , recipient);
		return  "redirect:/happ/site/home";
	}*/
	
}
