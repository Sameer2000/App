<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head prefix="og: http://ogp.me/ns#">
	<meta charset="utf-8" />
	<meta property="og:title" content="Play Online Casino" />
	<meta property="og:type" content="Gambling" />
	<meta property="og:url" content="http://localhost:8080${pageContext.request.contextPath}/happ/site/home" />
	<meta property="og:image" content="http://localhost:8080${pageContext.request.contextPath}/images/logo.png" />
	<title>Online Casino</title>
	
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/siteloginstyle.css" media="screen">
    <meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link type="text/css" rel="stylesheet" media="all" href="../css/responsive.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../css/ab_library.css" />
    <link type="text/css" rel="stylesheet" media="all" href="../css/style.css" />	
    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../js/ab_library.js"></script>	
    <script type="text/javascript" src="../js/script.js"></script>	
        <script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>
    <script type="text/javascript" src="../js/jquery.flexslider.js"></script>
    
    <script>
		$(function() {
			var pull 		= $('#pull');
				menu 		= $('nav ul');
				menuHeight	= menu.height();

			$(pull).on('click', function(e) {
				e.preventDefault();
				menu.slideToggle();
			});

			$(window).resize(function(){
        		var w = $(window).width();
        		if(w > 250 && menu.is(':hidden')) {
        			menu.removeAttr('style');
        		}
    		});
		});
	</script>
    <script language="JavaScript">
		function submitUserLoginForm()
		{
		  document.userLoginForm.submit();
		}
	 </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="jsp/header.jsp"></jsp:include>
        
	    <jsp:include page="navigation.jsp"></jsp:include>
        
        <!--Start Registration form & slider-->
        <section class="full-size slider-bg bb">
        	<div id="mid_container">
		    	<p id="usermsg">${error}</p>
            </div>
        </section>
        <!--Start Registration form & slider-->
        
        <!--Start bottom container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            	<div class="service bd">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/money.png" style="padding: 15px 7px;"></div>
                    <div class="details"><h1>Redeem Cash Any time</h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="service bd">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/win-cash_prize.png" style="padding: 8px 6px;"></div>
                    <div class="details"><h1>Win Cash Prize </h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                  </div>
                    <div class="clearfix"></div>
                </div>
                <div class="service bd">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/three_player.png" style="padding:2px"></div>
                    <div class="details"><h1>Every Game - Three Winner</h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                  </div>
                    <div class="clearfix"></div>
                </div>
                <div class="service bd">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/luck.png" style="padding: 12px 2px;"></div>
                    <div class="details"><h1>Know Your Luck</h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                  </div>
                    <div class="clearfix"></div>
                </div>
                <div class="service">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/demo-game.png" style="padding:5px"></div>
                    <div class="details"><h1>Play Demo For Free</h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                  </div>
                    <div class="clearfix"></div>
                </div>
                <div class="service">
                	<div class="service_icon"><img src="${pageContext.request.contextPath}/images/paypal.png" style="padding: 16px 7px;"></div>
                    <div class="details"><h1>Paypal </h1>
                    	<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text </p>
                  </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
        </section>
        <!--End bottom container-->
    
</div>
</body>
</html>
