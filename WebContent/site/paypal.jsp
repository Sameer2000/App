<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PayPal</title>
<script type="text/javascript">
    window.onload=function(){ 
        window.setTimeout(document.ppcheckout.submit(), 5000);
    };
</script>
</head>
<body>
<form name="ppcheckout" action="https://www.sandbox.paypal.com/webscr" method="post">
<!-- <input type="hidden" name="cmd" value="_xclick"/> -->
 <input type="hidden" name="cmd" value="_express-checkout-mobile"/>
 <input type="hidden" name="business" value="sumit.jagia.test@gmail.com"/>
<input type="hidden" name="password" value="1379919703"/>
<input type="hidden" name="custom" value="1123"/>
<input type="hidden" name="TOKEN" value="EC-7FU52529LA221360D"/>
<input type="hidden" name="tx" value="TransactionID"/>
<input type="hidden" name="item_name" value="1"/>
<input type="hidden" name="amount" value="1"/>
<input type="hidden" name="hosted_button_id" value="LAHQRCXWQEZZE"/>
<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!"/>

<input type="hidden" name="return" value="http://localhost:8080${pageContext.request.contextPath}/happ/site/home"/>
<input type="hidden" name="cancel_return" value="http://localhost:8080${pageContext.request.contextPath}/happ/site/home"/>
<input type="hidden" name="notify_url" value="http://localhost:8080${pageContext.request.contextPath}/happ/site/home"/>

<!-- <input type="hidden" name="return" value="http://localhost:8080/SSC/final.do?action=saveTransaction"/>
<input type="hidden" name="cancel_return" value="http://localhost.54:8080/SSC/unsuccessfulPayment.jsp"/>
<input type="hidden" name="notify_url" value="http://localhost:8080/SSC/test.jsp"/>
 -->
 </form>

</body>
</html>