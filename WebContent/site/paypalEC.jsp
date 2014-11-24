<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method=post action=https://api-3t.sandbox.paypal.com/nvp>
		<input type=hidden name=USER value=sumit.jagia.test_api1.gmail.com>
		<input type=hidden name=PWD value=1379919703>
		<input type=hidden name=SIGNATURE value=AGwOSjpHvOfU1-n9bGTB0iservpVANzq6iZFLXhlwd.KrsFyi6yWCb21>
		<input type=hidden name=VERSION value=65.1>
		<input type=hidden name=PAYMENTREQUEST_0_PAYMENTACTION
			value=Sale>
		<input name=PAYMENTREQUEST_0_AMT value=1>
		<input type=hidden name=RETURNURL
			value=http://localhost:8080${pageContext.request.contextPath}/happ/site/home>
		<input type=hidden name=CANCELURL
			value=http://localhost:8080${pageContext.request.contextPath}/happ/site/home>
		<input type=submit name=METHOD value=SetExpressCheckout>
	</form>
</body>
</html>