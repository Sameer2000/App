package com.housee.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.contants.PGConstants;
import com.housee.app.json.RechargeAmountForm;
import com.housee.app.service.PaymentTransactionServiceImpl;

@Controller
@RequestMapping("/paypal")
public class MobilePaypalControllerImpl {

	@Autowired
	private PaymentTransactionServiceImpl paymentService;
	
	@RequestMapping(value="pg/submit")
	public String submitPPTransaction(@RequestParam("token") String token,HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("PPTOKEN", token);
		String redirectStr = PGConstants.ppMobilePaymentGateway+token;
		System.out.println("REDIRECT STR ---> " + redirectStr);
		return "redirect:"+ redirectStr;
	}
	
	@RequestMapping(value="pg/failure")
	public ModelAndView failedPPTransaction(HttpServletRequest request){
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		mv.addObject("URL",request.getContextPath() + "/happ/paypal/trans/failure");
		mv.addObject("message","Your Transaction has been Failed, Please try again later.");
		return mv;
	}
	
	@RequestMapping(value="trans/failure")
	public ModelAndView ppTransactionFail(){
		return null;
	}
	
	@RequestMapping(value="trans/success")
	public ModelAndView ppTransactionSuccess(){
		return null;
	}
	
	
	@RequestMapping(value="pg/success")
	public ModelAndView returnPPTransaction(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response){
		System.out.println("returnPPTransaction Request Token --> " + token);
		System.out.println("returnPPTransaction Session Token --> " + (String)request.getSession().getAttribute("PPTOKEN"));
		String sessionToken = (String)request.getSession().getAttribute("PPTOKEN");
		ModelAndView mv  = new ModelAndView("pp_trans_page");
		if(token.equalsIgnoreCase(sessionToken)){
			boolean status = paymentService.getDetailPPTransaction(sessionToken,request);
			if(status){
				mv.addObject("URL",request.getContextPath() + "/happ/paypal/onSuccessPPTrans");
				mv.addObject("message","Please wait while, Your Transaction has been processed");
			}else{
				mv.addObject("URL",request.getContextPath() + "/happ/paypal/trans/failure");
				mv.addObject("message","Please wait while, Your Transaction has been failed");
			}
		}else{
			mv.addObject("URL",request.getContextPath() + "/happ/paypal/trans/failure");
			mv.addObject("message","Please wait while, Your Transaction has been failed");
		}
		return mv;
	}
	
	@RequestMapping(value="onSuccessPPTrans")
	public String onSuccessPPTransaction(HttpServletRequest request, HttpServletResponse response){
		System.out.println("onSuccessPPTransaction--> " + (String)request.getSession().getAttribute("PPTOKEN"));
		String sessionToken = (String)request.getSession().getAttribute("PPTOKEN");
		boolean status = paymentService.commitPPTransactionMobile(sessionToken);
		if(status){
			return  "redirect:/happ/paypal/trans/success";
		}else{
			return  "redirect:/happ/paypal/trans/failure";
		}
	}
	

}
