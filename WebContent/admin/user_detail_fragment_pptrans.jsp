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
            url: "${pageContext.request.contextPath}/happ/admin/user_detail_fragment_pptrans_page",
            data:"p="+page+"&usrname=${usrname}",
            success: function(response) {
                $("#subViewDivPPTrans").html(response);
            }
        });
    }
</script>
          <div id="subViewDivPPTrans" class="db_box1">

              	<table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                     <tr bgcolor="#eee">
                       <th width="20%" scope="col">Transaction ID</th>
                       <th width="15%" scope="col">Payer ID</th>
                         <th width="15%" scope="col">Token</th>
                        <th width="10%" scope="col">Final Status</th>
                          <th width="10%" scope="col">Total Amount</th>
                        <th width="10%" scope="col">Time Stamp</th>
                       <th width="10%" scope="col">Payment Gateway</th>
                        <th width="5%" scope="col">Fee Amount</th>
                        <th width="5%" scope="col">Balance Amount</th>
                     </tr>
                     <c:forEach items="${transList}" var="transDetails">
                     <tr>
                       <td>${transDetails.transactionId}</td>
                       <td>${transDetails.payerId}</td>
                       <td>${transDetails.token}</td>
                       <td>${transDetails.transStatus}</td>
                       <td>$<fmt:formatNumber pattern="0.00" value="${transDetails.totalAmount}"/></td>
                       <td>${transDetails.TIMESTAMP}</td>
                       <td>${transDetails.paymentGateway}</td>
                       <td>$<fmt:formatNumber pattern="0.00" value="${transDetails.feeAmount}"/></td>
                       <td>$<fmt:formatNumber pattern="0.00" value="${transDetails.amount}"/></td>
                     </tr>
                     </c:forEach>
                      <tr bgcolor="#C0B8B6"></tr>
                   </table>
                   <div style="display: ${next};">
	                 	<a class="fr" onclick="doAjaxPostLoadFragmentPPTransPage('next');">Next <!-- &raquo; --></a>
                   </div>
                   <div style="display: ${pre};">
	                    <a  class="fr" onclick="doAjaxPostLoadFragmentPPTransPage('pre');">Pre <!-- &raquo; --></a>
                   </div>
                   <div class="clearfix"></div>

           </div>