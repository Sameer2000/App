<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Forget Password</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calendar1.css" media="all">
 <script type="text/javascript">
	$(function() {
  if ($.browser.msie && $.browser.version.substr(0,1)<7)
  {
	$('li').has('ul').mouseover(function(){
		$(this).children('ul').css('visibility','visible');
		}).mouseout(function(){
		$(this).children('ul').css('visibility','hidden');
		})
  }
}); 
	</script>
</head>

<body>
<div id="wrapper">
	 
	 	<jsp:include page="header.jsp"></jsp:include>
        
         <jsp:include page="../navigation.jsp"></jsp:include>
        
        
        <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Forgot Password</div>
           <div align="center" style="width: 100%; font-size: large; color: maroon;">
            <c:if test="${msg==null}"></div>
                <form:form class="contact_form" name="contact_form" method="POST" modelAttribute="newpasswordForm" action="newPassword">
                    <ul>
                            <label for="email">Email:</label>
                            <!-- <input type="email" name="username" placeholder="john_doe@example.com" value="" required />-->
                             <input type="email" name="username" placeholder="john_doe@example.com" value="${usr}" required readonly="readonly"/>				 
                             <span class="form_hint">Proper format "name@something.com"</span>
                        </li>
                        <li>
                            <label for="password">Password:</label>
                            <input type="password" name="password" placeholder="New Password"/>
                        </li>
                        <li>
                            <label for="confirm">Confirm Password:</label>
                            <input type="password" name="confirm" placeholder="Confirm Password"/>
                        </li>
                        <li>
                            <button class="red_btn" type="submit">Submit</button>
                        </li>
                    </ul>
                </form:form>
                </c:if>
                <c:if test="${msg!=null}">
                	<p><c:out value="${msg}"></c:out></p>
                </c:if>
            </div>
            </div>
            
             <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
         </section>
          <!--End bottom container-->
</div>
</body>
</html>
