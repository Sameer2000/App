<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
            <div class="head">
            	<div class="main_heading">Forget Password</div>
            </div>
            <c:set var="value" value="${msg}"></c:set>
            <c:if test="${msg==null}"> 
                <form:form class="contact_form" name="forget_password" method="POST" modelAttribute="Forget_password" action="forgetpassword">
                    <ul>
                      
                        
                        <li>
                            <label for="email">Email:</label>
                            <input type="email" name="email" placeholder="john_doe@example.com" required />
                            <span class="form_hint">Proper format "name@something.com"</span>
                    
                        </li>
                       
                        <li>
                            <button class="red_btn" type="submit">Submit</button>
                        </li>
                    </ul>
                </form:form>
                </c:if>
                <div class="contact_form">
                <c:if test="${msg!=null}">
                	<p style="margin: 0 0 0 30%;"><b><c:out value="${msg}"></c:out></b></p>
                	<p style="margin: 0 0 0 30%;">If email is not received<a href="site_forget_password">click here</a>..</p>
                </c:if>
                </div>
            </div>
            </div>
            
             <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
         </section>
          <!--End bottom container-->
</div>
</body>
</html>
