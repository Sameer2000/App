package com.housee.app.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.henum.GameStatus;
import com.housee.app.json.GameList;
import com.housee.app.json.RedeemForm;
import com.housee.app.json.RoomForm;
import com.housee.app.json.UserRegistrationForm;
import com.housee.app.mail.Push_notification_mail;
import com.housee.app.model.Admin;
import com.housee.app.model.ContactUs;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.FriendContacts;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.MessageInbox;
import com.housee.app.model.Redeem;
import com.housee.app.model.Room;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;
import com.housee.app.pn.Send_Push_notification;
import com.housee.app.service.AdminServiceImpl;
import com.housee.app.service.CommonServiceImpl;
import com.housee.app.service.PaymentTransactionServiceImpl;
import com.housee.app.session.UserSession;
import com.housee.app.util.DateTimeUtils;
import com.housee.app.util.OptionsMapUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl {

	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	CommonServiceImpl commonService;

	@Autowired
	PaymentTransactionServiceImpl paymentService;
	
	 @RequestMapping(value = "login", method = RequestMethod.GET)
	 public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
	   ModelMap model) {
		 if (error == true) {
			   // Assign an error message
			   model.put("error", "You have entered an invalid username or password!");
			  } else {
			   model.put("error", "");
			  }
			   
			  // This will resolve to /WEB-INF/jsp/loginpage.jsp
			  return "admin_home";
	 }
	 
	@RequestMapping(value={"forward/{pageName}"})
	public ModelAndView forward(@PathVariable("pageName") String pageName, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Admin Forward To("+pageName+") Page");
		modelAndView.setViewName("admin_"+pageName);
		return modelAndView;
	}
	
	@RequestMapping(value={"/**/redirect/{pageName}"})
	public String redirect(@PathVariable("pageName") String pageName, HttpServletRequest request){
		return "redirect:/happ/admin/"+pageName;
	}
	
	@RequestMapping(value={"/auth"}, method = RequestMethod.POST)
	public ModelAndView adminAuth(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("adminAuth") Admin admin, BindingResult result){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Auth Admin, Username --> " + admin.getUsername());
		boolean authFlag = adminService.authAdmin(admin);
		if(authFlag){
			modelAndView.addObject("roomsList", adminService.getAllRooms());
			modelAndView.setViewName("admin_welcome");
		}else{
			modelAndView.setViewName("admin_home");
		}	
		return modelAndView;
	}


	@RequestMapping(value={"/rooms"}, method = RequestMethod.GET)
		public ModelAndView getAllRooms(@RequestParam(value="p", required=false) String pageNumber,
		@RequestParam(value="search1", required=false) String searchRoom, HttpServletRequest request, HttpServletResponse response){
			if(pageNumber == null){
				String searchRoomCriteria = (searchRoom != null ? searchRoom : "");
				request.getSession().setAttribute("ROOMS_SEARCH_CRITERIA", searchRoomCriteria);	
				request.getSession().setAttribute("ROOMS_TOTAL_TRANS", Integer.valueOf(adminService.getAlRoomsCount(searchRoomCriteria).toString()));

			}
			ModelAndView modelAndView = new ModelAndView();
			adminService.getAllRooms(pageNumber, modelAndView, request);
			modelAndView.setViewName("admin_room_list_view");
			return modelAndView;
		}
	
	
	
	//sameer
	@RequestMapping(value={"/sendPN"}, method = RequestMethod.GET)
	public ModelAndView messagePage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("send_push_notification_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/notification"}, method = RequestMethod.POST)
	public ModelAndView sendMessage(@ModelAttribute MessageInbox inbox, HttpServletRequest request, HttpServletResponse response){
		List<MessageInbox> item = adminService.messageActiveStatus();
		ModelAndView modelAndView = new ModelAndView();
		for(MessageInbox status : item){
			System.out.println(status.isActive()+""+status.getLongMessage());
			status.setActive(false);
			commonService.updateActiveStatus(status);
		}
		inbox.setActive(true);
		commonService.setMessage(inbox);
		Push_notification_mail sendMail = new Push_notification_mail();
		List<DeviceMapping> users = commonService.deviceMappingUsers();
		if(users.isEmpty()){
			System.out.println("in if method");
			modelAndView.addObject("err", "User is Not Found");
			modelAndView.setViewName("send_push_notification_view");
			return modelAndView;
		}
		
			
		
		System.out.println("Controller users."+users);
		Game gameId = commonService.getGameId("7");
		
		for(DeviceMapping allUsers : users){
			System.out.println(allUsers.getUsername());
			sendMail.sendEmail(allUsers.getUsername(), inbox.getShortMessage(), inbox.getLongMessage(), "sameer@innverse.com");
		}

		Send_Push_notification p_notification = new Send_Push_notification();
		p_notification.sendPN(users, inbox.getShortMessage(), gameId);
		
		modelAndView.setViewName("send_push_notification_view");
		return modelAndView;
	}
	
	
	
	
	
	
	@RequestMapping(value={"/users"}, method = RequestMethod.GET)
	public ModelAndView getAllUsers(@RequestParam(value="p", required=false) String pageNumber,
	@RequestParam(value="search1", required=false) String searchUser, HttpServletRequest request, HttpServletResponse response){
		if(pageNumber == null){
			String searchUserCriteria = (searchUser != null ? searchUser : "");
			request.getSession().setAttribute("USERS_SEARCH_CRITERIA", searchUserCriteria);
			request.getSession().setAttribute("USERS_TOTAL_TRANS", Integer.valueOf(adminService.getAlUsersCount(searchUserCriteria).toString()));
		}
		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllUsers(pageNumber, modelAndView, request);
		modelAndView.setViewName("admin_users_list_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/players"}, method = RequestMethod.GET)
	public ModelAndView getAllPlayers(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("playerList", adminService.getAlPlayers());
		modelAndView.setViewName("admin_players_list_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/kyl"}, method = RequestMethod.GET)
	public ModelAndView getAllKYLRequest(@RequestParam(value="p", required=false) String pageNumber,
			@RequestParam(value="search1", required=false) String searchUser,
			@RequestParam(value="v", required=false) String viewOption, HttpServletRequest request, HttpServletResponse response){		

		String sessionViewOption = (String)request.getSession().getAttribute("ViewOption");
		if(sessionViewOption == null || !sessionViewOption.equalsIgnoreCase(viewOption)){
			request.getSession().setAttribute("ViewOption", viewOption);
			sessionViewOption = viewOption;
		}

		if(pageNumber == null){
			String searchUserCriteria = (searchUser != null ? searchUser : "");
			request.getSession().setAttribute("KYL_SEARCH_CRITERIA", searchUserCriteria);
			if(sessionViewOption != null && sessionViewOption.equalsIgnoreCase("a")){
				request.getSession().setAttribute("KYL_TOTAL_TRANS", Integer.valueOf(adminService.getAlKylCount(searchUserCriteria).toString()));
			}else{
				request.getSession().setAttribute("KYL_TOTAL_TRANS", Integer.valueOf(adminService.getAlPendingKylCount(searchUserCriteria).toString()));
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllKYLRequest(pageNumber, modelAndView, request);
		modelAndView.setViewName("admin_kyl_list_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/cntUsQuery"}, method = RequestMethod.GET)
	public ModelAndView getAllContactUsRequest(@RequestParam(value="p", required=false) String pageNumber,
	@RequestParam(value="search1", required=false) String searchQuery, HttpServletRequest request, HttpServletResponse response){
		if(pageNumber == null){
			String searchQueryCriteria = (searchQuery != null ? searchQuery : "");
			request.getSession().setAttribute("CONTACTUS_SEARCH_CRITERIA", searchQueryCriteria);	
			request.getSession().setAttribute("CONTACTUS_TOTAL_TRANS", Integer.valueOf(adminService.getAlQueryCount(searchQueryCriteria).toString()));
		}
		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllContactUsRequest(pageNumber, modelAndView, request);
		modelAndView.setViewName("admin_contact_us_view");
		return modelAndView;
	}	

	@RequestMapping(value={"/createRoom"}, method = RequestMethod.GET)
	public ModelAndView createRoom(Model model){
		model.addAttribute("roomCategory", OptionsMapUtils.getRoomCategory());
		model.addAttribute("gameLevel", OptionsMapUtils.getGameLevel());
		model.addAttribute("hours", OptionsMapUtils.getHours());
		model.addAttribute("minutes", OptionsMapUtils.getMinutes());
		model.addAttribute("createRoomForm", new RoomForm());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_create_room");
		return modelAndView;
	}
	
	@RequestMapping(value={"/editRoom/{roomId}"}, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editRoom(@PathVariable("roomId") String roomId, Model model, HttpServletRequest request, HttpServletResponse response){
		Room room = adminService.getRoomById(roomId);
		RoomForm form = new RoomForm();
		model.addAttribute("createRoomForm", new RoomForm());
		model.addAttribute("roomCategory", OptionsMapUtils.getRoomCategory());
		model.addAttribute("gameLevel", OptionsMapUtils.getGameLevel());
		model.addAttribute("allowedUserCount", OptionsMapUtils.getMaxAllowedUserCount());
		model.addAttribute("allowedCountryCode", OptionsMapUtils.getAllowedCountryCode());
		model.addAttribute("weekDays", OptionsMapUtils.getAllWeekDays());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("room", room);
		modelAndView.setViewName("admin_edit_room");
		return modelAndView;
	}	
	
	@RequestMapping(value={"/saveRoom"}, method = RequestMethod.POST)
	public ModelAndView saveRoom(@ModelAttribute("createRoomForm") @Valid RoomForm createRoomForm,
			BindingResult result, Model model,HttpServletRequest request, HttpServletResponse response){
		System.out.println("Room Name --> " + createRoomForm.getName());
		System.out.println("Room Price --> " + createRoomForm.getPrice());
		System.out.println("Room Charges % --> " + createRoomForm.getWinningChargeInPercentage());
		System.out.println("Room Level1 --> " + createRoomForm.getLevel1());
		System.out.println("Room Category --> " + createRoomForm.getCategory());
		System.out.println("Room Allowed Country Code --> " + createRoomForm.getAllowCountryCode());
		System.out.println("Schedule On --> " + createRoomForm.getScheduleOn());
		System.out.println("Start Time --> " + createRoomForm.getStartTime());
		System.out.println("Active --> " + createRoomForm.getActive());
		
		if(result.hasErrors()) {
			System.out.println("Error ---> " + result.getAllErrors());
			model.addAttribute("createRoomForm", createRoomForm);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("admin_create_room");
			return modelAndView;
        }
		adminService.createRoom(createRoomForm);
		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllRooms(null, modelAndView, request);
		modelAndView.setViewName("admin_room_list_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/registration"}, method = RequestMethod.GET)
	public ModelAndView registration(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_registration_view");
		return modelAndView;
	}	

	@RequestMapping(value="updateKYLRequest" , method = RequestMethod.POST)
	public ModelAndView updateKYLrequest(@ModelAttribute("kylrequestForm") KYLRequest kylRequestForm, BindingResult result,HttpServletRequest request){
		adminService.updateKYLRequest(kylRequestForm);		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllKYLRequest(null, modelAndView, request);
		modelAndView.setViewName("admin_kyl_list_view");
		return modelAndView;
	}
	
	@RequestMapping(value={"/kyl/reply/{kylid}*"}, method= RequestMethod.GET)
	public ModelAndView getKYLById(@PathVariable("kylid") String kylid,HttpServletRequest request, HttpServletResponse response){
		KYLRequest kyl =  adminService.getKYLById(kylid);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("kyl", kyl);
		modelAndView.setViewName("admin_kyl_reply_view");
		return modelAndView;
	}

	@RequestMapping(value={"/createUser"}, method = RequestMethod.GET)
	 public ModelAndView createNewUser(Model model){
	  model.addAttribute("roomCategory", OptionsMapUtils.getRoomCategory());
	  model.addAttribute("gameLevel", OptionsMapUtils.getGameLevel());
	  model.addAttribute("hours", OptionsMapUtils.getHours());
	  model.addAttribute("minutes", OptionsMapUtils.getMinutes());
	  model.addAttribute("createRoomForm", new RoomForm());
	  ModelAndView modelAndView = new ModelAndView();
	  modelAndView.setViewName("admin_create_user");
	  return modelAndView;
	 }
	
	@RequestMapping(value="createUser")
	 public ModelAndView RegisterUser(@ModelAttribute("userRegForm") UserRegistrationForm userRegForm,
	       BindingResult result,HttpServletRequest request){
	  System.out.println("User Email Id --> " + userRegForm.getEmailId());
	  
	  adminService.registerUserProfile(userRegForm);
	  
	  ModelAndView modelAndView = new ModelAndView();
	  adminService.getAllUsers(null, modelAndView, request);
	  
	  modelAndView.setViewName("admin_users_list_view");
	  
	  return modelAndView;
	 }

	@RequestMapping(value={"/updateRoom/{roomId}"}, method = RequestMethod.POST)
	 public ModelAndView updateRoom(@PathVariable("roomId") String roomId, @ModelAttribute("createRoomForm") @Valid RoomForm createRoomForm,
	   BindingResult result, Model model,HttpServletRequest request, HttpServletResponse response){
	  System.out.println("Room Name --> " + createRoomForm.getName());
	  System.out.println("Room Price --> " + createRoomForm.getPrice());
	  System.out.println("Room Level1 --> " + createRoomForm.getLevel1());
	  System.out.println("Room Category --> " + createRoomForm.getCategory());
	  System.out.println("Room Allowed Country Code --> " + createRoomForm.getAllowCountryCode());
	  System.out.println("Schedule On --> " + createRoomForm.getScheduleOn());
	  System.out.println("Start Time --> " + createRoomForm.getStartTime());
	  System.out.println("Active --> " + createRoomForm.getActive());
	  
	  if(result.hasErrors()) {
		   System.out.println("result.hasErrors() --> " + result.getAllErrors().get(0).getDefaultMessage());
		   Room room = adminService.getRoomById(roomId);
		   RoomForm form = new RoomForm();
		   model.addAttribute("createRoomForm", new RoomForm());
		   model.addAttribute("roomCategory", OptionsMapUtils.getRoomCategory());
		   model.addAttribute("gameLevel", OptionsMapUtils.getGameLevel());
		   model.addAttribute("allowedUserCount", OptionsMapUtils.getMaxAllowedUserCount());
		   model.addAttribute("allowedCountryCode", OptionsMapUtils.getAllowedCountryCode());
		   model.addAttribute("weekDays", OptionsMapUtils.getAllWeekDays());
		   ModelAndView modelAndView = new ModelAndView();
		   modelAndView.addObject("room", room);
		   modelAndView.setViewName("admin_edit_room");
		   return modelAndView;
	  }
	  
	  adminService.editRoom(createRoomForm,roomId);
	  
	  ModelAndView modelAndView = new ModelAndView();
	  adminService.getAllRooms(null, modelAndView, request);
	  modelAndView.setViewName("admin_room_list_view");
	  return modelAndView;
	 }
	
	@RequestMapping(value={"/detailUsers/{uVieId}"}, method = RequestMethod.GET)
	public ModelAndView getuserDetails(@PathVariable("uVieId") String uId,HttpServletRequest request, HttpServletResponse response){
		UserProfile user = adminService.getDetails(uId);

		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int ppTolalTansCount = paymentService.getUserPPTransCount(user.getUsername());

		int accTotalTransCount = paymentService.getUserAccountBalanceCount(user.getUsername());
		request.getSession().setAttribute("PP_TRANS_PAGE", 0);
		request.getSession().setAttribute("PP_TOTAL_TRANS", ppTolalTansCount);
		
		request.getSession().setAttribute("ACC_TRANS_PAGE", 0);
		request.getSession().setAttribute("ACC_TOTAL_TRANS", accTotalTransCount);
		
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("usersDetails",user);
		modelAndView.addObject("transList",paymentService.getUserPPTransactionList(0, settingPageSize, user.getUsername() ));
		modelAndView.addObject("accountList",paymentService.getUserTransactionListByUsername(user.getUsername() ,0, settingPageSize));
		modelAndView.addObject("usrname", user.getUsername());
		modelAndView.setViewName("admin_users_edit_view");
		modelAndView.addObject("pre","none");
		modelAndView.addObject("next","block");
		return modelAndView;
	}
	
	@RequestMapping(value="user_detail_fragment_pptrans_page")
	public ModelAndView getFragmentAccoutTrans(@RequestParam("usrname") String usrname,@RequestParam(value="p", required=false) String page, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("user_detail_fragment_pptrans_page");
		List<UserPPTransaction> userTransList = adminService.getUserPPTransFragmentData(mv, request, page, usrname);
		mv.addObject("usrname", usrname);
		mv.addObject("transList", userTransList);
		return mv;

	}
	
	@RequestMapping(value="user_detail_fragment_account_page")
	public ModelAndView getTransFragmentPage(@RequestParam("usrname") String usrname,@RequestParam(value="p", required=false) String page, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("user_detail_fragment_account_page");
		List<UserAccountBalance> userAccountBal = adminService.getUserAccountBalanceFragmentData(mv, request, page, usrname );
		mv.addObject("usrname", usrname);
		mv.addObject("accountList",userAccountBal);
		return mv;
		
	}


	
	@RequestMapping(value={"/games"}, method = RequestMethod.GET)
	 public ModelAndView getAllGames(@RequestParam(value="p", required=false) String pageNumber,
	      @RequestParam(value="searchGame", required=false) String searchUser, HttpServletRequest request, HttpServletResponse response){
        if(pageNumber == null){
          String searchGameCriteria = (searchUser != null ? searchUser : "");
			request.getSession().setAttribute("GAMES_SEARCH_CRITERIA", searchGameCriteria);
			request.getSession().setAttribute("GAMES_TOTAL_TRANS", Integer.valueOf(adminService.getAlGameCount(searchGameCriteria).toString()));
        }

        ModelAndView modelAndView = new ModelAndView();
	    adminService.getAllGames(pageNumber, modelAndView, request);
	    modelAndView.setViewName("admin_games_list_view");
	    return modelAndView;
	}

	@RequestMapping(value = { "/detailGames/{gId}" }, method = RequestMethod.GET)
	public ModelAndView getGameDetails(@PathVariable("gId") String gId,
			HttpServletRequest request, HttpServletResponse response) {
		Game gameDetail = adminService.getGameDetails(gId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gameDetails", gameDetail);
		modelAndView.setViewName("admin_game_detail_view");
		return modelAndView;
	}

	@RequestMapping(value={"/redeems"}, method = RequestMethod.GET)
	public ModelAndView getAllRedeem(@RequestParam(value="p", required=false) String pageNumber,
			@RequestParam(value="searchredeem", required=false) String searchUser, HttpServletRequest request, HttpServletResponse response){

  	    if(pageNumber == null){
			String searchUserCriteria = (searchUser != null ? searchUser : "");
			request.getSession().setAttribute("REDEEM_SEARCH_CRITERIA", searchUserCriteria);	
  			request.getSession().setAttribute("REDEEM_TOTAL_TRANS", commonService.getAllSearchRedeemCount(searchUserCriteria));
		}
		
  	    ModelAndView modelAndView = new ModelAndView();
		adminService.getAllRedeemPage(pageNumber, modelAndView, request);		
		modelAndView.setViewName("admin_redeem_list_view");
		return modelAndView;
	}

	/*@RequestMapping(value={"/editRedeem/{redeemId}"}, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editRedeem(@PathVariable("redeemId") String redeemId, Model model, HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute("redeemForm", new RedeemForm());
		Redeem redeem = adminService.getRedeemById(redeemId);

		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		redeem.setCompletedTime(timeFormat.format(currentTime.getTime()));
		redeem.setTimeZone("GMT");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("redeem", redeem);
		model.addAttribute("redeemTransCategory", OptionsMapUtils.getRedeemTransCategory());
		
		UserAccountBalance balance = paymentService.getUserAvailableBalance(redeem.getUsername());
		modelAndView.addObject("balance", balance);
		modelAndView.setViewName("admin_edit_redeem");
		return modelAndView;
	}
*/
	

	@RequestMapping(value={"/editRedeem/{redeemId}"}, method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView editRedeem(@PathVariable("redeemId") String redeemId, Model model, HttpServletRequest request, HttpServletResponse response){
			
			model.addAttribute("redeemForm", new RedeemForm());
			Redeem redeem = adminService.getRedeemById(redeemId);
			
			String redeeTtraStatus = redeem.getTransStatus().toString();
			System.out.println("Complete/pending :" +redeeTtraStatus);

			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
			Calendar currentTime = DateTimeUtils.getGMTCalendar();
			redeem.setCompletedTime(timeFormat.format(currentTime.getTime()));
			redeem.setTimeZone("GMT");

			ModelAndView modelAndView = new ModelAndView();
			
			if(redeeTtraStatus.equalsIgnoreCase("Completed")){
				modelAndView.addObject("msg", "Redeem has completed and your status is :");
				modelAndView.addObject("redeem", redeem);
				model.addAttribute("redeemTransCategory", OptionsMapUtils.getRedeemTransCategory());
				
				UserAccountBalance balance = paymentService.getUserAvailableBalance(redeem.getUsername());
				modelAndView.addObject("balance", balance);
				modelAndView.setViewName("admin_edit_redeem");
			}
			else{
			modelAndView.addObject("redeem", redeem);
			model.addAttribute("redeemTransCategory", OptionsMapUtils.getRedeemTransCategory());		
			UserAccountBalance balance = paymentService.getUserAvailableBalance(redeem.getUsername());
			modelAndView.addObject("balance", balance);
			modelAndView.setViewName("admin_edit_redeem");
			}
			return modelAndView;
		}
	
	@RequestMapping(value={"/updateRedeem/{userId}"}, method = RequestMethod.POST)
	public ModelAndView updateRedeem(@PathVariable("userId") String redeemId,  @ModelAttribute("redeemForm") @Valid RedeemForm newRedeemForm,
		BindingResult result, Model model,HttpServletRequest request, HttpServletResponse response){
		Redeem redeem = adminService.getRedeemById(redeemId);
		String responseMessage = adminService.upadateRedeem(newRedeemForm, redeemId);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error",responseMessage);
		modelAndView.addObject("redeemList", adminService.getAlRedeem());
		modelAndView.setViewName("admin_edit_redeem");
		return modelAndView;
	}

	@RequestMapping(value={"/restartGames/{gId}"}, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView gameRestart(@PathVariable("gId") String gId, @RequestParam(value="pageNumber", required=false) String pageNumber,
		      @RequestParam(value="searchGame", required=false) String searchUser,HttpServletRequest request, HttpServletResponse response){
		
		Game gameU=adminService.getGameUpdate(gId);
		gameU.setStatus(String.valueOf(GameStatus.RESTARTED.getValue()));
		gameU.setMessage(GameStatus.RESTARTED.getMessage());
		commonService.updateGame(gameU);

		ModelAndView modelAndView=new ModelAndView();
	    modelAndView.addObject("game", gameU);

	    adminService.getAllGames(null, modelAndView, request);
	    modelAndView.setViewName("games_list_fragment_page");
		 
		return modelAndView;
	}
	
	@RequestMapping(value={"/cntUsQuery/reply/{contactid}*"}, method= RequestMethod.GET)
	public ModelAndView contactusReply(@PathVariable("contactid") String contactId,HttpServletRequest request, HttpServletResponse response){
		
		ContactUs contact = adminService.getContactById(contactId);		
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("contact", contact);
		modelAndView.setViewName("admin_cntUsQuery_reply_view");
		return modelAndView;
	}
	
	
	@RequestMapping(value="updateQueryRequest" , method = RequestMethod.POST)
	public ModelAndView queryrequest(@ModelAttribute("queryrequestForm") ContactUs queryRequestForm, BindingResult result, HttpServletRequest request){
		adminService.updateQueryRequest(queryRequestForm);		
		ModelAndView modelAndView = new ModelAndView();
		adminService.getAllContactUsRequest(null, modelAndView, request);
		modelAndView.setViewName("admin_contact_us_view");
		return modelAndView;
	}	
	
	/*// Friend Contacts
	@RequestMapping(value={" "}, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView saveFriendContacts(@ModelAttribute("frdContid") @Valid FriendContacts saveContact,
			BindingResult result, Model model,HttpServletRequest request, HttpServletResponse response){
		//Insert values
		adminService.friendContact(saveContact);
		
		//Update values
		adminService.updateFriendContact(saveContact);	
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName(" ");
		return modelAndView;
	}*/
	
	
		
	
}
