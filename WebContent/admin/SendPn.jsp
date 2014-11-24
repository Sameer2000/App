<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Contact Us</title>
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


        
        
        <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Send Push Notification</div>
            	
            	<c:if test="${err==null}">
                <form:form class="contact_form" name="push_notification_form" method="POST" modelAttribute="push_notification" action="notification">
                    <ul>
                      
                        <li>
                            <label for="shortMessage">Message:</label>
                            <input type="text" name="shortMessage" maxlength="50" placeholder="Enter Message" required />
                            <span class="form_hint">50 Character limit</span>
                            <p style="font-size: 11px;margin-left: 173px;margin-top: 7px;color: gray;">50 Character limit</p>
                        </li>
                        <li>
                        	<label for="longMessage">Description:</label>
                            <textarea placeholder="Enter Description..." name="longMessage" cols="10" rows="6" maxlength="250" required></textarea>
                            <span class="form_hint">250 Character limit</span>
                            <p style="font-size: 11px;margin-left: 173px;margin-top: 7px;color: gray;">250 Character limit</p>
                        </li>

                        <li>
                            <button class="red_btn" type="submit">Submit</button>
                        </li>
                    </ul>
                </form:form>
                </c:if>
                <c:if test="${err!=null}">
                	<p><c:out value="${err}"></c:out> </p>
                </c:if>
            </div>
            </div>
            
             <!-- <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div> -->
         </section>
          <!--End bottom container-->

</body>
</html>
