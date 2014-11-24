<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
function chkButton(){
	location.reload();
	if(document.getElementById("trans").value=="COMPLETED"){
		document.getElementById('transButton').disabled = true;
		document.getElementById('inactive').value="INACTIVE SUBMIT BUTTON";
	}
}
</script>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calendar1.css" media="all">
 
       <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Request Time ${redeem.requestedTime}</div>
            	<div><b>Request Redeem Amount - </b>${redeem.amount}</div>
            	<div><b>Available Balance - </b>${balance.totalAvailableBalance}</div>
               <body onload="chkButton()">
               <input align="middle"  style="border: none; margin:0 0 0 32%; font-size:120%; font-weight:bolder; color:red; width:20%; background: white;" type="text" id="inactive" readonly="readonly"  value=" " />
                <form:form class="contact_form" name="edit" modelAttribute="redeemForm" method="POST" action="${pageContext.request.contextPath}/happ/admin/updateRedeem/${redeem.id}">       
                             <ul class="registration">
                      
                        <li>
                        	<div id="login-error">${error}</div>
                        </li>
                        <li>
                            <label for="username" >User :</label>
                            <input value="${redeem.username}" type="text" name="username" id="username" readonly="readonly"   />
                            
                        </li>
                        <li>
                            <label for="transStatus">Type :</label>
                             <select name="transStatus" id="transStatus">
	                        <c:forEach items="${redeemTransCategory}" var="category">
				                <c:set var="isSelected" value="false" />
			                    <c:if test="${redeem.transStatus.value==category.key}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
				                <c:choose>
				                    <c:when test="${isSelected}">
				                        <option value="${category.key}" selected="selected">${category.value}</option>
				                    </c:when>
				                    <c:otherwise>
				                        <option value="${category.key}">${category.value}</option>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
                            </select>
                        </li>
                        
                        <li>
                            <label for="message">Message  : </label>
                            <input  value="${redeem.message}"  type="text" name="message" id="message"     />
                            
                        </li>
                        
                        <li>
                            <label for="transationId">Transaction Id  : </label>
                            <input  value="${redeem.transationId}"  type="text" name="transationId" id="transationId"     />
                        </li>
                        
                         <li>
                            <label for="payerId">Payer Id  : </label>
                            <input  value="${redeem.payerId}"  type="text" name="payerId" id="payerId"     />
                        </li>
                        
                        
                        <li>
                            <button class="red_btn" id="transButton" type="submit" >Submit</button>
                        </li>

                    </ul> <input type="hidden"  id="trans" value="${redeem.transStatus}"/>
                    
                </form:form></body>
            </div>
            </div>
	</section>
