<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="JavaScript">
	function submitForm()
	{
	  document.rechargeAmountForm.submit();
	}
</script>

                	<div class="right-Container2">
		               <form:form name="rechargeAmountForm" method="post" action="recharge_amount_page" modelAttribute="rechargeAmountForm">
                    	<div class="page_heading">Recharge Amount</div>
                        	<div class="recharge_amount">
                            	<label>Enter Amount</label>
                                <input type="text" name="amount">
                                <input type="submit" value="Procceed to Payment">
                                <img src="${pageContext.request.contextPath}/images/help.png" onclick="submitForm()" align="absmiddle" title="Need help to recharge amount?">
                                
                            </div>
                            <div class="about_payment">
                            	<div class="pay_box fl">
                                	<h3>Online Payment Partner</h3>
                                    <img src="${pageContext.request.contextPath}/images/paypal_small.png">
                                </div>
                                <div class="pay_box fr">
                                	<h3>Payment Secured</h3>
                                    <img src="${pageContext.request.contextPath}/images/secured.png">
                                </div>
                                <div class="clearfix"></div>
                            </div>  
        				</form:form>                    
                    </div>
               