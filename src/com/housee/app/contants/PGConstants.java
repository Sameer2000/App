package com.housee.app.contants;

public class PGConstants {

	public static String host = "http://localhost:8080/HWebapp";
	public static String iphost = "http://192.168.1.3:8080/HWebapp";
	
	public static String ppPaymentGateway = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	public static String returnURL = "/happ/site/paypal/pg/return";
	public static String cancelURL = "/happ/site/paypal/pg/cancel";

	public static String ppMobilePaymentGateway = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout-mobile&useraction=commit&token=";
	public static String mobileReturnURL = "/happ/paypal/pg/success";
	public static String mobileCancelURL = "/happ/paypal/pg/failure";

	public static String paymentType = "Sale";
	public static String currencyCode = "USD";
	
}
