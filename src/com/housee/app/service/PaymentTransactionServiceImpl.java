package com.housee.app.service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.housee.app.json.RechargeAmountForm;
import com.housee.app.model.UserPPTransaction;


public class PaymentTransactionServiceImpl {

	private ECSetExpressCheckout ppSetEC;
	private ECGetExpressCheckout ppGetEC;
	private ECDoExpressCheckout ppDoEC;
	
	
	public ECSetExpressCheckout getPpSetEC() {
		return ppSetEC;
	}

	public void setPpSetEC(ECSetExpressCheckout ppSetEC) {
		this.ppSetEC = ppSetEC;
	}

	public ECGetExpressCheckout getPpGetEC() {
		return ppGetEC;
	}

	public void setPpGetEC(ECGetExpressCheckout ppGetEC) {
		this.ppGetEC = ppGetEC;
	}

	public ECDoExpressCheckout getPpDoEC() {
		return ppDoEC;
	}

	public void setPpDoEC(ECDoExpressCheckout ppDoEC) {
		this.ppDoEC = ppDoEC;
	}



	@Autowired
	private PaymentDaoImpl paymentDao;
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
	public boolean initPPTransactionMobile(RechargeAmountForm rechargeAmountForm, PaypalTrans pp,String username){
		try{
			UserPPTransaction trans = new UserPPTransaction();
			trans.setAmount(rechargeAmountForm.getAmount());
			trans.setPaymentGateway(rechargeAmountForm.getPaymentgateway());
			double totalAmount = rechargeAmountForm.getAmount();
			trans.setTotalAmount(totalAmount);
			trans.setCurrencycode(rechargeAmountForm.getCurrencycode());
			trans.setUsername(username);
			trans.setPaymentType(PGConstants.paymentType);
			trans.setCurrencycode(rechargeAmountForm.getCurrencycode());
			
			Map<String,String> result = null;
			
			String rURL = iphost + mobileReturnURL;
			String cURL = iphost + mobileCancelURL;
			result = ppSetEC.mECSetExpressCheckout(rURL, cURL, String.valueOf(totalAmount), paymentType, rechargeAmountForm.getCurrencycode());
			
			System.out.println("SET EC ---> " + result.toString());
			System.out.println("PP TOKEN --->  " + result.get("TOKEN"));
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				pp.setToken(result.get("TOKEN"));
				System.out.println("RETRIEVE SESSION PP TOKEN ---> " + pp.getToken());
				trans.setToken(result.get("TOKEN"));
				
				timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				Calendar currentTime = DateTimeUtils.getGMTCalendar();
				trans.setTransTime(timeFormat.format(currentTime.getTime()));
				trans.setTimeZone("GMT");
				
				paymentDao.savePaymentTransaction(trans);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	public boolean initPPTransaction(RechargeAmountForm rechargeAmountForm, HttpServletRequest request){
		try{
			UserPPTransaction trans = new UserPPTransaction();
			trans.setAmount(rechargeAmountForm.getAmount());
			trans.setPaymentGateway(rechargeAmountForm.getPaymentgateway());
			double totalAmount = rechargeAmountForm.getAmount();
			trans.setTotalAmount(totalAmount);
			trans.setCurrencycode(rechargeAmountForm.getCurrencycode());
			trans.setUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
			trans.setPaymentType(PGConstants.paymentType);
			trans.setCurrencycode(rechargeAmountForm.getCurrencycode());

			Map<String,String> result = null;
			String rURL = host + returnURL;
			String cURL = host + cancelURL;
			result = ppSetEC.mECSetExpressCheckout(rURL, cURL, String.valueOf(totalAmount), paymentType, rechargeAmountForm.getCurrencycode());
			
			System.out.println("SET EC ---> " + result.toString());
			System.out.println("PP TOKEN --->  " + result.get("TOKEN"));
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				request.getSession().setAttribute("PPTOKEN", result.get("TOKEN"));
				System.out.println("RETRIEVE SESSION PP TOKEN ---> " + request.getSession().getAttribute("PPTOKEN"));

				timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				Calendar currentTime = DateTimeUtils.getGMTCalendar();
				trans.setTransTime(timeFormat.format(currentTime.getTime()));
				trans.setTimeZone("GMT");
				trans.setToken(result.get("TOKEN"));
				paymentDao.savePaymentTransaction(trans);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean getDetailPPTransactionMobile(String token,HttpServletRequest request){
		try{
			Map<String,String> result = ppGetEC.mECGetExpressCheckout(token);
			System.out.println("GET EC ----> " + result.toString());
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 UserPPTransaction ppTransaction = paymentDao.getUserPPTransactionByToken(token);
				 ppTransaction.setPayerId(result.get("PAYERID"));
				 ppTransaction.setTransStatus(result.get("PAYERSTATUS"));
				 paymentDao.mergePaymentTransaction(ppTransaction);
				 request.getSession().setAttribute("PPTrans", ppTransaction);
				 return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public boolean getDetailPPTransaction(String token,HttpServletRequest request){
		try{
			Map<String,String> result = ppGetEC.mECGetExpressCheckout(token);
			System.out.println("GET EC ----> " + result.toString());
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 UserPPTransaction ppTransaction = paymentDao.getUserPPTransactionByToken(token);
				 ppTransaction.setPayerId(result.get("PAYERID"));
				 ppTransaction.setTransStatus(result.get("PAYERSTATUS"));
				 paymentDao.mergePaymentTransaction(ppTransaction);
				 request.getSession().setAttribute("PPTrans", ppTransaction);
				 return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean commitPPTransactionMobile(String token){
		try{
			UserPPTransaction ppTransaction = paymentDao.getUserPPTransactionByToken(token);
			Map<String,String> result = ppDoEC.mECDoExpressCheckout(ppTransaction.getToken(), ppTransaction.getPayerId(), String.valueOf(ppTransaction.getTotalAmount()) , ppTransaction.getPaymentType(), ppTransaction.getCurrencycode());
			System.out.println("DO EC ---> " + result.toString());
			if(ppTransaction != null && result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 ppTransaction.setTransactionId(result.get("TRANSACTIONID"));
				 ppTransaction.setTransStatus(result.get("PAYMENTSTATUS"));
				 ppTransaction.setFeeAmount(Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setAmount(ppTransaction.getTotalAmount() - Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setORDERTIME(result.get("ORDERTIME"));
				 ppTransaction.setTIMESTAMP(result.get("TIMESTAMP"));
				 
				 UserAccountBalance userAccBal = null;
				 try{
					 userAccBal = paymentDao.getLastUserAccBalByUsername(ppTransaction.getUsername());
				 }catch(Exception e){
						e.printStackTrace();
				 }

				 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
				 if(userAccBal != null){
					 userAccBalNewRecord.setUsername(ppTransaction.getUsername());
					 userAccBalNewRecord.setRechargeAmount(ppTransaction.getAmount());
					 userAccBalNewRecord.setServiceFeeAmount(ppTransaction.getFeeAmount());
					 userAccBalNewRecord.setTotalAvailableBalance(userAccBal.getTotalAvailableBalance() + ppTransaction.getAmount());
					 
				 }else{
					 userAccBalNewRecord.setUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
					 userAccBalNewRecord.setRechargeAmount(ppTransaction.getAmount());
					 userAccBalNewRecord.setServiceFeeAmount(ppTransaction.getFeeAmount());
					 userAccBalNewRecord.setTotalAvailableBalance(ppTransaction.getAmount());
				 }
				 SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 
				 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				 Calendar currentTime = DateTimeUtils.getGMTCalendar();
				 
				 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
				 userAccBalNewRecord.setTimeZone("GMT");
				 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
				 
				ppTransaction.setTransTime(timeFormat.format(currentTime.getTime()));
				ppTransaction.setTimeZone("GMT");

				 paymentDao.finalPaymentTransaction(ppTransaction,userAccBalNewRecord);
				 return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean commitPPTransaction(HttpServletRequest request){
		try{
			 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);

			UserPPTransaction sessionPPTransaction =  (UserPPTransaction)request.getSession().getAttribute("PPTrans");
			Map<String,String> result = ppDoEC.mECDoExpressCheckout(sessionPPTransaction.getToken(), sessionPPTransaction.getPayerId(), String.valueOf(sessionPPTransaction.getTotalAmount()) , sessionPPTransaction.getPaymentType(), sessionPPTransaction.getCurrencycode());
			System.out.println("DO EC ---> " + result.toString());
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 UserPPTransaction ppTransaction = paymentDao.getUserPPTransactionByToken(sessionPPTransaction.getToken());
				 ppTransaction.setTransactionId(result.get("TRANSACTIONID"));
				 ppTransaction.setTransStatus(result.get("PAYMENTSTATUS"));
				 ppTransaction.setFeeAmount(Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setAmount(ppTransaction.getTotalAmount() - Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setORDERTIME(result.get("ORDERTIME"));
				 ppTransaction.setTIMESTAMP(result.get("TIMESTAMP"));
				 
				 UserAccountBalance userAccBal = null;
				 try{
					 userAccBal = paymentDao.getLastUserAccBalByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
				 }catch(Exception e){
						e.printStackTrace();
				 }

				 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
				 
				 if(userAccBal != null){
					 userAccBalNewRecord.setUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
					 userAccBalNewRecord.setRechargeAmount(ppTransaction.getAmount());
					 userAccBalNewRecord.setServiceFeeAmount(ppTransaction.getFeeAmount());
					 userAccBalNewRecord.setTotalAvailableBalance(NumberFormatUtil.twoDecimalDigits( userAccBal.getTotalAvailableBalance() + ppTransaction.getAmount()));
				 }else{
					 userAccBalNewRecord.setUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
					 userAccBalNewRecord.setRechargeAmount(NumberFormatUtil.twoDecimalDigits(ppTransaction.getTotalAmount()));
					 userAccBalNewRecord.setServiceFeeAmount(ppTransaction.getFeeAmount());
					 userAccBalNewRecord.setTotalAvailableBalance(ppTransaction.getAmount());
				 }
				 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				 Calendar currentTime = DateTimeUtils.getGMTCalendar();
				 
				 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
				 userAccBalNewRecord.setTimeZone("GMT");
				 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
				 
				ppTransaction.setTransTime(timeFormat.format(currentTime.getTime()));
				ppTransaction.setTimeZone("GMT");

				paymentDao.finalPaymentTransaction(ppTransaction,userAccBalNewRecord);
				 return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<UserPPTransaction> getUserPPTransactionList(int pageIndex, int pageSize, String username){
		return paymentDao.getUserPPTransactionListByUsername(username, pageIndex, pageSize);
	}
	
	public List<UserPPTransaction> getUserAllPPTransactionListByUsername(int pageIndex, int pageSize, String username){
		return paymentDao.getUserAllPPTransactionListByUsername(username, pageIndex, pageSize);
	}
	
	public UserTotalBalance getUserTotalBalance(){
		return paymentDao.getUserTotalAccBalByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public UserAccountBalance getUserAvailableBalance(){
		 return paymentDao.getLastUserAccBalByUsername(UserSession.getInstance().getCurrentlyAuthenticatedUser().getUsername());
	}
	
	public UserTotalBalance getUserTotalBalance(String username){
		return paymentDao.getUserTotalAccBalByUsername(username);
	}
	
	public UserAccountBalance getUserAvailableBalance(String username){
		 return paymentDao.getLastUserAccBalByUsername(username);
	}
	
	public List<UserAccountBalance> getUserTransactionForPlayedGameListByUsername(String username,int pageIndex,int pageSize){
		 return paymentDao.getUserTransactionForPlayedGameListByUsername(username,pageIndex, pageSize);
	}
	
	public List<UserAccountBalance> getUserTransactionForRedeemListByUsername(String username,int pageIndex,int pageSize){
		 return paymentDao.getUserTransactionForRedeemListByUsername(username,pageIndex, pageSize);
	}

	public List<UserAccountBalance> getUserTransactionListByUsername(String username,int firstPage,int pageSize){
		 return paymentDao.getUserTransactionListByUsername(username, firstPage, pageSize);
	}

	public Integer getUserAccountBalanceCount(String username){
		 return  Integer.valueOf(String.valueOf(paymentDao.getUserAccountBalanceCount(username)));
	}
	
	public Integer getUserPPTransCount(String username){
		 return  Integer.valueOf(String.valueOf(paymentDao.getUserAccountBalanceCount(username)));
	}


	public void updateUserAccountOnConfirmTicket(Player player){
		UserAccountBalance userAccountBalance = paymentDao.getLastUserAccBalByUsername(player.getUser().getUsername());
		
		 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
		 userAccBalNewRecord.setUsername(player.getUser().getUsername());
		 userAccBalNewRecord.setGameId(player.getGame().getId());
		 userAccBalNewRecord.setPlayedGameAmount(player.getGame().getRoom().getPrice());
		 userAccBalNewRecord.setTotalAvailableBalance(NumberFormatUtil.twoDecimalDigits(userAccountBalance.getTotalAvailableBalance() - player.getGame().getRoom().getPrice()));
			 
		 SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		 Calendar currentTime = DateTimeUtils.getGMTCalendar();
		 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
		 userAccBalNewRecord.setTimeZone("GMT");
		 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
		 paymentDao.updateAccountBalance(userAccBalNewRecord);
	}
	
	public void updateUserAccountOnRedeemAccount(UserProfile user, double amount){
		UserAccountBalance userAccountBalance = paymentDao.getLastUserAccBalByUsername(user.getUsername());
		
		 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
		 userAccBalNewRecord.setUsername(user.getUsername());
		 userAccBalNewRecord.setRedeemAmount(NumberFormatUtil.twoDecimalDigits(amount));
		 userAccBalNewRecord.setTotalAvailableBalance(NumberFormatUtil.twoDecimalDigits(userAccountBalance.getTotalAvailableBalance() - amount));
			 
		 SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		 Calendar currentTime = DateTimeUtils.getGMTCalendar();
		 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
		 userAccBalNewRecord.setTimeZone("GMT");
		 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
		 paymentDao.updateAccountBalance(userAccBalNewRecord);
	}

	public void updateUserAccountOnKYLCharges(UserProfile user, double amount){
		UserAccountBalance userAccountBalance = paymentDao.getLastUserAccBalByUsername(user.getUsername());
		
		 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
		 userAccBalNewRecord.setUsername(user.getUsername());
		 userAccBalNewRecord.setKylAmount(NumberFormatUtil.twoDecimalDigits(amount));
		 userAccBalNewRecord.setTotalAvailableBalance(NumberFormatUtil.twoDecimalDigits(userAccountBalance.getTotalAvailableBalance() - amount));
			 
		 SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		 Calendar currentTime = DateTimeUtils.getGMTCalendar();
		 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
		 userAccBalNewRecord.setTimeZone("GMT");
		 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
		 paymentDao.updateAccountBalance(userAccBalNewRecord);
	}

	public void updateCompletedGameWinnerTransaction(Game completedGame, Winner winner,UserProfile user){
		UserAccountBalance userAccountBalance = paymentDao.getLastUserAccBalByUsername(user.getUsername());
		
		 UserAccountBalance userAccBalNewRecord = new UserAccountBalance();
		 userAccBalNewRecord.setUsername(user.getUsername());
		 userAccBalNewRecord.setGameId(completedGame.getId());
		 userAccBalNewRecord.setWinAmount(winner.getWinAmount());
		 userAccBalNewRecord.setWinFeeAmount(winner.getWinFeeAmount());
		 userAccBalNewRecord.setTotalAvailableBalance(NumberFormatUtil.twoDecimalDigits(userAccountBalance.getTotalAvailableBalance() + winner.getWinAmount()));
			 
		 SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		 Calendar currentTime = DateTimeUtils.getGMTCalendar();
		 userAccBalNewRecord.setTimestamp(timeFormat.format(currentTime.getTime()));
		 userAccBalNewRecord.setTimeZone("GMT");
		 userAccBalNewRecord.setLastUpdatedTime(new Timestamp(DateTimeUtils.getGMTCalendar().getTime().getTime()));
		
		 // Update Winner Transaction Status
		 winner.setTransStatus(TransStatus.COMPLETED);
		 
		 paymentDao.updateAccountBalance(userAccBalNewRecord,winner);
	}
}
