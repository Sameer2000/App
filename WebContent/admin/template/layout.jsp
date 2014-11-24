<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin_style.css" media="screen" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>  
    <link href="${pageContext.request.contextPath}/images/favicon.png" rel="icon" />
    <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />      
</head>

<body>
<div id="wrapper">
    <header class="full-size header-bg">
        <!--Start Header-->
           <tiles:insertAttribute name="header" />
     </header>
        <!--End Header-->
        
        <!--Start Nav-->
        <tiles:insertAttribute name="menu" />
        <!--Start Nav-->
        
        <!--Start bottom container-->
        <section class="full-size bottom-bg">
			<tiles:insertAttribute name="body" />
	        <tiles:insertAttribute name="footer" />
        </section>
        <!--End bottom container-->

</div>
<!--end wrapper-->
</body>
</html>
