package com.housee.app.pn;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.dao.CommonDaoImpl;
import com.housee.app.model.DeviceMapping;
import com.housee.app.model.Game;
import com.housee.app.model.MessageInbox;
import com.housee.app.model.Player;
import com.housee.app.service.AdminServiceImpl;
import com.housee.app.service.CommonServiceImpl;
import com.housee.app.service.GameServiceImpl;
import com.housee.app.pn.AndroidGCMPNService;
import com.housee.app.pn.iOSPNService;

public class Send_Push_notification {
	
	
	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	CommonServiceImpl commonService;

	private Game game;
	private Hashtable<Integer,Integer> openNumber = new Hashtable<Integer,Integer>();
	
	
	
	public Send_Push_notification() {
		// TODO Auto-generated constructor stub
	}

	public void sendPN(List<DeviceMapping> users, String message, Game gameId){
		//this.users = users;
		System.out.println("push notification class-----------------------");
		System.out.println("Message is"+message);

		System.out.println("Game id ====="+gameId.getId());
	List<String> androidUserList = new ArrayList<String>();
	List<String> iPhoneUserList = new ArrayList<String>();
	for(DeviceMapping mapping : users){
		if(mapping.getDeviceType().equalsIgnoreCase("android") || mapping.getDeviceType().equalsIgnoreCase("iPhone")){
			
		if(mapping.getDeviceType().equalsIgnoreCase("android")){
			androidUserList.add(mapping.getDeviceToken());
		}else{
			iPhoneUserList.add(mapping.getDeviceToken());
		}
		}
		else{
			ModelAndView mv =new ModelAndView();
			mv.addObject("err", "No device Found");
		}
	}
	
	String payloadMsg = "Housee Game Started(Game-"+gameId.getId()+")";
	
		
	if(androidUserList.size() > 0){
		AndroidGCMPNService.sendPush(androidUserList,payloadMsg);
	}
	if(iPhoneUserList.size() > 0){
		for(String deviceToken : iPhoneUserList){
			try {
				iOSPNService.push(payloadMsg,deviceToken);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
	}
	
}
