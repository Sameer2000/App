package com.housee.app.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.PGConstants;
import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.RedeemType;
import com.housee.app.henum.TransStatus;
import com.housee.app.json.ChangePasswordForm;
import com.housee.app.json.GameForm;
import com.housee.app.json.KYLForm;
import com.housee.app.json.RechargeAmountForm;
import com.housee.app.json.RedeemForm;
import com.housee.app.json.Root;
import com.housee.app.json.UserRegistrationForm;
import com.housee.app.json.UserTotalBalance;
import com.housee.app.mail.InviteFriendmail;
import com.housee.app.mail.RechargeEmailPDF;
import com.housee.app.model.ContactUs;
import com.housee.app.model.Forgetpassword;
import com.housee.app.model.Game;
import com.housee.app.model.Redeem;
import com.housee.app.model.Room;
import com.housee.app.model.TokenTable;
import com.housee.app.model.UserAccount;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;
import com.housee.app.security.MD5Test;
import com.housee.app.service.CommonServiceImpl;
import com.housee.app.service.PaymentTransactionServiceImpl;
import com.housee.app.service.UserServiceImpl;
import com.housee.app.session.UserSession;
import com.housee.app.test.data.TestCountryData;
import com.housee.app.util.DateTimeUtils;

import static com.housee.app.contants.PGConstants.*;

@Controller
@RequestMapping(value="/site")
@SessionAttributes
public class SiteControllerImpl {

	@Autowired
	private PaymentTransactionServiceImpl paymentService;
	
	@Autowired
	private UserServiceImpl userService;
	
	 
	 @Autowired
	private CommonDaoImpl commonDao;
	 
	 
	 
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home(Model model,HttpServletRequest request){
		System.out.println("Site Home age ");
		model.addAttribute("userRegForm", new UserRegistrationForm());
		
		
		ModelAndView mv = new ModelAndView("site_home");
		mv.addObject("countryMap", TestCountryData.getCountryMap());
		return mv;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView homePage(Model model,HttpServletRequest request){
		System.out.println("Welcome User!");
		ModelAndView mv = null;
		if(UserSession.getInstance().getCurrentlyAuthenticatedUser() != null){
			mv = new ModelAndView("user_my_account_page");
			int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
			int tolalTansCount = paymentService.getUserPPTransCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
			request.getSession().setAttribute("TOTAL_TRANS_COUNT", tolalTansCount);
			mv.addObject("userpptranslist", paymentService.getUserPPTransactionList(0, settingPageSize, UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername() ));
			mv.addObject("usertotalbalance",paymentService.getUserTotalBalance());
			mv.addObject("useravailablebalance",paymentService.getUserAvailableBalance());
			mv.addObject("pre","none");
			mv.addObject("next","block");
		}else{
			model.addAttribute("userRegForm", new UserRegistrationForm());
			mv = new ModelAndView("site_home");
			mv.addObject("countryMap", TestCountryData.getCountryMap());
			Root root = new Root();
			root.setErrMessage("Username or Password is incorrect");
			mv.addObject("err",root.getErrMessage());
		}
		return mv;
	}
	
	@RequestMapping(value="forward/{pageName}")
	public ModelAndView forward(@PathVariable("pageName") String pageName){
		System.out.println("Site Forward To("+pageName+") Page");
		return new ModelAndView("user_"+pageName);
	}
	
	@RequestMapping(value="my_profile_page")
	public ModelAndView myProfilePage(){
		UserProfile usrprf = userService.getUserProfile();
		System.out.println("View User Profile --> " + usrprf.getUsername());
		ModelAndView mv = new ModelAndView("user_my_profile_page");
		mv.addObject("usrprf", usrprf);
		mv.addObject("tmp", "tmp");
		return mv;
	}
	
	@RequestMapping(value="my_account_page")
	public ModelAndView myAccountPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("user_my_account_page");
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int tolalTansCount = paymentService.getUserPPTransCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		
		request.getSession().setAttribute("PP_TRANS_PAGE", 0);
		request.getSession().setAttribute("PP_TOTAL_TRANS", tolalTansCount);

		mv.addObject("userpptranslist", paymentService.getUserPPTransactionList(0, settingPageSize, UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername() ));
		mv.addObject("usertotalbalance",paymentService.getUserTotalBalance());
		mv.addObject("useravailablebalance",paymentService.getUserAvailableBalance());
		mv.addObject("pre","none");
		mv.addObject("next","block");
		return mv;
	}
	
	@RequestMapping(value="fragment_account_pptrans_page")
	public ModelAndView getFragmentAccoutTrans(@RequestParam(value="p", required=false) String page, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("fragment_pptrans_page");
		List<UserPPTransaction> userTransList = userService.getUserPPTransFragmentData(mv, request, page);
		mv.addObject("userpptranslist", userTransList);
		return mv;

	}

	
	/*@RequestMapping(value="game_history_page")
	public ModelAndView gameHistoryPage(@RequestParam(value="p", required=false) String page,  HttpServletRequest request){
		ModelAndView mv  = new ModelAndView("user_game_history_page");
		
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		Integer pageIndex = (Integer)request.getSession().getAttribute("GAME_TRANS_PAGE");
		Integer totalTransCount = (Integer)request.getSession().getAttribute("GAME_TOTAL_TRANS");
		if(page == null || page.trim().equalsIgnoreCase("") || totalTransCount == null){
			totalTransCount = userService.getAllWinGameByUserIdCount();
			pageIndex = 0;
			request.getSession().setAttribute("GAME_TOTAL_TRANS", totalTransCount);
			mv.addObject("pre","none");
			mv.addObject("next","block");
		}else
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
		List<Game> gameList = userService.getAllWinGameByUserId(pageIndex, settingPageSize);
		mv.addObject("wingamelist", gameList);
		if(gameList != null && gameList.size() > 0){
			request.getSession().setAttribute("GAME_TRANS_PAGE", pageIndex);
		}
		return mv;
	}*/
	
	@RequestMapping(value="game_history_page")
	public ModelAndView gameHistoryPage(@RequestParam(value="p", required=false) String page,  HttpServletRequest request){
	
		ModelAndView mv  = new ModelAndView("user_game_history_page");
		try{
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		Integer pageIndex = (Integer)request.getSession().getAttribute("GAME_TRANS_PAGE");
		Integer totalTransCount = (Integer)request.getSession().getAttribute("GAME_TOTAL_TRANS");
		if(page == null || page.trim().equalsIgnoreCase("") || totalTransCount == null){
			totalTransCount = userService.getAllWinGameByUserIdCount();
			pageIndex = 0;
			request.getSession().setAttribute("GAME_TOTAL_TRANS", totalTransCount);
			mv.addObject("pre","none");
			mv.addObject("next","block");
		}else
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
		List<Game> gameList = userService.getAllWinGameByUserId(pageIndex, settingPageSize);
		mv.addObject("wingamelist", gameList);
		if(gameList != null && gameList.size() > 0){
			request.getSession().setAttribute("GAME_TRANS_PAGE", pageIndex);
		}
		}catch (Exception e) {
			System.out.println("E R R O R :"+e.getMessage());
		}
		return mv;
	}

	
	@RequestMapping(value="dashboard_page")
	public ModelAndView dashboardPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("user_dashboard_page");
		UserTotalBalance totalBalance = userService.getUserAvailableBalance(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		mv.addObject("uab",totalBalance);
		mv.addObject("pgLst",paymentService.getUserTransactionForPlayedGameListByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(),0, settingPageSize));
		mv.addObject("rdLst",paymentService.getUserTransactionForRedeemListByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(),0, settingPageSize));
		mv.addObject("LstTrans",paymentService.getUserTransactionListByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(),0, settingPageSize));
		int totalTransCount = paymentService.getUserAccountBalanceCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		request.getSession().setAttribute("TRANS_PAGE", 0);
		request.getSession().setAttribute("TOTAL_TRANS", totalTransCount);
		mv.addObject("pre","none");
		mv.addObject("next","block");
		return mv;
	}
	
	@RequestMapping(value="fragment_trans_page")
	public ModelAndView getTransFragmentPage(@RequestParam(value="p", required=false) String page, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("fragment_trans_page");
		List<UserAccountBalance> userAccountBal = userService.getUserAccountBalanceFragmentData(mv, request, page, UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		mv.addObject("LstTrans",userAccountBal);
		return mv;
		
	}
	
	@RequestMapping(value="recharge_amount_page")
	public ModelAndView rechargeAmountPage(){
		return new ModelAndView("user_recharge_amount_page");
	}
	
	@RequestMapping(value="private_room")
	public ModelAndView privateRoom(){
		return new ModelAndView("user_private_room_page");
	}
	
	@RequestMapping(value="kyl_page" )
	public ModelAndView kylPage(@RequestParam(value="pageNumber", required=false) String pageNumber,
	@RequestParam(value="search1", required=false) String searchKylRequest, HttpServletRequest request, HttpServletResponse response){
				
		if(pageNumber == null){
			String searchKylRequestCriteria = (searchKylRequest != null ? searchKylRequest : "");
			request.getSession().setAttribute("SITE_KYL_SEARCH_CRITERIA", searchKylRequestCriteria);	
			request.getSession().setAttribute("SITE_KYL_TOTAL_TRANS", Integer.valueOf(userService.getAlKylCount(searchKylRequestCriteria).toString()));

		}
		 
		ModelAndView modelAndView = new ModelAndView();
		userService.getAllKYLUserRequest(pageNumber, modelAndView, request);
		modelAndView.addObject("KYLFee", SystemSettingConstants.kylFee);
		modelAndView.setViewName("user_kyl_page");
		return modelAndView;
	}
	
	@RequestMapping(value="change_password_page")
	public ModelAndView changePasswordPage(){
		return new ModelAndView("user_change_password_page");
	}
	/*
	@RequestMapping(value="regUser")
	public ModelAndView RegisterUser(@ModelAttribute("userRegForm") UserRegistrationForm userRegForm,
							BindingResult result){
		System.out.println("User Email Id --> " + userRegForm.getEmailId());
		ModelAndView mv = new ModelAndView("user_new_login");
		Root root = userService.registerUserProfile(userRegForm);
		mv.addObject("error", root.getErrMessage());
			
		return mv;
	}*/
	

	
	

	@RequestMapping(value="loginsuccess")
	public String RegisterUser(){
//		return new ModelAndView("user_welcome_page");
//		return new ModelAndView("user_dashboard_page");
		return  "redirect:/happ/site/dashboard_page";
	}
	
	@RequestMapping(value="contactus")
	public ModelAndView contactUsPage(){
		return new ModelAndView("site_contact_us");
	}

	@RequestMapping(value="download")
	public ModelAndView downloadPage(){
		return new ModelAndView("site_download");
	}

	@RequestMapping(value="astrology")
	public ModelAndView astrologyPage(){
		return new ModelAndView("site_astrology");
	}

	@RequestMapping(value="contactus" , method = RequestMethod.POST)
	public ModelAndView contactus(@ModelAttribute("contactUsForm") ContactUs contactUsForm,
			BindingResult result){

		userService.contactUsRequest(contactUsForm);
		ModelAndView mv = new ModelAndView("site_contact_us");
		return mv;
	}
	
	@RequestMapping(value="savekyl", method = RequestMethod.POST)
	public ModelAndView saveKYLRequest(@ModelAttribute("kylForm") KYLForm kylForm, BindingResult result , HttpServletRequest request, HttpServletResponse response){

		userService.kyLRequest(kylForm);

		ModelAndView modelAndView = new ModelAndView();
		userService.getAllKYLUserRequest(null, modelAndView, request);
		modelAndView.addObject("KYLFee", SystemSettingConstants.kylFee);
		modelAndView.setViewName("user_kyl_page");
		return modelAndView;
	}
	
	@RequestMapping(value="cpwd" , method = RequestMethod.POST)
	public ModelAndView changePasswordRequest(@ModelAttribute("cpwdAttri") ChangePasswordForm cpwd,BindingResult result){
		ModelAndView mv = new ModelAndView("user_change_password_page");
	    userService.chPwdRequest(cpwd);
		return mv;
		
	}

	@RequestMapping(value="played_game_page")
	public ModelAndView getAllPlayedGame(){
		ModelAndView mv  = new ModelAndView("user_played_game_page");
		mv.addObject("playedgamelist", userService.getAllPlayedGameByUserId());
		return mv;
	}

	@RequestMapping(value="my_profile_page" , method = RequestMethod.POST)
	public ModelAndView saveMyProfilePage(@ModelAttribute("userProfileForm") UserProfile userProfileForm, BindingResult result , 
									HttpServletRequest request, HttpServletResponse response){
		UserProfile usrprf = userService.getUserProfile();
		System.out.println("View User Profile --> " + userProfileForm.getAddress1());
		userProfileForm.setId(usrprf.getId());
		userProfileForm.setUsername(usrprf.getUsername());
 
		userProfileForm.setRegistrationDate(usrprf.getRegistrationDate());
 
		userService.updateUserProfile(userProfileForm);
		ModelAndView mv = new ModelAndView("user_my_profile_page");
		mv.addObject("usrprf", userProfileForm);
		return mv;
	}
	
	@RequestMapping(value="recharge_amount_page" , method = RequestMethod.POST)
	public ModelAndView rechargeAmountPage(@ModelAttribute("rechargeAmountForm") RechargeAmountForm rechargeAmountForm, BindingResult result , 
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv  = new ModelAndView("user_recharge_amount_confirm_page");
		mv.addObject("rechargeamount",rechargeAmountForm.getAmount());
		return mv;
	}

	@RequestMapping(value="recharge_amount_confirm_page" , method = RequestMethod.POST)
	public ModelAndView confirmRechargeAmountPage(@ModelAttribute("rechargeAmountForm") RechargeAmountForm rechargeAmountForm, BindingResult result , 
			HttpServletRequest request, HttpServletResponse response){
		
		request.getSession().setAttribute("PPRecharge", rechargeAmountForm);
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		mv.addObject("URL","paypal/pg/submit");
		mv.addObject("message","Please wait while you have been transfered to Paypal Payment Gateway");
		return mv;
	}

	@RequestMapping(value="paypal/pg/submit")
	public String submitPPTransaction(HttpServletRequest request, HttpServletResponse response){
		RechargeAmountForm rechargeAmountForm = (RechargeAmountForm)request.getSession().getAttribute("PPRecharge");
		boolean status = paymentService.initPPTransaction(rechargeAmountForm, request);
		System.out.println("SESSION PP TOKEN ---> " + request.getSession().getAttribute("PPTOKEN"));
		String redirectStr = "";
		if(!status){
			redirectStr = "/happ/site/paypal/pg/failed";
		}else{
			redirectStr = PGConstants.ppPaymentGateway+request.getSession().getAttribute("PPTOKEN");
		}
		
		System.out.println("REDIRECT STR ---> " + redirectStr);
		return "redirect:"+ redirectStr;
	}
	
	
	@RequestMapping(value="paypal/pg/failed")
	public ModelAndView failedPPTransaction(HttpServletRequest request){
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		mv.addObject("URL",request.getContextPath() + "/happ/site/home");
		mv.addObject("message","Your Transaction has been Failed, Please try again later.");
		return mv;
	}
	
	@RequestMapping(value="paypal/pg/cancel")
	public ModelAndView cancelPPTransaction(HttpServletRequest request){
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		mv.addObject("URL",request.getContextPath() + "/happ/site/home");
		mv.addObject("message","Your Transaction has been Cancelled");
		return mv;
	}

	@RequestMapping(value="paypal/pg/return")
	public ModelAndView returnPPTransaction(HttpServletRequest request, HttpServletResponse response){
		
		String token = request.getParameter("token");
		String sessionToken = (String)request.getSession().getAttribute("PPTOKEN");
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		if(token.equalsIgnoreCase(sessionToken)){
			boolean status = paymentService.getDetailPPTransaction(sessionToken,request);
			if(status){
				mv.addObject("URL",request.getContextPath() + "/happ/site/onSuccessPPTrans");
				mv.addObject("message","Please wait while, Your Transaction has been processed");
			}else{
				mv.addObject("URL",request.getContextPath() + "/happ/site/home");
				mv.addObject("message","Please wait while, Your Transaction has been failed");
			}
		}else{
			mv.addObject("URL",request.getContextPath() + "/happ/site/home");
			mv.addObject("message","Please wait while, Your Transaction has been failed");
		}
		return mv;
	}
	
	@RequestMapping(value="onSuccessPPTrans")
	public String onSuccessPPTransaction(HttpServletRequest request, HttpServletResponse response){
		boolean status = paymentService.commitPPTransaction(request);
		if(status){
			List<UserAccountBalance> balance = userService.getUserTransactionListByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), 0, 1);
			UserProfile user = userService.getUserProfile();
			UserSession.getInstance().getCurrentlyAuthenticatedUser();
			RechargeEmailPDF sendMail = new RechargeEmailPDF();
			sendMail.email(user.getName(), String.valueOf( balance.get(0).getRechargeAmount()), String.valueOf( balance.get(0).getTotalAvailableBalance()));
			return  "redirect:/happ/site/home";
		}else{
			return  "redirect:/happ/site/paypal/pg/failed";
		}
	}
	


		
	
/*	@RequestMapping(value="onSuccessPPTrans")
	public String inviteFriend(@ModelAttribute GameForm gameForm,HttpServletRequest request, HttpServletResponse response){
		String contact = "Manish Panchal";
		List<Game> roomList = commonDao.getAllGameRooms(new Long("s"));
		
		for(Game gameroom:roomList){
			if(gameForm.getRoomId()==gameroom.getRoom().getId())
			{
				System.out.println("Room Id===>"+gameForm.getRoomId());
				break;
			}
		}
		InviteFriendmail inviteFriend = new InviteFriendmail();
		inviteFriend.email(contact, gameForm.getRoomId(), "manish@innverse.com", "sameer@innverse.com");
		return  "redirect:/happ/site/home";
	}*/
	

	/*@RequestMapping(value="forgetpassword" , method = RequestMethod.POST)
>>>>>>> .r36
	public ModelAndView forget(@ModelAttribute("Forget_password") Forgetpassword Forget_password,
			BindingResult result){
		int count = 0;
		userService.setTokenStatus(count++);
		Double token = (Double)Math.random();
		String baseurl =  host + "/happ/site/genpass/";   ///our site base url
		StringBuilder url= new StringBuilder(baseurl);
		url.append(token.toString());  // appending baseurl with token
		Boolean status = userService.forgetPasswordRequest(Forget_password,url.toString());  //sending email with baseurl with token
		ModelAndView mv = new ModelAndView("site_forget_password");
		if(status==true){
			mv.addObject("msg", "your forget Password link has been sent to your Email");
			mv.addObject("token", token.toString());
		}
		return mv;
	}*/	
	
	@RequestMapping(value="site_forget_password" , method = RequestMethod.GET)
	public ModelAndView resendforget(@ModelAttribute("site_forget_password") Forgetpassword Forget_password,BindingResult result){
		ModelAndView mv=new ModelAndView("site_forget_password");
		return mv;
	}
	
			
			
	@RequestMapping(value={"genpass/{token}"}, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Newpassword(@PathVariable("token") String token, HttpServletRequest request, HttpServletResponse response){
		
		StringBuffer path = request.getRequestURL();
		List<TokenTable> tokens = userService.getTokenValue();
		ModelAndView mv=new ModelAndView("site_New_Password");
		for(TokenTable tokentable:tokens){
			if(path.toString().equals(tokentable.getToken())) {
				int hitCount = tokentable.getHit()+1;
				
				if(hitCount>1){
					System.out.println(hitCount+"count=======>");
					mv.addObject("msg", "Link is not valid");					
					break;
				}
				else{
					tokentable.setHit(hitCount);
					userService.setTokenStatus(tokentable);
					mv.addObject("usr", tokentable.getuserName().toString());
					mv.addObject("id", tokentable.getId());
					break;
				}
			}
		}
			return mv;
	}
	
	
	
	
	@RequestMapping(value={"genpass/newPassword"}, method = RequestMethod.POST)
	public String newPassword(@ModelAttribute("newpasswordForm") UserAccount useraccount ,BindingResult result){
		UserAccount dbUserAccount  = userService.userAccountDetails(useraccount.getUsername());
		if(dbUserAccount != null){
			MD5Test md5 = new MD5Test();
			dbUserAccount.setPassword(md5.MD5HashingPassword(useraccount.getPassword()));
			userService.newpasswordRequest(dbUserAccount);
		}
		return "redirect:/happ/site/home";
	}
	
	@RequestMapping(value="redeemSubmit" , method = RequestMethod.POST)
	public ModelAndView redeemRequest(@ModelAttribute("redeemAttri") @Valid RedeemForm redeemForm,
			BindingResult result, Model model,HttpServletRequest request, HttpServletResponse response){
			 
		int tolalTansCount = paymentService.getUserPPTransCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user_redeem");
		
		Redeem redeem = new Redeem();
		redeem.setUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		UserAccountBalance balance = paymentService.getUserAvailableBalance(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		if(RedeemType.getRedeemType(redeemForm.getRedeemType()).equals(RedeemType.FULL)){
			if(balance.getTotalAvailableBalance() ==0){
			redeem.setMessage("Nil Balance.");
			}
			redeem.setAmount(balance.getTotalAvailableBalance());
			redeem.setRedeemType(RedeemType.getRedeemType(redeemForm.getRedeemType()));
		}
		else
		{	redeem.setAmount(redeemForm.getAmount());
			redeem.setRedeemType(RedeemType.getRedeemType(redeemForm.getRedeemType()));
			if(balance.getTotalAvailableBalance() ==0){
				redeem.setMessage("Nil-Balance.");
			}else if(balance.getTotalAvailableBalance() >= redeemForm.getAmount()){
				redeem.setRedeemType(RedeemType.getRedeemType(redeemForm.getRedeemType()));
				redeem.setMessage("Expected Time to redeem Amount within 48 hours.");
			}else{
				redeem.setTransStatus(TransStatus.Cancelled);
				redeem.setMessage("Insufficient Balance!");
			}
			modelAndView.addObject("useravailablebalance",paymentService.getUserAvailableBalance());
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		redeem.setRequestedTime(timeFormat.format(currentTime.getTime()));
		redeem.setTimeZone("GMT");
		
		redeem.setTransStatus(TransStatus.PENDING);
		userService.redeemRequest(redeem);

		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int rowCount = userService.getAllRedeemCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		List<Redeem> redeemList = userService.getAllRedeem(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), 0, settingPageSize);
		modelAndView.addObject("redeemList",redeemList);
		request.getSession().setAttribute("REDEEM_TRANS_PAGE", 0);
		request.getSession().setAttribute("REDEEM_TOTAL_TRANS", rowCount);
		modelAndView.addObject("pre","none");
		modelAndView.addObject("next","block");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "redeem")
	public ModelAndView getAllRedeem(Model model,HttpServletRequest request) {
		model.addAttribute("redeemForm", new RedeemForm());
		ModelAndView mv = new ModelAndView("user_redeem");
		int tolalTansCount = paymentService.getUserPPTransCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int rowCount = userService.getAllRedeemCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		List<Redeem> redeemList = userService.getAllRedeem(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), 0, settingPageSize);
		mv.addObject("redeemList",redeemList);
		request.getSession().setAttribute("REDEEM_TRANS_PAGE", 0);
		request.getSession().setAttribute("REDEEM_TOTAL_TRANS", rowCount);
		mv.addObject("pre","none");
		mv.addObject("next","block");
		mv.addObject("useravailablebalance",paymentService.getUserAvailableBalance());
		return mv;
	}
	
	@RequestMapping(value="fragment_redeem_page")
	public ModelAndView getAllRedeemFragment(@RequestParam(value="p",required=false)String page,
			HttpServletRequest request, HttpServletResponse response){
		int settingPageSize = SystemSettingConstants.PAGE_SIZE_MEDIUM;
		int pageIndex = (Integer)request.getSession().getAttribute("REDEEM_TRANS_PAGE");
		int totalTransCount = (Integer)request.getSession().getAttribute("TOTAL_TRANS");
		ModelAndView mv = new ModelAndView("fragment_redeem_page");
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

		List<Redeem> redeemList = userService.getAllRedeem(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), pageIndex, settingPageSize);
		mv.addObject("redeemList",redeemList);
		if(redeemList != null && redeemList.size() > 0){
			request.getSession().setAttribute("REDEEM_TRANS_PAGE", pageIndex);
		}

		return mv;
	}
	
	@RequestMapping(value="forgetpassword" , method = RequestMethod.POST)
	public ModelAndView forget(@ModelAttribute("Forget_password") Forgetpassword Forget_password,
			BindingResult result){

		ModelAndView mv = new ModelAndView("site_forget_password");
		System.out.println("forget entered :");
		boolean validUser = commonDao.isUserAlreadyExist(Forget_password.getEmail());
		System.out.println("user profile email :"+commonDao.isUserAlreadyExist(Forget_password.getEmail()));
		if(validUser)
		{			
		Double token = (Double)Math.random();
		String baseurl =  host + "/happ/site/genpass/";   ///our site base url
		StringBuilder url= new StringBuilder(baseurl);
		url.append(token.toString());  // appending baseurl with token
		Boolean status = userService.forgetPasswordRequest(Forget_password,url.toString());  //sending email with baseurl with token
		
		if(status==true){
			mv.addObject("msg", "your forget Password link has been sent to your Email");
			mv.addObject("token", token.toString());
			}
				return mv;
		}
		else{
			mv.addObject("msg", "you are not a valid user");
			return mv;
		}
	}
							 
							
							


	@RequestMapping(value="regUser")

	public ModelAndView RegisterUser(@ModelAttribute("userRegForm") UserRegistrationForm userRegForm,
							BindingResult result){
		System.out.println("User Email Id --> " + userRegForm.getEmailId());
		System.out.println("User Country Id --> " + userRegForm.getCountry());
		ModelAndView mv =new ModelAndView();
		
		if(userRegForm.getEmailId()!="" && userRegForm.getPassword()!="" && userRegForm.getFullName()!="" && !userRegForm.getCountry().equals("NONE"))
		{
			Root root = userService.registerUserProfile(userRegForm);
			mv.setViewName("user_new_login");
			mv.addObject("error", root.getErrMessage());
			return mv;	
		}
		else{
			mv.setViewName("site_home");
			mv.addObject("countryMap", TestCountryData.getCountryMap());
			return mv;
		}
		
	}
	
	
}
