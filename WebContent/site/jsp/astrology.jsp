<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Astrology</title>
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
            	<div class="main_heading">Astrology</div>
                <div class="demo_content">
                	<div class="astrology">
                    <h2>Our Astrologer</h2>
                    	<p>"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
                        <div class="user_astro_detail">
                        	<div class="astro_img"><img src="${pageContext.request.contextPath}/images/astrologer.gif"></div>
                            <div class="astro_name">John Carter</div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            </div>
            
             <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
         </section>
          <!--End bottom container-->
</div>
</body>
</html>
