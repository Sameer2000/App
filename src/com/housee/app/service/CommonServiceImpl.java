package com.housee.app.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.json.GameList;
import com.housee.app.json.PlayerList;
import com.housee.app.json.RoomsList;
import com.housee.app.json.UserList;
import com.housee.app.model.ContactUs;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.FriendContacts;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.MessageInbox;
import com.housee.app.model.Player;
import com.housee.app.model.Redeem;
import com.housee.app.model.Room;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;
import com.housee.app.session.UserSession;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class CommonServiceImpl {

	@Autowired
	CommonDaoImpl commonDao;
	
	@Autowired
	PaymentTransactionServiceImpl paymentService;
	
	public void setMessage(MessageInbox inbox){
		java.util.Date date= new java.util.Date();
		inbox.setSendTime(new Timestamp(date.getTime()));
		final long ONE_DAY_MILLISCONDS = 360 * 60 * 60 * 1000;
		inbox.setValidDate(new Timestamp (date.getTime() + ONE_DAY_MILLISCONDS));
		commonDao.message(inbox);
	}
	
	public void updateActiveStatus(MessageInbox inbox){

		commonDao.activeStatus(inbox);
	}
	
	public List deviceMappingUsers(){
		List<DeviceMapping> users = commonDao.getAllUserOfDeviceMapping();
		return users;
	}
	
	
	
	
	public RoomsList getAllRooms(){
		List<Room> rooms = commonDao.getAllRooms();
		RoomsList roomList = new RoomsList();
		roomList.setRooms(rooms);
		return roomList;
		
	}
	
	public Room getRoomById(String roomId){
		Room room = commonDao.getRoomById(Long.valueOf(roomId));
		return room;
		
	}
	
	public GameList getAllGames(){
		List<Game> games = commonDao.getAllGames();
		GameList gameList = new GameList();
		gameList.setGames(games);
		return gameList;
	}
	
	public UserList getAllUsers(){
		List<UserProfile> users = commonDao.getAlUsers();
		UserList userList = new UserList();
		userList.setUsers(users);
		return userList;
	}
	
	public PlayerList getAlPlayers(){
		List<Player> players = commonDao.getAlPlayers();
		PlayerList playerList = new PlayerList();
		playerList.setPlayers(players);
		return playerList;
	}
	
	public List<KYLRequest> getAllPendingKYLRequest(){
		List<KYLRequest> kylList = commonDao.getAllPendingKYLRequest();
		return kylList;
	}
	
	public List<ContactUs> getAllContactUsRequest(){
		List<ContactUs> contactUsList = commonDao.getAllContactUsRequest();
		return contactUsList;
	}

	public List<ContactUs> getAllContactUsRequest(String su,int rowIndex, int pageSize){
		return commonDao.getAllContactUsRequest(su,rowIndex,pageSize);
	}

	public void createRoom(Room room){
		commonDao.createRoom(room);
	}
	
	public void saveKYLRequest(KYLRequest kylRequest){
		commonDao.saveKYLRequest(kylRequest);
	}

	public UserProfile getUserProfileById(String userid){
		UserProfile userProfile = commonDao.getUserProfileById(Long.valueOf(userid));
		return userProfile;
	}
	
	public UserProfile getUserProfileByUsername(String userid){
		UserProfile userProfile = commonDao.getUserProfile(userid);
		return userProfile;
	}

	public KYLRequest getKYLById(String kylId){
		KYLRequest kyl = commonDao.getKYLById(Long.valueOf(kylId));
		return kyl;		
	}
	
	public List<KYLRequest> getAllKYLRequest(){
		List<KYLRequest> kylList = commonDao.getAllKYLRequest();
		return kylList;
	}

	public UserList getAllUsers(String su,int rowIndex, int pageSize){
		List<UserProfile> users = commonDao.getAlUsers(su,rowIndex,pageSize);
		UserList userList = new UserList();
		userList.setUsers(users);
		return userList;
	}
	
	public Object getAlUsersCount(String su){
		Object rowcount = commonDao.getAlUsersCount(su);
		return rowcount;
	}

	public void updateRoom(Room room){
		  commonDao.updateRoom(room);
	}

	public RoomsList getAllRooms(String su,int rowIndex, int pageSize){
		List<Room> rooms = commonDao.getAllRooms(su,rowIndex,pageSize);
		RoomsList roomList = new RoomsList();
		roomList.setRooms(rooms);
		return roomList;
	}
	
	public Object getAlRoomsCount(String su) {
		Object rowcount = commonDao.getAlRoomsCount(su);
		return rowcount;
	}

	public List<UserPPTransaction> getDetailsTransation(String tId) {
		List<UserPPTransaction> transationList = commonDao
				.getDetailsTransation((tId));
		return transationList;
	}

	public List<UserAccountBalance> getDetailsAccount(String tId) {
		List<UserAccountBalance> accountList = commonDao
				.getDetailsAccount((tId));
		return accountList;
	}
	
	public UserProfile getDetails(String userid) {
		UserProfile userProfile = commonDao.getDetails(Long.valueOf(userid));
		return userProfile;
	}

	public Object getAlGameCount(String su) {
		Object rowcount = commonDao.getAlGameCount(su);
		return rowcount;
	}

	public GameList getAllGames(String su, int rowIndex, int pageSize) {
		List<Game> games = commonDao.getAllGamesSearch(su, rowIndex, pageSize);
		GameList gameList = new GameList();
		gameList.setGames(games);
		return gameList;
	}

	public Game getGameDetails(String gameid) {
		Game gameDetails = commonDao.getGameDetails(Long.valueOf(gameid));
		return gameDetails;
	}

	public List<Redeem> getAlRedeem() {
		List<Redeem> redeems = commonDao.getAlRedeem();
		return redeems;
	}

	public Redeem getRedeemById(String redeemId) {
		Redeem rede = commonDao.getRedeemById(Long.valueOf(redeemId));
		return rede;

	}

	public void upadateRedeem(Redeem room) {
		commonDao.upadateRedeem(room);
	}

	public Game getGameUpdate(String gameid1) {
		Game gameDetails1 = commonDao.getGameUpdate(Long.valueOf(gameid1));
		return gameDetails1;
	}
	
	public Game getGameId(String status) {
		Game gameDetails1 = commonDao.getGameId(status);
		return gameDetails1;
	}

	public GameList getAllGUsers() {
		List<Game> users = commonDao.getAllGUsers();
		GameList userList = new GameList();
		userList.setGames(users);
		return userList;
	}
	
	public List getAllGUsersId() {
		List<Game> users = commonDao.getAllGUsers();
		return users;
	}

	public void updateGame(Game game){
		commonDao.updateGame(game);
	}
	
	public ContactUs getContactById(String contactId){
		ContactUs contact = commonDao.getContactById(Long.valueOf(contactId));
		return contact;		
	}	
	
	public List<Redeem> getRedeemPage(String su,int rowIndex, int pageSize){
		List<Redeem> users = commonDao.getRedeemPage(su,rowIndex,pageSize);
		return users;
	}	
	
	public Integer getAllRedeemCount(String username){
		return Integer.valueOf(String.valueOf( commonDao.getAllRedeemCount(username)));
	}
	
	public Integer getAllSearchRedeemCount(String su){
		return Integer.valueOf(String.valueOf( commonDao.getAllSearchRedeemCount(su)));
	}

	public List<UserAccountBalance> getUserAccountBalanceFragmentData(ModelAndView mv,HttpServletRequest request,String page, String username){
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int pageIndex = (Integer)request.getSession().getAttribute("ACC_TRANS_PAGE");
		int totalTransCount = (Integer)request.getSession().getAttribute("ACC_TOTAL_TRANS");
		if(page.equalsIgnoreCase("Pre")){
			pageIndex = (pageIndex > 0 ) ? pageIndex - settingPageSize : 0;
			if(pageIndex <= 0){
				mv.addObject("pre","none");
				mv.addObject("next","block");
			}
		}else{
			pageIndex = pageIndex + settingPageSize;
			if(pageIndex + settingPageSize >  (totalTransCount - 1)){
				mv.addObject("next","none");
				mv.addObject("pre","block");
			}
		}
		List<UserAccountBalance> userAccountBal = paymentService.getUserTransactionListByUsername(username,pageIndex, settingPageSize);
		if(userAccountBal != null && userAccountBal.size() > 0){
			request.getSession().setAttribute("ACC_TRANS_PAGE", pageIndex);
		}

		return userAccountBal;

	}

	public List<UserPPTransaction> getUserPPTransactionFragmentData(ModelAndView mv,HttpServletRequest request,String page,String username){
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int pageIndex = (Integer)request.getSession().getAttribute("PP_TRANS_PAGE");
		int totalTransCount = (Integer)request.getSession().getAttribute("PP_TOTAL_TRANS");
		if(page.equalsIgnoreCase("Pre")){
			pageIndex = (pageIndex > 0 ) ? pageIndex - settingPageSize : 0;
			if(pageIndex <= 0){
				mv.addObject("pre","none");
				mv.addObject("next","block");
			}
		}else{
			pageIndex = pageIndex + settingPageSize;
			if(pageIndex + settingPageSize >  (totalTransCount - 1)){
				mv.addObject("next","none");
				mv.addObject("pre","block");
			}
		}
		List<UserPPTransaction> userTransList = paymentService.getUserAllPPTransactionListByUsername(pageIndex, settingPageSize, username);
		if(userTransList != null && userTransList.size() > 0){
			request.getSession().setAttribute("PP_TRANS_PAGE", pageIndex);
		}
		return userTransList;
	}
	
	public Object getAlKylCount(String su){
		Object rowcount = commonDao.getAlKylCount(su);
		return rowcount;
	}
	
	public Object getAlPendingKylCount(String su){
		Object rowcount = commonDao.getAlPendingKylCount(su);
		return rowcount;
	}

	
	public List<KYLRequest> getAllKYLRequest(String su,int rowIndex, int pageSize){
		List<KYLRequest> kylList = commonDao.getAllKYLRequest(su,rowIndex,pageSize);
		return kylList;
	}
	
	public List<KYLRequest> getAllPendingKYLRequest(String su,int rowIndex, int pageSize){
		List<KYLRequest> kylList = commonDao.getAllPendingKYLRequest(su,rowIndex,pageSize);
		return kylList;
	}
	// Friend Contacts
	public FriendContacts getFriendContactById(String contactId){
		FriendContacts contact = commonDao.getFriendContactById(Long.valueOf(contactId));
		return contact;		
	}

	//Friend Contact
	public void friendContact(FriendContacts frndCont){
		commonDao.friendContact(frndCont);
	}

}
