<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<div id="wrapper">

    	<jsp:include page="header.jsp"></jsp:include>   
        
         <jsp:include page="../navigation.jsp"></jsp:include>
        
        
        <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Contact Us</div>
                <form:form class="contact_form" name="contact_form" method="POST" modelAttribute="contactUsForm" action="contactus">
                    <ul>
                      
                        <li>
                            <label for="name">Name:</label>
                            <input type="text" name="name"  placeholder="John Doe" required />
                        </li>
                        <li>
                            <label for="email">Email:</label>
                            <input type="email" name="email" placeholder="john_doe@example.com" required />
                            <span class="form_hint">Proper format "name@something.com"</span>
                        </li>
                        <li>
                            <label for="phone">Phone:</label>
                            <input type="phone" name="phone" placeholder="9847438483"/>
                            <span class="form_hint">Proper format "9847438483"</span>
                        </li>
                        <li>
                            <label for="message">Message:</label>
                            <textarea name="message" cols="40" rows="6" required maxlength="1000" ></textarea>max 1000
                        </li>
                        <li>
                            <button class="red_btn" type="submit">Submit</button>
                        </li>
                    </ul>
                </form:form>
            </div>
            </div>
            
             <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
         </section>
          <!--End bottom container-->
</div>
</body>
</html>
