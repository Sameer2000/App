package com.housee.app.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.henum.KYLStatus;
import com.housee.app.henum.KYLType;
import com.housee.app.henum.TransStatus;
import com.housee.app.json.ChangePasswordForm;
import com.housee.app.json.KYLForm;
import com.housee.app.json.RegisterRoot;
import com.housee.app.json.Root;
import com.housee.app.json.UserRegistrationForm;
import com.housee.app.json.UserTotalBalance;
import com.housee.app.mail.ContactUsRequestMail;
import com.housee.app.mail.KYLRequestMail;
import com.housee.app.mail.Testmail;
import com.housee.app.model.ContactUs;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.Forgetpassword;
import com.housee.app.model.Game;
import com.housee.app.model.KYLRequest;
import com.housee.app.model.Redeem;
import com.housee.app.model.TokenTable;
import com.housee.app.model.UserAccount;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;



import com.housee.app.security.MD5Test;

import com.housee.app.session.UserSession;
import com.housee.app.util.DateTimeUtils;

public class UserServiceImpl {

	@Autowired
	private Testmail testmail;

	@Autowired
	private PaymentTransactionServiceImpl paymentService;

	@Autowired
	private CommonServiceImpl commonService;

	@Autowired
	private CommonDaoImpl commonDao;
	

	public Root registerUserProfile(UserRegistrationForm userForm){
		Root root = new Root();
		try{
			if(commonDao.isUserAlreadyExist(userForm.getEmailId())){
				throw new Exception("User already Exist");
			}

			MD5Test md5 = new MD5Test();

			
			

			UserAccount userAccount = new UserAccount();
			userAccount.setActive(true);			
			userAccount.setUsername(userForm.getEmailId());				
			userAccount.setPassword(md5.MD5HashingPassword(userForm.getPassword()));			
			UserProfile userProfile = new UserProfile();
			userProfile.setUsername(userForm.getEmailId());
			userProfile.setName(userForm.getFullName());
			userProfile.setEmail(userForm.getEmailId());
			userProfile.setPhone(userForm.getPhone());
			userProfile.setCountry(userForm.getCountry());
			userProfile.setRegistrationDate(Calendar.getInstance().getTime());
			
			commonDao.saveUser(userAccount,userProfile);
			
			root.setErrFlag(false);
			root.setErrMessage("You are Succesfully Registered");
			
		}catch(Exception e){
			e.printStackTrace();
			root.setErrFlag(true);
			root.setErrMessage(e.getMessage());
		}
		return root;
	}
	
	public RegisterRoot authenticateUserProfile(UserRegistrationForm userForm){
		RegisterRoot root = new RegisterRoot();
		try{
			if(commonDao.validateUser(userForm.getEmailId(), userForm.getPassword())){
				UserProfile userProfile = commonDao.getUserProfile(userForm.getEmailId());
				root.setErrFlag(false);
				root.getRequest().setUsername(userProfile.getUsername());
				root.getRequest().setEmailId(userProfile.getEmail());
				root.getRequest().setFullName(userProfile.getName());
				root.getRequest().setPhone(userProfile.getPhone());
				root.getRequest().setCountry(userProfile.getCountry());
			}else{
				throw new Exception("Authentication failed");
			}
		}catch(Exception e){
			root.setErrFlag(true);
			root.setErrMessage(e.getMessage());
		}
		return root;
	}
	
	
	public void contactUsRequest(ContactUs contactUs){
		commonDao.saveContactUsRequest(contactUs);
		
		try{
			ContactUsRequestMail contactRequest = new ContactUsRequestMail();
			contactRequest.email(contactUs.getName(), contactUs.getPhone(), contactUs.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<KYLRequest> getAllKYLRequest(){
		return commonDao.getAllKYLRequest(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public void kyLRequest(KYLForm kylForm){
		
		KYLRequest kylRequest = new KYLRequest();
		kylRequest.setKylStatus(KYLStatus.PENDING);
		kylRequest.setKylType(KYLType.getKYLType(kylForm.getKyl()));
		kylRequest.setRequest(kylForm.getMessage());
		UserProfile userProfile = commonDao.getUserProfile(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		kylRequest.setUserProfile(userProfile);
		kylRequest.setReqDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		kylRequest.setReqStrDateTime(timeFormat.format(currentTime.getTime()));
		kylRequest.setTimeZone("GMT");
		
		
		UserAccountBalance balance = paymentService.getUserAvailableBalance(userProfile.getUsername());
		if(balance.getTotalAvailableBalance() >= SystemSettingConstants.kylFee){
			commonDao.saveKYLRequest(kylRequest);
			paymentService.updateUserAccountOnKYLCharges(kylRequest.getUserProfile(), SystemSettingConstants.kylFee);
		}else{
			kylRequest.setSuggestion("Insufficient Balance for KYL Request! Please recharge");
			commonDao.saveKYLRequest(kylRequest);
		}
		
		try{
			KYLRequestMail kylmail = new KYLRequestMail();
			kylmail.email(kylRequest.getUserProfile().getName(), kylRequest.getKylType(), kylRequest.getRequest());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void kyLRequest(String username, KYLForm kylForm){
		
		KYLRequest kylRequest = new KYLRequest();
		kylRequest.setKylStatus(KYLStatus.PENDING);
		kylRequest.setKylType(KYLType.KYL);
		kylRequest.setRequest(kylForm.getMessage());
		UserProfile userProfile = commonDao.getUserProfile(username);
		kylRequest.setUserProfile(userProfile);
		kylRequest.setReqDateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		kylRequest.setReqStrDateTime(timeFormat.format(currentTime.getTime()));
		kylRequest.setTimeZone("GMT");

		commonDao.saveKYLRequest(kylRequest);
	}
	
	public UserProfile getUserProfile(){
		return commonDao.getUserProfile(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public void updateUserProfile(UserProfile userProfile){
		commonDao.saveUserProfile(userProfile);
	}
	
	
	public List<KYLRequest> getAllKYLRequest(String username){
		return commonDao.getAllKYLRequest(username);
	}

	public UserTotalBalance getAccountInfo(String username){
		UserTotalBalance userTotalBalance = paymentService.getUserTotalBalance(username);
		UserAccountBalance userAvailableBalance = paymentService.getUserAvailableBalance(username);
		userTotalBalance.setTotalAvailableBalance(userAvailableBalance.getTotalAvailableBalance());
		
		return userTotalBalance;
	}
	
	public UserTotalBalance getUserAvailableBalance(String username){
		UserTotalBalance userTotalBalance = new UserTotalBalance();
		UserAccountBalance userAvailableBalance = paymentService.getUserAvailableBalance(username);
		userTotalBalance.setTotalAvailableBalance(userAvailableBalance.getTotalAvailableBalance());
		return userTotalBalance;
	}
	
	public List<UserAccountBalance> getUserTransactionForPlayedGameListByUsername(String username,int pageIndex,int pageSize){
		return paymentService.getUserTransactionForPlayedGameListByUsername(username,pageIndex, pageSize);
	}
	
	public List<UserAccountBalance> getUserTransactionForRedeemListByUsername(String username,int pageIndex,int pageSize){
		return paymentService.getUserTransactionForRedeemListByUsername(username,pageIndex, pageSize);
	}

	public List<UserAccountBalance> getUserTransactionListByUsername(String username,int pageIndex, int pageSize){
		 return paymentService.getUserTransactionListByUsername(username, pageIndex, pageSize);
	}

	public void saveDeviceToken(Root root){
		DeviceMapping mapping = commonDao.getDeviceMapping(root.getRequest().getUserId());
		System.out.println("Device Type ---> " + root.getRequest().getDeviceType());
		System.out.println("Device Token ---> " + root.getRequest().getDeviceToken());
		if(mapping == null && root.getRequest().getDeviceToken() != null){
			DeviceMapping newDeviceMapping = new DeviceMapping();
			newDeviceMapping.setUsername(root.getRequest().getUserId());
			newDeviceMapping.setDeviceType(root.getRequest().getDeviceType());
			newDeviceMapping.setDeviceToken(root.getRequest().getDeviceToken());
			commonDao.saveDeviceMapping(newDeviceMapping);
		}else if(root.getRequest().getDeviceToken() != null){
			mapping.setDeviceToken(root.getRequest().getDeviceToken());
			mapping.setDeviceType(root.getRequest().getDeviceType());
			commonDao.updateDeviceMapping(mapping);
		}
	}
	
	public List<Game> getAllWinGameByUserId(){
		return commonDao.getAllWinGameByUserId(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public List<Game> getAllWinGameByUserId(int pageIndex, int pageSize){
		return commonDao.getAllWinGameByUserId(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), pageIndex, pageSize);
	}

	public Integer getAllWinGameByUserIdCount(){
		return  Integer.valueOf( String.valueOf( commonDao.getAllWinGameByUserIdCount(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername())));
	}

	public List<Game> getAllPlayedGameByUserId(){
		return commonDao.getAllPlayedGameByUserId(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public void chPwdRequest(ChangePasswordForm cpwd) {
		UserAccount userAccount = commonDao.searchUserDatabase(UserSession
				.getInstance().getCurrentlyAuthenticatedUser().getUsername());
		if (userAccount != null
				&& cpwd.getNewPassword().equals(cpwd.getConfirmPassword())) {

			MD5Test md5 = new MD5Test();

			if (userAccount.getPassword().equals(md5.MD5HashingPassword(cpwd.getOldPassword()))) {
				userAccount.setPassword(md5.MD5HashingPassword(cpwd.getNewPassword()));
				commonDao.saveUserAccount(userAccount);
			}
			
		}
	}
	

	
	public boolean forgetPasswordRequest(Forgetpassword Forget_password,String url){
		System.out.println(Forget_password.getEmail().toString()+"   Url is"+url+"======================================>>>>>>>>>>>>");
		int count = 0;
		Boolean status = testmail.sendMail(Forget_password,url);
		TokenTable tokentable =new TokenTable();
		tokentable.setTime(new Timestamp(new Date().getTime()));
		tokentable.setuserName(Forget_password.getEmail());
		tokentable.setToken(url.toString());
		tokentable.setHit(count);
		commonDao.setTokens(tokentable);
		return status;
	}
	
	public boolean setTokenStatus(int count){
		TokenTable tokentable = new TokenTable();
		tokentable.setHit(count);
		commonDao.setTokensHitstatus(tokentable);
		return true;
	}
	
	public boolean newpasswordRequest(UserAccount useraccount)
	{
		useraccount.getUsername();
		useraccount.getPassword();
		boolean status = commonDao.isUserAlreadyExist(useraccount.getUsername());
		if(status){
			commonDao.saveUserAccount(useraccount);
		}
		System.out.println();
		return true;
	}
	
	public UserAccount userAccountDetails(String username){
		return commonDao.getUserAccount(username);
	}
	
	public boolean setTokenStatus(TokenTable tokentable){
		commonDao.setTokensHitstatus(tokentable);
		return true;
	}
	
	public List<TokenTable> getTokenValue(){
		return commonDao.getTokens();
	}
	
	public void redeemRequest(Redeem redeem){
		commonDao.saveRedeemRequest(redeem);
	}
	
	public List<Redeem> getAllRedeem(String username,int pageIndex, int pageSize){
		return commonDao.getAllRedeem(username,pageIndex,pageSize);
	}
	
	public Integer getAllRedeemCount(String username){
		return commonService.getAllRedeemCount(username);
	}
	
	
	public List<UserAccountBalance> getUserAccountBalanceFragmentData(ModelAndView mv,HttpServletRequest request,String page, String username){
		return commonService.getUserAccountBalanceFragmentData(mv, request, page, username);
	}

	public List<UserPPTransaction> getUserPPTransFragmentData(ModelAndView mv,HttpServletRequest request,String page){
		return commonService.getUserPPTransactionFragmentData(mv, request, page, UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}

	public Object getAlKylCount(String su){		
		Object rowcount = commonDao.getAlKylCount(su);
		return rowcount;
	}

	public List<KYLRequest> getAllKYLUserRequest(String su,int rowIndex, int pageSize){
		return commonDao.getAllKYLUserRequest(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername(), su, rowIndex, pageSize);
	}
	
	public void getAllKYLUserRequest(String pageNumber, ModelAndView modelAndView, HttpServletRequest request){
		String sessionSearchUserCriteria = (String)request.getSession().getAttribute("SITE_KYL_SEARCH_CRITERIA");
		int pageIndex = 0;
		int totalNumberOfRecords =  Integer.valueOf(String.valueOf(request.getSession().getAttribute("SITE_KYL_TOTAL_TRANS")));
		int numberOfRecordsPerPage = SystemSettingConstants.PAGE_SIZE_MEDIUM;
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
		
		modelAndView.addObject("kyllist", getAllKYLUserRequest(sessionSearchUserCriteria, s, numberOfRecordsPerPage));
		modelAndView.addObject("pageCount", pageCount );

	}
	
	
}
