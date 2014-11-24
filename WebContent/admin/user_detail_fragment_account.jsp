<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function doAjaxPostLoadFragmentAccPage(page) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/happ/admin/user_detail_fragment_account_page",
            data:"p="+page+"&usrname=${usrname}",
            success: function(response) {
                $("#subViewDivAcc").html(response);
            }
        });
    }
</script>
          <div id="subViewDivAcc" class="db_box1">

              	<table  width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                            	
                                  <tr bgcolor="#eee">
                                    <th width="10%" scope="col">Game Id</th>
                                    <th width="15%" scope="col">User Name</th>
                                    <th width="5%" scope="col">Played Game Amount</th>
                                    <th width="5%" scope="col">Recharge & Amount</th>
                                    <th width="10%" scope="col">Redeem Amount</th>
                                    <th width="10%" scope="col">KYL Fee</th>
                                    <th width="10%" scope="col">Service Fee</th>
                                   <th width="15%" scope="col">Date / Time</th> 
                                     <th width="10%" scope="col">Total Balance</th>
                                    
                                    <th width="10%" scope="col">Win Amount</th> 
                                  </tr>
                                  
                                   <c:forEach items="${accountList}" var="account">
                                  <tr>
                                      <td>${account.gameId}</td>
                                       <td>${account.username}</td>
                                       <td>$<fmt:formatNumber pattern="0.00" value="${account.playedGameAmount}"/></td>
                                      <td>$<fmt:formatNumber pattern="0.00" value="${account.rechargeAmount}"/></td>
                                     <td>$<fmt:formatNumber pattern="0.00" value="${account.redeemAmount}"/></td>
                                     <td>$<fmt:formatNumber pattern="0.00" value="${account.kylAmount}"/></td>
                                       <td>$<fmt:formatNumber pattern="0.00" value="${account.serviceFeeAmount}"/></td>
                                      <td>${account.timestamp}</td>
                                      <td>$<fmt:formatNumber pattern="0.00" value="${account.totalAvailableBalance}"/></td>
                                      
                                      <td>$<fmt:formatNumber pattern="0.00" value="${account.winAmount}"/></td>
                                  </tr>
                                </c:forEach>
                                </table>
                   <div style="display: ${next};">
	                 	<a  class="fr" onclick="doAjaxPostLoadFragmentAccPage('next');">Next <!-- &raquo; --></a>
                   </div>
                   <div style="display: ${pre};">
	                    <a class="fr" onclick="doAjaxPostLoadFragmentAccPage('pre');">Pre <!-- &raquo; --></a>
                   </div>
                   <div class="clearfix"></div>

           </div>