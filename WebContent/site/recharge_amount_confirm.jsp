<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

              	<div class="right-Container2">
              		<form:form name="rechargeAmountForm" method="post" action="recharge_amount_confirm_page" modelAttribute="rechargeAmountForm" >
                    <div>
                    	<input type="hidden" name="paymentgateway" value="paypal"/>
						<input type="hidden" name="amount" value="${rechargeamount}"/>                     
						<input type="hidden" name="currencycode" value="USD"/>                     
                    </div>  
                  	<div class="page_heading">Recharge Amount</div>
                      	<div class="pay_note">
                          	<div class="note_heading"><img src="${pageContext.request.contextPath}/images/note-edit.png" align="absmiddle">&nbsp;Note</div>
                              <div class="note_disc">After clicking on the <strong>"Continue"</strong> button, you will be
							redirected to a secure payment gateway.</div> 
							<div class="note_heading"><img src="${pageContext.request.contextPath}/images/note-edit.png" align="absmiddle">&nbsp;Note</div>
							<div class="note_disc">Including<strong> paypal</strong> service charges.</div>
						<div class="clearfix"></div>
                          </div>
                          <div class="about_payment">
                          	Amount: <span class="amount_value"><!-- <img src="${pageContext.request.contextPath}/images/currency.png" align="absmiddle"> -->&nbsp;${rechargeamount} USD</span>  
                          	<input type="submit" value="Continue" class="">
                          </div>
                  	</form:form>
                  </div>
