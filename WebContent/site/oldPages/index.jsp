<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>HAPP site</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
</head>


<body>
<div id="pageWrap">
    <div id="header">
        <h1>Welcome to my web page</h1>
        <h2>My header sub text</h2>
        
        <ul class="navigation">
            <li><a href="index.html" title="home">Home</a></li>
            <li><a href="http://www.tripod.com" title="Tripod">Tripod</a></li>
            <li><a href="http://www.tripod.com" title="Lycos">Search the web</a></li>
        </ul>
    </div>
    
    <div class="content">
    
    <div class="sidebar-left">
    	<h3>A list of my favorite links</h3>
    	<ul class="list">
        	<li><a href="http://www.tripod.com" title="Build your own website at Tripod">Tripod</a></li>
            <li><a href="http://www.lycos.com" title="Lycos, go get it!">Lycos Search</a></li>
            <li><a href="http://www.facebook.com" title="Facebook">Facebook</a></li>
            <li><a href="http://www.w3schools.com/" title="W3Schools.com">Website coding resources</a></li>
        </ul>
    </div>
    
    <div class="col-middle">
        <p><img src="${pageContext.request.contextPath}/images/leafPeeping.jpg" alt="alternative text.  This text is for screen readers or in the instance an image can't load, a description of what should have been here." /></p>
        <h3>A great story title complimenting the image goes here</h3>
        <p><strong>At vero eos et accusamus et iusto odio</strong> dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.
        </p>
        <p>
        <em>At vero eos et accusamus et iusto odio</em> dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.
        </p>
    </div>
    
    <div class="sidebar-right">
    	<h3>Right Sidebar header!</h3>
    	<a href="mailto:me@fakeemail.com">Send me an e-mail!</a>
        <br />
        <br />
        <img src="${pageContext.request.contextPath}/images/img1.jpg" alt="doggy!" style="width:100%" /> <!-- the width on this img tag is actually making the image 100% of the size of the container it's in.  In this example, it's in a div with the class of sidebar-left that has a width of 195px, so the image is only 195px wide.  If you put this same code into the middle column, the image would be a different size. --><br />
        <h3>Image one</h3>
        <p>info about the image</p><br />
        <img src="${pageContext.request.contextPath}/images/img3.jpg" alt="doggy!" style="width:100%" /><br />
        <h3>Image one</h3>
        <p>info about the image</p><br />
        <img src="${pageContext.request.contextPath}/images/img4.jpg" alt="doggy!" style="width:100%" /> <br />
        <h3>Image one</h3>
        <p>info about the image</p><br />
    </div>
    <br class="clear" />  <!-- this code is clearing the floats from the above divs.  See what happens when you take it away to really learn what clear does... -->
    </div>
    <div id="footer">
        <p>Put footer text here.  Like copyright info and such</p>
    </div>
</div>
</body>
</html>