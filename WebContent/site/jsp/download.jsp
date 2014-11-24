<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Download</title>
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
            	<div class="main_heading">Download</div>
                <div class="download_game_box">
                	<legend class="legend"><img src="${pageContext.request.contextPath}/images/android.png" align="absmiddle">&nbsp;Android</legend>
                    <div class="d_game fl"><img src="${pageContext.request.contextPath}/images/d1.gif"><br>
                    	<input type="submit" value="Native" class="red_btn" style="margin-left:100px; margin-top:15px">
                    </div>
                    <div class="d_game fr"><img src="${pageContext.request.contextPath}/images/d2.gif"><br>
                    	<input type="submit" value="PhoneGap" class="red_btn" style="margin-left:100px; margin-top:15px">
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="download_game_box">
                	<legend class="legend"><img src="${pageContext.request.contextPath}/images/mac.png" align="absmiddle">&nbsp;iOS</legend>
                    <div class="d_game fl"><img src="${pageContext.request.contextPath}/images/d1.gif"><br>
                    	<input type="submit" value="Native" class="red_btn" style="margin-left:100px; margin-top:15px">
                    </div>
                    <div class="d_game fr"><img src="${pageContext.request.contextPath}/images/d2.gif"><br>
                    	<input type="submit" value="PhoneGap" class="red_btn" style="margin-left:100px; margin-top:15px">
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            </div>
            
             <div id="footer">&copy; 2012  Online Casino, all right reserved. <a href="#">Privacy Policy</a></div>
         </section>
          <!--End bottom container-->
</div>
</body>
</html>
