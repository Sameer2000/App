 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>       
<style>
		.error {
			color: #ff0000;
		}
		.errorblock {
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
</style>
<script type="text/javascript">
function fn(){
	document.getElementById('amt').disabled = true;
}
function fn1(){
	document.getElementById('amt').disabled = false;
}
</script>

<script>
function displayResult()
{
	document.getElementById("full").checked=true;
	document.getElementById('amt').disabled = true;
}

function loadXMLDoc()
{
	//alert("hhhh");
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","redeemSubmit?redeemSubmit=${username}",true);
xmlhttp.send();
}
</script>
 
 <body onload="displayResult();">
                	<div class="right-Container2">
                    	<div class="page_heading">Redeem</div>
                    	<div class="t_name2" style="background-color: white; border: none;">Avaliable Balance</div>
                                <div class="t_value2" style="float: left;margin-top: 5px;">$<fmt:formatNumber pattern="0.00" value="${useravailablebalance.totalAvailableBalance}"/></div>
                        	<div id="login">
                                 <form:form class="redeem_form" action="redeemSubmit" method="POST" modelAttribute="redeemForm"  name="redeem_form" style="margin-left:0">
                                  <p>
	                                  <label><b>Amount</b></label><input type="text"  name="amount"  placeholder="Enter amount*" id="amt" onblur="loadXMLDoc()"/> 
	                                  <label><b>Payment</b></label><br />

	                                   <input type="radio" name="redeemType" value="0" onclick="fn1()" checked="checked"/>&nbsp; Partial Payment &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        				<input type="radio" name="redeemType" value="1" onclick="fn()"/>&nbsp; Full Payment  

	                                   	<div id="myDiv"><h2></h2></div>
										 
                                  </p>
                                  <p class="submit"><input type="submit" name="redeem" value="Submit" /></p>
                                </form:form>
                              </div>
	                        <div id="subViewDiv" style="float: left;width: 95%;margin: 20px 0 0 15px;">
								<jsp:include page="fragment_redeem_page.jsp" />
							</div>
                              
                    </div>
                    
                    </body>