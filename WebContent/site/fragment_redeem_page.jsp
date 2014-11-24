<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function doAjaxPostLoadFragmentRedeemPage(page) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/happ/site/fragment_redeem_page",
            data:"p="+page,
            success: function(response) {
                $("#subViewDiv").html(response);
            }
        });
    }
</script>
           <!--table-->
           <div class="table">
           	<table width="100%" border="1"  cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse;">
		        <tr class="fbold wtxt">
					<th>Req. Time</th>
					<th>Type</th>
					<th>Status</th>
					<th>Transaction Id</th>
					<th>Redeem Amt.</th>
					<th>Message</th>
				</tr>
                 <c:forEach items="${redeemList}" var="redee"
				varStatus="status">
				<tr>
					<td>
			           	<c:set var="now1" value="${redee.requestedTime} ${redee.timeZone}" />
                       	<fmt:parseDate value="${now1}" var="parsedEmpDate" 
                   							pattern="yyyy-MM-dd HH:mm:ss z" />
                   		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"  
 											value="${parsedEmpDate}" />
					</td>
					<td>${redee.redeemType}</td>
					<td>${redee.transStatus}</td>
					<td>${redee.transationId}</td>
					<td>$<fmt:formatNumber pattern="0.00" value="${redee.amount}"/></td>
					<td>${redee.message}</td>
 				</tr>
			</c:forEach>
			
               </table> 
                    <div style="display: ${next};">
                  <a href="#" class="fr" onclick="doAjaxPostLoadFragmentRedeemPage('next');">Next <!-- &raquo; --></a>
                 </div>
                 <div style="display: ${pre};">
                  <a href="#" class="fr" onclick="doAjaxPostLoadFragmentRedeemPage('pre');">Pre <!-- &raquo; --></a>
                 </div>
                 <div class="clearfix"></div>
       </div>
           <!--end table-->