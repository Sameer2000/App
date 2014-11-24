package com.housee.app.dao;

import static com.housee.app.contants.Constants.LOCK_TIME;
import static com.housee.app.contants.Constants.utcSDFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.housee.app.henum.GameLevel;
import com.housee.app.henum.GameStatus;
import com.housee.app.henum.KYLStatus;
import com.housee.app.henum.UserType;
import com.housee.app.model.Admin;
import com.housee.app.model.ContactUs;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.FriendContacts;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.MessageInbox;
import com.housee.app.model.Player;
import com.housee.app.model.Redeem;
import com.housee.app.model.Room;
import com.housee.app.model.TokenTable;
import com.housee.app.model.UserAccount;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;
import com.housee.app.model.Winner;

@Transactional
public class CommonDaoImpl {

	@Autowired
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserProfile(UserProfile userProfile){
		em.merge(userProfile);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserAccount(UserAccount userAccount){
		em.merge(userAccount);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUser(UserAccount userAccount,UserProfile userProfile){
		em.persist(userAccount);
		em.persist(userProfile);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean validateUser(String username,String password){
		List<UserAccount> userList =  em.createQuery("Select u From UserAccount u Where u.username=:username and u.password=:password",UserAccount.class)
		.setParameter("username", username)
		.setParameter("password", password)
		.getResultList();
		
		return (userList != null && userList.size() > 0 ? true : false); 
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserProfile(String username){
		List<UserProfile> userProfileList =  em.createQuery("Select u From UserProfile u Where u.username=:username",UserProfile.class)
		.setParameter("username", username)
		.getResultList();
		
		return (userProfileList != null && userProfileList.size() > 0 ? userProfileList.get(0) : null); 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserAccount(String username){
		UserAccount userAccount =  em.createQuery("Select u From UserAccount u Where u.username=:username",UserAccount.class)
		.setParameter("username", username)
		.getSingleResult();
		
		return userAccount; 
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserProfileById(String userId){
		UserProfile userProfile =  em.createQuery("Select u From UserProfile u Where u.username=:userId",UserProfile.class)
		.setParameter("userId", userId)
		.getSingleResult();
		
		return userProfile; 
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean isUserAlreadyExist(String username){
		List userProfileList = em.createQuery("Select u From UserAccount u Where u.username=:username")
		.setParameter("username",  username.trim())
		.getResultList();
		return (userProfileList != null && userProfileList.size() > 0 ? true : false);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean createRoom(Room room){
		em.persist(room);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveContactUsRequest(ContactUs contactUs){
		em.persist(contactUs);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateContactUsRequest(ContactUs contactUs){
		em.merge(contactUs);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveKYLRequest(KYLRequest kylRequest){
		em.merge(kylRequest);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllKYLRequest(String username){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl where kyl.userProfile.username=:username Order By kyl.id Desc",KYLRequest.class)
				.setParameter("username", username )
				.getResultList();
		return kylList;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Room> getAllRooms(){
		List<Room> rooms = em.createQuery("Select r From Room r",Room.class).getResultList();
		return rooms;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserProfile> getAlUsers(){
		List<UserProfile> users = em.createQuery("Select u From UserProfile u",UserProfile.class).getResultList();
		return users;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserProfile> getAllUsersByType(UserType type){
		List<UserProfile> users = em.createQuery("Select u From UserProfile u Where u.type=:type",UserProfile.class)
				.setParameter("type", type)
				.getResultList();
		return users;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Player> getAlPlayers(){
		List<Player> players = em.createQuery("Select p From Player p",Player.class).getResultList();
		return players;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllPendingKYLRequest(){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl Where kyl.kylStatus=:pending or kyl.kylStatus=:review",KYLRequest.class)
				.setParameter("pending", KYLStatus.PENDING)
				.setParameter("review", KYLStatus.REVIEW)
				.getResultList();
		return kylList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ContactUs> getAllContactUsRequest(){
		List<ContactUs> contactUsList = em.createQuery("Select c From ContactUs c",ContactUs.class)
				.getResultList();
		return contactUsList;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAlQueryCount(String userId){
		Object rowCount = em.createQuery("Select Count(*) From ContactUs c Where c.name Like :userId  or c.email Like :userId Order By c.id Desc")
				.setParameter("userId","%" + userId + "%")
				.getSingleResult();
		return rowCount;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ContactUs> getAllContactUsRequest(String su,int rowIndex, int pageSize){
		List<ContactUs> contactUsList = em.createQuery("Select c From ContactUs c Where c.name Like :su or c.email Like :su Order By c.id Desc",ContactUs.class)
				.setParameter("su", "%"+su+"%")
				.setFirstResult(rowIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return contactUsList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Room> getAllRoomsByLevel(String level){
		List<Room> rooms = em.createQuery("Select r From Room r Where r.level1=:level",Room.class)
				.setParameter("level", GameLevel.getGameLevel(Integer.valueOf(level) ) )
				.getResultList();
		return rooms;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Room> getAllActiveRoomsByLevel(String level){
		List<Room> rooms = em.createQuery("Select r From Room r Where r.level1=:level and r.active=true",Room.class)
				.setParameter("level", GameLevel.getGameLevel(Integer.valueOf(level) ) )
				.getResultList();
		return rooms;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Room getRoomById(long roomId){
		Room room = em.createQuery("Select r From Room r Where r.id=:id",Room.class)
				.setParameter("id", roomId)
				.getSingleResult();
		return room;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGames(){
		List<Game> gameList = em.createQuery("Select g From Game g",Game.class)
				.getResultList();
		return gameList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getAllGameRooms(long roomId){
		Game roomList =  em.createQuery("Select g From Game g Where g.room_id=:id",Game.class)
				.setParameter("id", roomId)
				.getSingleResult();
		return roomList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesByDate(long roomId,Calendar date){
		List<Game> gameList = em.createQuery("Select g From Game g Where g.room.id=:id and DATE_FORMAT(g.scheduledOn,'%d/%m/%y')=:todayDate",Game.class)
				.setParameter("id", roomId)
				.setParameter("todayDate", utcSDFormat.format(date.getTime()))
				.getResultList();
		return gameList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesByDateRange(long roomId,Calendar beforeDate,Calendar afterDate){
		List<Game> gameList = em.createQuery("Select g From Game g Where g.room.id=:id and g.scheduledOn<=:beforeDate and g.scheduledOn>:afterDate",Game.class)
				.setParameter("id", roomId)
				.setParameter("beforeDate", beforeDate.getTime())
				.setParameter("afterDate", afterDate.getTime())
				.getResultList();
		return gameList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesPlayedByUserInTimeRange(long roomId,String userId,Calendar beforeDate,Calendar afterDate){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.user.username=:userId and p.game.room.id=:id and p.game.scheduledOn<=:beforeDate and p.game.scheduledOn>:afterDate",Game.class)
				.setParameter("id", roomId)
				.setParameter("userId", userId)
				.setParameter("beforeDate", beforeDate.getTime())
				.setParameter("afterDate", afterDate.getTime())
				.getResultList();
		return gameList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesPlayedByUserInTimeRange(long roomId,Calendar beforeDate,Calendar afterDate){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.game.room.id=:id and p.game.scheduledOn<=:beforeDate and p.game.scheduledOn>:afterDate",Game.class)
				.setParameter("id", roomId)
				.setParameter("beforeDate", beforeDate.getTime())
				.setParameter("afterDate", afterDate.getTime())
				.getResultList();
		return gameList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllPlayedGameByUserId(String userId){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.user.username=:userId Order By p.game.id Desc",Game.class)
				.setParameter("userId", userId)
				.getResultList();
		return gameList;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllWinGameByUserId(String userId){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.user.username=:userId and p.winCount > 0 Order By p.game.id Desc",Game.class)
				.setParameter("userId", userId)
				.getResultList();
		return gameList;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllWinGameByUserId(String userId, int pageIndex, int pageSize){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.user.username=:userId and p.winCount > 0 Order By p.game.id Desc",Game.class)
				.setParameter("userId", userId)
				.setFirstResult(pageIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return gameList;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAllWinGameByUserIdCount(String userId){
		Object rowCount = em.createQuery("Select Count(*) From Player p Where p.user.username=:userId and p.winCount > 0 Order By p.game.id Desc")
				.setParameter("userId", userId)
				.getSingleResult();
		return rowCount;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesReadyToStart(){
		List<Game> readyGameList = em.createQuery("Select g From Game g Where g.status=:readyStatus and g.playerCount > 0",Game.class)
				.setParameter("readyStatus", String.valueOf(GameStatus.READYTOSTART.getValue()))
				.getResultList();
		return readyGameList;
				
	}	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllCompletedGamePendingWinnerTransaction(){
		List<Game> readyGameList = em.createQuery("Select g From Game g Where g.status=:readyStatus and g.playerCount > 0 and g.transStatus = 0 and g.room.level1 = :level",Game.class)
				.setParameter("readyStatus", String.valueOf(GameStatus.COMPLETED.getValue()))
				.setParameter("level", GameLevel.PayedGame)
				.getResultList();
		return readyGameList;
	}	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void checkGameReadyToStartPlay(Calendar todayDate){
		SimpleDateFormat lockTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar readyTime = Calendar.getInstance();
		readyTime.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		lockTimeSDF.setCalendar(readyTime);
		lockTimeSDF.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		em.createQuery("Update Game Set status=:readyStatus, message=:readyMessage Where status=:status  and playerCount > 0 and DATE_FORMAT(scheduledOn,'%d/%m/%y')=:todayDate and str_to_date(startTime,'%Y-%m-%d %H:%i:%s') <=str_to_date(:readyTime,'%Y-%m-%d %H:%i:%s')")
				.setParameter("status", String.valueOf(GameStatus.LOCKED.getValue()))
				.setParameter("readyTime", lockTimeSDF.format(readyTime.getTime()))
				.setParameter("readyStatus", String.valueOf(GameStatus.READYTOSTART.getValue()))
				.setParameter("readyMessage", GameStatus.READYTOSTART.getMessage())
				.setParameter("todayDate", utcSDFormat.format(todayDate.getTime()))
				.executeUpdate();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int lockGamesByScheduledToday(Calendar todayDate){
		SimpleDateFormat lockTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lockTimeSDF.setTimeZone(TimeZone.getTimeZone("GMT"));

		Calendar lockTime = Calendar.getInstance();
		lockTime.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		lockTime.add(Calendar.MINUTE, LOCK_TIME);

		System.out.println("Lock Time ---> " + lockTimeSDF.format(lockTime.getTime()));
		int result = em.createQuery("Update Game Set status=:lockStatus, message=:lockMessage Where status=:status and playerCount > 0 and DATE_FORMAT(scheduledOn,'%d/%m/%y')=:todayDate and str_to_date(startTime,'%Y-%m-%d %H:%i:%s') <=str_to_date(:lockTime,'%Y-%m-%d %H:%i:%s')")
				.setParameter("status", String.valueOf(GameStatus.NOT_STARTED.getValue()))
				.setParameter("lockTime", lockTimeSDF.format(lockTime.getTime()))
				.setParameter("lockStatus", String.valueOf(GameStatus.LOCKED.getValue()))
				.setParameter("lockMessage", GameStatus.LOCKED.getMessage())
				.setParameter("todayDate", utcSDFormat.format(todayDate.getTime()))
				.executeUpdate();
		return result;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getGamesByTodayScheduledTime(){
		Calendar playTime = Calendar.getInstance();
		playTime.setTimeZone(TimeZone.getTimeZone("GMT"));
		Game game = em.createQuery("Select g From Game g Where g.status=1 and  g.startTime Set status=1 Where status=0 and startTime <=:lockTime",Game.class)
				.setParameter("playTime", playTime)
				.getSingleResult();
		return game;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getGamesById(long gameId){
		Game game = em.createQuery("Select g From Game g Where g.id=:id",Game.class)
				.setParameter("id", gameId)
				.getSingleResult();
		return game;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createGame(Game game){
		em.merge(game);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createWinner(Winner winner){
		em.merge(winner);
		
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Player getPlayer(String userId,long gameId){
		Player player = em.createQuery("Select p From Player p Where p.user.username=:userId and p.game.id=:gameId",Player.class)
							.setParameter("userId", userId)
							.setParameter("gameId", gameId)
							.getSingleResult();
		return player;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getCompletedGameByUserId(String userId){
		List<Game> gameList = em.createQuery("Select p.game From Player p Where p.user.username=:userId and p.game.status=:status and p.confirmed=true Order By p.game.id Desc",Game.class)
				.setParameter("userId", userId)
				.setParameter("status", String.valueOf(GameStatus.COMPLETED.getValue()))
				.setMaxResults(3)
				.getResultList();
		return gameList;
		
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Player  getPlayerById(long playerId){
		Player player = em.createQuery("Select p From Player p Where p.id=:playerId",Player.class)
							.setParameter("playerId", playerId)
							.getSingleResult();
		return player;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Player createPlayer(Player player){
		return em.merge(player);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeNotConfirmedPlayer(Game game){
		for(Player player : game.getPlayers()){
			if(!player.isConfirmed()){
				em.remove(player);
			}
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePlayer(Player player){
		em.merge(player);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void confirmPlayerTicket(Player player){
		em.merge(player);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateGame(Game game){
		em.merge(game);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveTicket(Player player){
		em.merge(player);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAdmin(Admin admin){
		em.persist(admin);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean authAdmin(String username,String password){
		Admin admin = null;
		try{
			admin  = em.createQuery("Select a From Admin a Where a.username=:username and a.password=:password", Admin.class)
			.setParameter("username", username)
			.setParameter("password", password)
			.getSingleResult();
		}catch(Exception ex){
			admin = null;
		}
		if(admin!=null){
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Admin searchAdminDatabase(String username){
		Admin admin = null;
		try{
			admin  = em.createQuery("Select a From Admin a Where a.username=:username", Admin.class)
			.setParameter("username", username)
			.getSingleResult();
		}catch(Exception ex){
			admin = null;
		}
		return admin;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount searchUserDatabase(String username){
		UserAccount user = null;
		try{
			user  = em.createQuery("Select u From UserAccount u Where u.username=:username", UserAccount.class)
			.setParameter("username", username)
			.getSingleResult();
		}catch(Exception ex){
			user = null;
		}
		return user;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveDeviceMapping(DeviceMapping mapping){
		try{
			em.persist(mapping);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateDeviceMapping(DeviceMapping mapping){
		try{
			em.merge(mapping);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public DeviceMapping getDeviceMapping(String username){
		DeviceMapping mapping = null;
		try{
			mapping  = em.createQuery("Select dm From DeviceMapping dm Where dm.username :username", DeviceMapping.class)
			.setParameter("username",  username )
			.getSingleResult();

		}catch(Exception e){
			mapping = null;
		}
		return mapping;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List getAllUserOfDeviceMapping(){
	    List<DeviceMapping> mapping  = em.createQuery("Select dm From DeviceMapping dm", DeviceMapping.class).getResultList();
		return mapping;
	}
	
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<DeviceMapping> getDeviceMapping(List<String> usernameList){
		List<DeviceMapping> mappingList = null;
		try{
			mappingList  = em.createQuery("Select dm From DeviceMapping dm Where dm.username in (:usernamesList)", DeviceMapping.class)
			.setParameter("usernamesList",  usernameList )
			.getResultList();

		}catch(Exception e){
			mappingList = null;
		}
		return mappingList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserProfileById(long userId){
		UserProfile userProfile = em.createQuery("Select u From UserProfile u Where u.id=:id",UserProfile.class)
				.setParameter("id", userId)
				.getSingleResult();
		return userProfile;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public KYLRequest getKYLById(long kylId){
		KYLRequest kyl = em.createQuery("Select k From KYLRequest k Where k.id=:id",KYLRequest.class)
				.setParameter("id", kylId)
				.getSingleResult();
		return kyl;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllKYLRequest(){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl Order By kyl.id Desc",KYLRequest.class)
				.getResultList();
		return kylList;
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	 public boolean updateRoom(Room room){
	  em.merge(room);
	  return true;
	 }	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<TokenTable> getTokens(){
		List<TokenTable> result =  em.createQuery("Select u From TokenTable u ",TokenTable.class).getResultList();
		return result; 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserProfile> getAlUsers(String su,int rowIndex, int pageSize){
		List<UserProfile> users = em.createQuery("Select u From UserProfile u Where u.username Like :su or u.name Like :su Order By u.id Desc",UserProfile.class)
				.setParameter("su", "%"+su+"%")
				.setFirstResult(rowIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return users;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Room> getAllRooms(String su,int rowIndex, int pageSize){
		List<Room> rooms = em.createQuery("Select u From Room u Where u.name Like :su ",Room.class)
				.setParameter("su", "%"+su+"%")
				.setFirstResult(rowIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return rooms;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAlUsersCount(String su){
		String queryString = "SELECT Count(*) FROM UserProfile u Where u.username Like :su or u.name Like :su";  
		Query query = em.createNativeQuery(queryString).setParameter("su", "%"+su+"%"); 
		System.out.println("User Row Count --> " + query.getSingleResult());
		return query.getSingleResult();
	
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAlRoomsCount(String su){
		String queryString = "SELECT Count(*) FROM Room u Where u.name Like :su ";  
		Query query = em.createNativeQuery(queryString).setParameter("su", "%"+su+"%"); 
		return query.getSingleResult();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserProfile> getAlSearchUsers(){
		List<UserProfile> users = em.createQuery("Select u From UserProfile u Where u.name=: ",UserProfile.class).getResultList();
		return users;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserPPTransaction> getDetailsTransation(String tId){
		List<UserPPTransaction> transationList = em.createQuery("Select t From UserPPTransaction t Where t.username=:username",UserPPTransaction.class)
				.setParameter("username", tId)
				.getResultList();
		return transationList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserAccountBalance> getDetailsAccount(String tId){
		List<UserAccountBalance> accountList = em.createQuery("Select t From UserAccountBalance t Where t.username=:username",UserAccountBalance.class)
				.setParameter("username", tId)
				.getResultList();
		return accountList;
	}	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getDetails(long uId) {
		UserProfile details = em
				.createQuery("Select u From UserProfile u Where u.id=:id",
						UserProfile.class).setParameter("id", uId)
				.getSingleResult();
		return details;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean setTokensHitstatus(TokenTable tokentable){
		em.merge(tokentable);
		return true; 
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveNewPasswordRequest(String username,String password){
		List<UserAccount> userList =  em.createQuery("Select u From UserAccount u Where u.username=:username and u.password=:password",UserAccount.class)
		.setParameter("username", username)
		.setParameter("password", password)
		.getResultList();
		return (userList != null && userList.size() > 0 ? true : false);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean setTokens(TokenTable tokentable){
		em.persist(tokentable);
		return true; 
	}	
	
	  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	  public Object getAlGameCount(String su){
	    String queryString = "SELECT Count(*) FROM Game u Where u.startTime Like :su ";  
	    Query query = em.createNativeQuery(queryString).setParameter("su", "%"+su+"%"); 
	    System.out.println("User Row Count --> " + query.getSingleResult());
	    return query.getSingleResult();
	  
	  }


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGamesSearch(String su, int rowIndex, int pageSize) {
		List<Game> games = em
				.createQuery(
						"Select u From Game u Where u.startTime Like :su ",
						Game.class).setParameter("su", "%" + su + "%")
				.setFirstResult(rowIndex).setMaxResults(pageSize)
				.getResultList();
		return games;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getGameDetails(long gId) {
		Game details = em
				.createQuery("Select u From Game u Where u.id=:id", Game.class)
				.setParameter("id", gId).getSingleResult();

		return details;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveRedeemRequest(Redeem redeem){
		em.persist(redeem);
		return true;
	} 

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Redeem> getAlRedeem(){
		List<Redeem> redeems = em.createQuery("Select p From Redeem p Order By p.id DESC",Redeem.class).getResultList();
		return redeems;
	}	
	
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Redeem getRedeemById(long redeemId){
		Redeem rede = em.createQuery("Select r From Redeem r Where r.id=:id",Redeem.class)
				.setParameter("id", redeemId)
				.getSingleResult();
		return rede;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean upadateRedeem(Redeem room){
		em.merge(room);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getGameUpdate(long gId1) {
		Game details = em
				.createQuery("Select u From Game u Where u.id=:id", Game.class)
				.setParameter("id", gId1).getSingleResult();
		return details;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Game getGameId(String status) {
		Game details = em
				.createQuery("Select u From Game u Where u.status=:status", Game.class)
				.setParameter("status", status).getSingleResult();
		return details;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Game> getAllGUsers() {
		List<Game> users = em.createQuery("Select u From Game u ", Game.class)

		.getResultList();
		return users;
	}

	
	
	
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ContactUs getContactById(long contactId){
		ContactUs conatct = em.createQuery("Select c From ContactUs c Where c.id=:id",ContactUs.class)
				.setParameter("id", contactId)
				.getSingleResult();
		return conatct;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Redeem> getAllRedeem(String username,int pageIndex,int pageSize){
		List<Redeem> gameList = em.createQuery("Select p  From Redeem p Where p.username=:userId Order By p.id Desc",Redeem.class)
				.setParameter("userId", username)
				.setFirstResult(pageIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return gameList;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAllRedeemCount(String username){
		Object rowCount = em.createQuery("Select Count(*)  From Redeem p Where p.username=:userId Order By p.id Desc")
				.setParameter("userId", username)
				.getSingleResult();
		return rowCount;
		
	}
	
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAllSearchRedeemCount(String su){
		String queryString = "SELECT Count(*) FROM Redeem u Where u.username Like :su ";  
		Query query = em.createNativeQuery(queryString).setParameter("su", "%"+su+"%"); 
		 
		return query.getSingleResult();
	
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Redeem> getRedeemPage(String su,int rowIndex, int pageSize){
		List<Redeem> users = em.createQuery("Select u From Redeem u Where u.username Like :su or u.payerId Like :su or u.transationId Like :su Order By u.id Desc",Redeem.class)
				.setParameter("su", "%"+su+"%")
				.setFirstResult(rowIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return users;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAlKylCount(String su){
		String queryString = "SELECT Count(*) FROM KYLRequest kyl Where kyl.request Like :su or kyl.kylStatus Like :su";  
		Query query = em.createNativeQuery(queryString).setParameter("su", "%"+su+"%"); 
		System.out.println("User Row Count --> " + query.getSingleResult());
		return query.getSingleResult();
	
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getAlPendingKylCount(String su){
		String queryString = "SELECT Count(*) FROM KYLRequest kyl Where kyl.request Like :su or kyl.kylStatus Like :su and (kyl.kylStatus=:pending or kyl.kylStatus=:review)";  
		Query query = em.createNativeQuery(queryString)
		.setParameter("su", "%"+su+"%")
		.setParameter("pending", KYLStatus.PENDING)
		.setParameter("review", KYLStatus.REVIEW);
		return query.getSingleResult();
	
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllKYLUserRequest(String username, String su, int rowIndex, int pageSize){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl where kyl.userProfile.username=:username and kyl.request Like :su Order By kyl.id Desc",KYLRequest.class)
				.setParameter("username", username )
				.setParameter("su", "%"+su+"%")
				.setFirstResult(rowIndex)
				.setMaxResults(pageSize)
				.getResultList();				
		return kylList;
	}	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllKYLRequest(String su, int pageIndex, int pageSize){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl Where kyl.request Like :su1 ",KYLRequest.class)
				.setParameter("su1", "%"+su+"%")
				.setFirstResult(pageIndex)
				.setMaxResults(pageSize)
				.getResultList();
		return kylList;
	}
		
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<KYLRequest> getAllPendingKYLRequest(String su, int pageIndex, int pageSize){
		List<KYLRequest> kylList = em.createQuery("Select kyl From KYLRequest kyl Where (kyl.kylStatus=:pending or " +
				"kyl.kylStatus=:review) and kyl.request Like :su ",KYLRequest.class)
				.setParameter("pending", KYLStatus.PENDING)
				.setParameter("review", KYLStatus.REVIEW)
				.setParameter("su", "%"+su+"%")
				.setFirstResult(pageIndex)
				.setMaxResults(pageSize)

				.getResultList();
		return kylList;
	}
	//Friend Contact
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FriendContacts getFriendContactById(long contactId){
		FriendContacts conatct = em.createQuery("Select c From FriendContacts c Where c.id=:id",FriendContacts.class)
				.setParameter("id", contactId)
				.getSingleResult();
		return conatct;
	}
	
	//Friend Contact
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean friendContact(FriendContacts frndCont){
		em.persist(frndCont);
		return true;
	}
	//Friend Contact
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateFriendContact(FriendContacts contactUs){
		em.merge(contactUs);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean message(MessageInbox inbox){
		em.persist(inbox);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean activeStatus(MessageInbox inbox){
		em.merge(inbox);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List getMessageBoxStatus(){
		List<MessageInbox> item = em.createQuery("Select mi From MessageInbox mi",MessageInbox.class).getResultList();
		return item;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MessageInbox getMessageBoxShortmsg(boolean status){
		System.out.println("Common dao stayus**********************"+status);
		MessageInbox result = em.createQuery("Select mi From MessageInbox mi Where mi.active=:active",MessageInbox.class)
		.setParameter("active", status)
		.getSingleResult();
		System.out.println("In common dao===================="+result.isActive());
		return result;
	}
	

}
