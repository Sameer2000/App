<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="${pageContext.request.contextPath}/images/favicon.png" rel="icon" />
    <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="screen">
    <script language="JavaScript">
		function submitUserLoginForm()
		{
		  document.userLoginForm.submit();
		}
	 </script>
	 
</head>
    <header class="full-size header-bg">
        <!--Start Header-->
            <div id="header">
                <div class="logo"><img src="${pageContext.request.contextPath}/images/logo.png"></div>
                
                <div class="total_players"><img src="${pageContext.request.contextPath}/images/players.png">13,000<br><span>Players</span></div>
                  <form:form name="userLoginForm" method="post" action="${pageContext.request.contextPath}/happ/site/j_user_security_check" modelAttribute="adminAuth">
                  <a href="#" onclick="window.open('https://www.facebook.com/sharer/sharer.php?u=${pageContext.request.contextPath}/happ/site/home'),'facebook-share-dialog','width=626,height=436');return false;">
                 <img width="100" height="30" src="${pageContext.request.contextPath}/images/fb1.png" align="absmiddle"></a>
            
                <div class="login_form">
                	<input type="text" name="j_username" placeholder="Username">
                	<input type="password" name="j_password" placeholder="Password">
                    <div class="btn_fp"><a href="${pageContext.request.contextPath}/happ/site/site_forget_password">Forgot password?</a><img src="${pageContext.request.contextPath}/images/login_btn.png" align="absmiddle" onclick="submitUserLoginForm();"></div>
                </div>
                </form:form>
                <div class="clearfix"></div>
            </div>
            
     </header>
        <!--End Header-->
