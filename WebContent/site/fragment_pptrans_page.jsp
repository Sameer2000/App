<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function doAjaxPostLoadFragmentPPTransPage(page) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/happ/site/fragment_account_pptrans_page",
            data:"p="+page,
            success: function(response) {
                $("#subViewDiv").html(response);
            }
        });
    }
</script>
                        <div id="subViewDiv" >
                        	<table width="100%" border="1" cellspacing="0" bordercolor="#d9d9d9" cellpadding="0">
                                  <tr>
                                    <th width="16%" scope="col">Transaction ID</th>
                                    <th width="15%" scope="col">Status</th>
                                    <th width="11%" scope="col">Recharge Amount</th>
                                    <th width="14%" scope="col">Date/Time</th>
                                    <th width="13%" scope="col">Payment Gatway</th>
                                    <th width="20%" scope="col">Financial Server Charges</th>
                                    <th width="11%" scope="col">Balance Amount</th>
                                  </tr>
                                  <c:forEach items="${userpptranslist}" var="userpptrans" varStatus="status">
										<tr>
											<td >${userpptrans.transactionId}</td>
											<td>${userpptrans.transStatus}</td>
											
											<td>$<fmt:formatNumber pattern="0.00" value="${userpptrans.totalAmount}"/></td>
											<td>
									           	<c:set var="now1" value="${userpptrans.transTime} ${userpptrans.timeZone}" />
			                                  	<fmt:parseDate value="${now1}" var="parsedEmpDate" 
			                              							pattern="yyyy-MM-dd HH:mm:ss z" />
			                              		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"  
			            											value="${parsedEmpDate}" />
                        
											</td>
											<td>${userpptrans.paymentGateway}</td>
											<td>$<fmt:formatNumber pattern="0.00" value="${userpptrans.feeAmount}"/></td>
											<td>$<fmt:formatNumber pattern="0.00" value="${(userpptrans.amount)}"/></td>
											
						 				</tr>
								   </c:forEach>
                               
                                </table>
							<div class="pagination">
                           
                            <div style="display: ${next};">
	                            <a href="#" class="fr" onclick="doAjaxPostLoadFragmentPPTransPage('next');">Next <!-- &raquo; --></a>
                            </div>
                            <div style="display: ${pre};">
	                            <a href="#" class="fr" onclick="doAjaxPostLoadFragmentPPTransPage('pre');">Pre <!-- &raquo; --></a>
                            </div>
                            
                            </div>
                            <div class="clearfix"></div>
                        </div>
