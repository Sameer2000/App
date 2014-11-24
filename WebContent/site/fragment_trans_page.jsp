<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function doAjaxPostLoadFragmentTransPage(page) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/happ/site/fragment_trans_page",
            data:"p="+page,
            success: function(response) {
                $("#subViewDiv").html(response);
            }
        });
    }
</script>
                        <div id="subViewDiv" class="db_box1">
                        	<div class="brown_heading">Latest Transactions</div>
                            <div class="db_table_box">
                            	<table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#eee">
                                    <th width="20%" scope="col">Date / Time</th>
                                    <th width="13%" scope="col">Played Amount</th> 
                                    <th width="13%" scope="col">Redeem Amount</th> 
                                    <th width="13%" scope="col">Recharge Amount</th> 
                                    <th width="13%" scope="col">Win Amount</th> 
                                    <th width="10%" scope="col">KYL Fee</th> 
                                    <th width="10%" scope="col">Service Fee</th> 
                                    <th width="10%" scope="col">Balance</th> 
                                  </tr>
                                  <c:forEach items="${LstTrans}" var="trans" varStatus="status">
                                  <tr>
                                  	<td>
                                  	<c:set var="now1" value="${trans.timestamp} ${trans.timeZone}" />
                                  	<fmt:parseDate value="${now1}" var="parsedEmpDate" 
                              							pattern="yyyy-MM-dd HH:mm:ss z" />
                              		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"  
            											value="${parsedEmpDate}" />
                                  	
                                  	</td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.playedGameAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.redeemAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.rechargeAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.winAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.kylAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.serviceFeeAmount}"/></td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${trans.totalAvailableBalance}"/></td>
                                  </tr>
                                  </c:forEach>
                                </table>
                                <div style="display: ${next};">
	                                <a href="#" class="fr" onclick="doAjaxPostLoadFragmentTransPage('next');">Next <!-- &raquo; --></a>
                                </div>
                                <div style="display: ${pre};">
	                                <a href="#" class="fr" onclick="doAjaxPostLoadFragmentTransPage('pre');">Pre <!-- &raquo; --></a>
                                </div>
                                <div class="clearfix"></div>
                               </div>
                        </div>
                       <!--end Transaction--> 
