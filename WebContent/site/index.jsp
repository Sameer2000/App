<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:og="http://ogp.me/ns#" xmlns:fb="https://www.facebook.com/2008/fbml"><head>
<head>
	<meta charset="utf-8" />
	<meta property="og:title" content="Play Online Casino" />
	<meta property="og:type" content="Gambling" />
	<meta property="og:url" content="http://localhost:8080/HWebapp/happ/site/home" />
	<meta property="og:image" content="http://localhost:8080/HWebapp/images/logo.png" />
	<title>Online Casino</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="screen">
    <script language="JavaScript">
		function submitForm()
		{
		  document.register.submit();
		}
		
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
            	<!--Registration form-->
            	<form:form name="register" method="POST" modelAttribute="userRegForm" action="/HWebapp/happ/site/regUser">
                	<div id="Registraion_form">
                    	<div class="bonus">
                        	<div class="rupee_icon"><img src="${pageContext.request.contextPath}/images/rupee_icon.gif"></div>
                            <div class="bonus_value">GET<br><span class="bvn">500*</span><br>BONUS<br><span class="tc">*T&C</span></div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="spn">START PLAYING <span class="ntxt">NOW!</span></div>
                        <div class="form_input">
                        	<div>
                            	<label>Email</label>
                            	<form:input path="emailId" />
                            </div>
                            <div class="mt1">
                            	<label>Password</label>
                            	<form:input path="password" />
                            </div>
                            <div class="mt1">
                            	<label>Name</label>
                            	<form:input path="fullName" />
                            </div>
                            <div class="mt1">
                            	<label>Country</label>
                                <form:select path="country">
                                	<form:option value="NONE" label="--- Select ---"/>
                                	<form:options items="${countryMap}" />
                                </form:select>
                            </div>
                            <div class="mt1">
                            	<label>Phone</label>
                            	<form:input path="phone" />
                            </div>
                        </div>
                        <div class="reg-btn"><input type="checkbox"> Email me special offers<br>
                        <img src="${pageContext.request.contextPath}/images/registration_btn.png" onclick="submitForm();">
                        </div>
                        <div class="info"><b>No Credit Card Required</b><br>By clicking 'Register for Free' you agree to our <a href="#">Privacy Policy</a></div>
                    </div>
                <!--End Registration form-->
                </form:form>
                
                <!--Strat slider-->
                <div id="slider">
                	<div class="slider_heading">
                    	<ul>
                        	<li>Easy To Start</li>
                            <li class="active">Non-Stop Fun</li>
                            <li>Win Real Cash & Prizes</li>
                        </ul>
                    </div>
                    <img src="${pageContext.request.contextPath}/images/slide1.png">
                </div>
                <!--End slider-->
                <div class="clearfix"></div>
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
