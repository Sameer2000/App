<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

  

                	<div class="right-Container2">
                    	<div class="page_heading">My Account</div>
                        <h2 class="red_text">Transaction History:</h2>
                        <div class="table_box">
							<jsp:include page="fragment_pptrans_page.jsp" />
                        </div>
                        <h2 class="red_text">Redeem Amount:</h2>
                        <div class="redeem_box">
                        	<div class="div_box">
                            	<div class="t_name">Total Recharge Amount</div>
                                <div class="t_value">$<fmt:formatNumber pattern="0.00" value="${usertotalbalance.totalRechargeAmount}"/></div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="div_box">
                            	<div class="t_name">Total Played Game</div>
                                <div class="t_value">$<fmt:formatNumber pattern="0.00" value="${usertotalbalance.totalPlayeGameAmount}"/></div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="div_box">
                            	<div class="t_name">Total Win Amount</div>
                                <div class="t_value">$<fmt:formatNumber pattern="0.00" value="${usertotalbalance.totalWinAmount}"/></div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="div_box">
                            	<div class="t_name">Total Redeem Amount</div>
                                <div class="t_value">$<fmt:formatNumber pattern="0.00" value="${usertotalbalance.totalRedeemAmount}"/></div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="div_box">
                            	<div class="t_name">Total Fee Amount</div>
                                <div class="t_value">$<fmt:formatNumber pattern="0.00" value="${usertotalbalance.feeAmount}"/></div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="div_box">
                            	<div class="t_name2">Total Balance</div>
                                <div class="t_value2">$<fmt:formatNumber pattern="0.00" value="${useravailablebalance.totalAvailableBalance}"/>
                                	<a href="${pageContext.request.contextPath}/happ/site/redeem" class="blue_btn">Redeem</a>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                      
                