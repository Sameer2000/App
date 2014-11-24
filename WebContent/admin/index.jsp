<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Admin Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminloginstyle.css" media="screen">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" media="screen">
</head>

<body>

<form:form id="adminForm" method="post" action="${pageContext.request.contextPath}/happ/admin/j_admin_security_check" modelAttribute="adminAuth">

<div class="bottom-bg full-size minH">
	<section id="admin_box">
    	<div class="admin_logo_box"><img src="${pageContext.request.contextPath}/images/logo.png"></div>
            	
        <div id="login-error">${error}</div>
            	
        <!--Start Admin login form-->
        <div class="admin_login_form">
        	<div class="innerbox">
            	<label>User ID</label>
                <input type="text" name="j_username">
            </div>
            <div class="innerbox">
            	<label>Password</label>
                <input type="text" name="j_password">
            </div>
            <div class="admin_login_Btn">
            	<input type="checkbox" >&nbsp;Remember me? <br>
                <img src="${pageContext.request.contextPath}/images/admin-login.png" align="absmiddle" onClick="adminForm.submit();"> 
                <a href="#">Forgot Password?</a>
                
            </div>
        </div>
        <!--End Admin login form-->
        
    </section>
</div>
		</form:form>
</body>
</html>
