package com.housee.app.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.GameLevel;
import com.housee.app.henum.KYLStatus;
import com.housee.app.henum.RoomCategory;
import com.housee.app.henum.TransStatus;
import com.housee.app.henum.UserType;
import com.housee.app.json.GameList;
import com.housee.app.json.PlayerList;
import com.housee.app.json.RedeemForm;
import com.housee.app.json.RoomForm;
import com.housee.app.json.RoomsList;
import com.housee.app.json.Root;
import com.housee.app.json.UserList;
import com.housee.app.json.UserRegistrationForm;
import com.housee.app.mail.ContactUsResponseMail;
import com.housee.app.mail.KYLResponseMail;
import com.housee.app.model.Admin;
import com.housee.app.model.ContactUs;
import com.housee.app.model.FriendContacts;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.MessageInbox;
import com.housee.app.model.Redeem;
import com.housee.app.model.Room;
import com.housee.app.model.UserAccount;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;
import com.housee.app.security.MD5Test;
import com.housee.app.session.UserSession;
import com.housee.app.util.DateTimeUtils;

import static com.housee.app.contants.SystemSettingConstants.*;

public class AdminServiceImpl {

	@Autowired
	CommonDaoImpl commonDao;
	
	@Autowired
	CommonServiceImpl commonService;
	
	@Autowired
	PaymentTransactionServiceImpl paymentService;
	
	public void saveAdmin(Admin admin){
		commonDao.saveAdmin(admin);
	}
	
	public boolean authAdmin(Admin admin){
		return commonDao.authAdmin(admin.getUsername(), admin.getPassword());
	}
	
	
	public Room getRoomById(String roomId){
		return  commonService.getRoomById(roomId);
	}

	public RoomsList getAllRooms(){
		return  commonService.getAllRooms();
	}
	
	public GameList getAllGames(){
		return  commonService.getAllGames();
	}
	
	public UserList getAllUsers(){
		return commonService.getAllUsers();
	}
	
	public void getAllUsers(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("USERS_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("USERS_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		modelAndView.addObject("usersList", getAllUsers(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}
	
	
	
	public PlayerList getAlPlayers(){
		return commonService.getAlPlayers();
	}
	
	public List<KYLRequest> getAllPendingKYLRequest(){
		return commonService.getAllPendingKYLRequest();
	}
	
	public List<ContactUs> getAllContactUsRequest(){
		return commonService.getAllContactUsRequest();
	}

	public Integer getAlQueryCount(String username){
		 return  Integer.valueOf(String.valueOf(commonDao.getAlQueryCount(username)));
	}

	public List<ContactUs> getAllContactUsRequest(String su,int rowIndex, int pageSize){
		return commonService.getAllContactUsRequest(su,rowIndex,pageSize);
	}
	
	public void getAllContactUsRequest(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("CONTACTUS_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("CONTACTUS_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		modelAndView.addObject("contactusList", getAllContactUsRequest(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}

	public void createRoom(RoomForm createRoomForm){
		Room room = new Room();
		room.setName(createRoomForm.getName());
		room.setPrice(Double.valueOf(createRoomForm.getPrice()));
		for(String timeString : createRoomForm.getStartTime()){
			
		}
		room.setStartTime(createRoomForm.getStartTime());;
		room.setCategory(RoomCategory.getCategory(createRoomForm.getCategory()));
		room.setActive(createRoomForm.getActive());
		room.setWinningChargeInPercentage(createRoomForm.getWinningChargeInPercentage());
		room.setKeywork("test");
		room.setAllowUserCount(createRoomForm.getMaxUserCount());
		room.setCountryCode(createRoomForm.getAllowCountryCode()+"");
		room.setLevel1(GameLevel.getGameLevel(createRoomForm.getLevel1()));
		room.setScheduleOn(createRoomForm.getScheduleOn());
				
		commonService.createRoom(room);
	}
	
	
	public KYLRequest getKYLById(String kylId){
		return  commonService.getKYLById(kylId);
	}
	
	public UserProfile getUserById(String userid){
		return commonService.getUserProfileById(userid);
	}
	
	
	public void updateKYLRequest(KYLRequest kylrequestform){
		KYLRequest kylRequest = commonService.getKYLById(String.valueOf(kylrequestform.getId()));
		kylRequest.setKylStatus(KYLStatus.COMPLETED);
		kylRequest.setSuggestion(kylrequestform.getSuggestion());		
		commonDao.saveKYLRequest(kylRequest);
		
		try{
			KYLResponseMail kylresponce = new KYLResponseMail();
			kylresponce.email(kylRequest.getUserProfile().getEmail(),kylRequest.getUserProfile().getName(), kylRequest.getKylType(), kylRequest.getRequest(), kylRequest.getSuggestion());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public List<KYLRequest> getAllKYLRequest(){
		return commonService.getAllKYLRequest();
	}
	
	public UserList getAllUsers(String su,int rowIndex, int pageSize){
		return commonService.getAllUsers(su,rowIndex,pageSize);
	}
	
	public Object getAlUsersCount(String su){
		return commonService.getAlUsersCount(su);
	}	

	
	public Root registerUserProfile(UserRegistrationForm userForm) {
		Root root = new Root();
		try {
			if (commonDao.isUserAlreadyExist(userForm.getEmailId())) {
				throw new Exception("User already Exist");
			}

			UserAccount userAccount = new UserAccount();
			userAccount.setActive(true);
			MD5Test md5 = new MD5Test();
			userAccount.setPassword(md5.MD5HashingPassword(userForm.getPassword()));
			userAccount.setUsername(userForm.getEmailId());

			UserProfile userProfile = new UserProfile();
			// userProfile.setPassword(userForm.getPassword());
			userProfile.setUsername(userForm.getEmailId());
			userProfile.setName(userForm.getFullName());
			userProfile.setEmail(userForm.getEmailId());
			userProfile.setPhone(userForm.getPhone());
			userProfile.setCountry(userForm.getCountry());
			userProfile.setRegistrationDate(Calendar.getInstance().getTime());
			userProfile.setType(UserType.DUMMY);

			commonDao.saveUser(userAccount, userProfile);

			root.setErrFlag(false);
		} catch (Exception e) {
			root.setErrFlag(true);
			root.setErrMessage(e.getMessage());
		}
		return root;
	}
	
	 public void editRoom(RoomForm createRoomForm,String roomId){
		  
		  Room room = commonService.getRoomById(roomId);
		  room.setName(createRoomForm.getName());
		  room.setPrice(Double.valueOf(createRoomForm.getPrice()));
		  room.setStartTime(createRoomForm.getStartTime());;
		  room.setCategory(RoomCategory.getCategory(createRoomForm.getCategory()));
		  room.setActive(createRoomForm.getActive());
		  room.setWinningChargeInPercentage(createRoomForm.getWinningChargeInPercentage());
		  room.setKeywork("test");
		  room.setAllowUserCount(createRoomForm.getMaxUserCount());
		  room.setCountryCode(createRoomForm.getAllowCountryCode()+"");
		  room.setLevel1(GameLevel.getGameLevel(createRoomForm.getLevel1()));
		  room.setScheduleOn(createRoomForm.getScheduleOn());
		  
		  commonService.updateRoom(room);
	}	
	 
	public RoomsList getAllRooms(String su, int rowIndex, int pageSize) {
		return commonService.getAllRooms(su, rowIndex, pageSize);
	}
	
	
	public void getAllRooms(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("ROOMS_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("ROOMS_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		modelAndView.addObject("roomsList", getAllRooms(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}

	public Object getAlRoomsCount(String su) {
		return commonService.getAlRoomsCount(su);
	}

	public List<UserPPTransaction> getDetailsTransation(String tId) {
		return commonService.getDetailsTransation(tId);
	}

	public List<UserAccountBalance> getDetailsAccount(String tId) {
		return commonService.getDetailsAccount(tId);
	}

	public UserProfile getDetails(String userid) {
		return commonService.getDetails(userid);
	}
	
	public Object getAlGameCount(String su) {
		return commonService.getAlGameCount(su);
	}

	public GameList getAllGames(String su, int rowIndex, int pageSize) {
		return commonService.getAllGames(su, rowIndex, pageSize);
	}

	public void getAllGames(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("GAMES_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("GAMES_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		modelAndView.addObject("gamesList", getAllGames(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}

	
	public Game getGameDetails(String gameid) {
		return commonService.getGameDetails(gameid);
	}

	public List<Redeem> getAlRedeem(){
		return commonService.getAlRedeem();
	}
	
		
	public Redeem getRedeemById(String redeemId){System.out.println("=======redeemId========"+redeemId );
		return  commonService.getRedeemById(redeemId);
	}
	
	//---------------------update redeem
	public String upadateRedeem(RedeemForm newRedeemForm,String redeemId){ 
		
		Redeem redeem = commonService.getRedeemById(redeemId);

		UserProfile user = commonService.getUserProfileByUsername(redeem.getUsername());
		  
		redeem.setTransStatus(TransStatus.getTransStatus(newRedeemForm.getTransStatus()));
		redeem.setMessage(newRedeemForm.getMessage());
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		redeem.setCompletedTime(timeFormat.format(currentTime.getTime()));
		redeem.setTimeZone("GMT");

		redeem.setTransationId(newRedeemForm.getTransationId()); 
		redeem.setPayerId(newRedeemForm.getPayerId()); 
		commonService.upadateRedeem(redeem);
		
		UserAccountBalance balance = paymentService.getUserAvailableBalance(user.getUsername());
		if(redeem.getTransStatus().equals(TransStatus.COMPLETED)){
			if(balance.getTotalAvailableBalance() >= redeem.getAmount()){
				paymentService.updateUserAccountOnRedeemAccount(user,redeem.getAmount());
				return "Redeem Success!";
			}else{
				return "Insufficient Balance";
			}
		}else{
			return "Redeem Request not Completed";
		}
		
	}
	
	public Game getGameUpdate(String gId) {
		return commonService.getGameUpdate(gId);
	}

	public GameList getAllGUsers() {
		return commonService.getAllGUsers();
	}

	public ContactUs getContactById(String conatctId){
		return  commonService.getContactById(conatctId);
	}
	
	
	public void updateQueryRequest(ContactUs queryrequestform){
		ContactUs queryRequest = commonService.getContactById(String.valueOf(queryrequestform.getId()));
		
		queryRequest.setReply(queryrequestform.getReply() );		
		commonDao.updateContactUsRequest(queryRequest);		

		try{
			ContactUsResponseMail contactResponse = new ContactUsResponseMail();
			contactResponse.email(queryRequest.getEmail(),queryRequest.getName(), queryRequest.getPhone(), queryRequest.getMessage(), queryRequest.getReply());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Redeem> getAllRedeemPage(String su,int rowIndex, int pageSize){
		return commonService.getRedeemPage(su,rowIndex,pageSize);
	}
	
	//for pagination
	private void setPaginationLinkLenght(int pageIndex,int pageCount,ModelAndView modelAndView){
		System.out.println("pageIndex ---> " + pageIndex);
		if(pageIndex == 1){
			modelAndView.addObject("min", pageIndex );
			int max = (pageIndex + PAGINATION_LINK_LENGHT - 1 < pageCount ? pageIndex + PAGINATION_LINK_LENGHT - 1 : pageCount);
			modelAndView.addObject("max", max );
		}else if(pageIndex == 2){
			modelAndView.addObject("min", 1 );
			int max = (pageIndex + PAGINATION_LINK_LENGHT - 2 < pageCount ? pageIndex + PAGINATION_LINK_LENGHT - 2 : pageCount);
			modelAndView.addObject("max", max );
		}else if(pageIndex > 2){
			modelAndView.addObject("min", pageIndex - 1 );
			int max = (pageIndex + PAGINATION_LINK_LENGHT - 2 < pageCount ? pageIndex + PAGINATION_LINK_LENGHT - 2  : pageCount);
			modelAndView.addObject("max", max );
		}

	}
	
	public void getAllRedeemPage(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("REDEEM_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("REDEEM_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		
		modelAndView.addObject("redeemList", getAllRedeemPage(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}
	
	public List<UserAccountBalance> getUserAccountBalanceFragmentData(ModelAndView mv,HttpServletRequest request,String page, String username){
		return commonService.getUserAccountBalanceFragmentData(mv, request, page, username);
	}

	public List<UserPPTransaction> getUserPPTransFragmentData(ModelAndView mv,HttpServletRequest request,String page ,String username){
		return commonService.getUserPPTransactionFragmentData(mv, request, page, username);
	}

	
	public Object getAlKylCount(String su){
		return commonService.getAlKylCount(su);
	}

	public Object getAlPendingKylCount(String su){
		return commonService.getAlPendingKylCount(su);
	}
	
	public List<KYLRequest> getAllKYLRequest(String su,int rowIndex, int pageSize){
		return commonService.getAllKYLRequest(su,rowIndex,pageSize);
	}
	public List<KYLRequest> getAllPendingKYLRequest(String su,int rowIndex, int pageSize){
		return commonService.getAllPendingKYLRequest(su,rowIndex,pageSize);
	}
	
	public void getAllKYLRequest(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionViewOption = (String)request.getSession().getAttribute("ViewOption");
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("KYL_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("KYL_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_LARGE;
		int pageCount = Integer.valueOf(((totalNumberOfRecords % numberOfRecordsPerPage) == 0 ?(totalNumberOfRecords / numberOfRecordsPerPage) : (totalNumberOfRecords / numberOfRecordsPerPage) +1));
		
		String sPageIndex = pageNumber;
		if(sPageIndex ==null)
		{
			pageIndex = 1;
		}else
		{
			pageIndex = Integer.parseInt(sPageIndex);
		}
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		
		if(sessionViewOption != null && sessionViewOption.equalsIgnoreCase("a")){
			modelAndView.addObject("kylList", getAllKYLRequest(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		}else{
			modelAndView.addObject("kylList", getAllPendingKYLRequest(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		}
		setPaginationLinkLenght(pageIndex,pageCount,modelAndView);
		modelAndView.addObject("pageCount", pageCount);

	}
	
	//Friend Contacts 
	public void  friendContact(FriendContacts frendCont){
		//FriendContacts frendCont=new FriendContacts();
		frendCont.setFriendemail(frendCont.getFriendemail());
		frendCont.setFriendname(frendCont.getFriendname());
		frendCont.setFriendphone(frendCont.getFriendphone());
		frendCont.setUsername(frendCont.getUsername());
		 commonService.friendContact(frendCont);
	}
	
	//Friend Contacts 
	public void updateFriendContact(FriendContacts friendContRequest){
		FriendContacts friendRequest = commonService.getFriendContactById(String.valueOf(friendContRequest.getId()));
		commonDao.updateFriendContact(friendRequest);		
		
	}

	
	public List messageActiveStatus(){
		return commonDao.getMessageBoxStatus();
	}
	
	public MessageInbox getShortMessage(boolean status){
		System.out.println("In Service....................."+commonDao.getMessageBoxShortmsg(status));
		return commonDao.getMessageBoxShortmsg(status);
	}

}

