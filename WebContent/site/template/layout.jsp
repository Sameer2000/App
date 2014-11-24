<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head prefix="og: http://ogp.me/ns#">
	<meta charset="utf-8" />
	<meta property="og:title" content="Play Online Casino" />
	<meta property="og:type" content="Gambling" />
	<meta property="og:url" content="http://localhost:8080${pageContext.request.contextPath}/happ/site/home" />
	<meta property="og:image" content="http://localhost:8080${pageContext.request.contextPath}/images/logo.png" />
	<title>
		<tiles:insertAttribute name="title" />
	</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginstyle.css" media="screen" />
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
	<link href="${pageContext.request.contextPath}/images/favicon.png" rel="icon" />
    <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />
</head>

<body>
<div id="wrapper">
      
      <tiles:insertAttribute name="header" />
      	<tiles:insertAttribute name="topmenu" />
        <!--Start Mid container-->
        <section class="full-size bottom-bg">
        	<div class="main_container">
                
				<tiles:insertAttribute name="usermenu" />
               
                <section>
                	<tiles:insertAttribute name="body" />
                </section>
                <div class="clearfix"></div>
            </div>
            
				<tiles:insertAttribute name="footer" />
        <section>
        <!--End Mid container-->
</div>
</body>
</html>