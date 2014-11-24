<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

                	<div class="right-Container2">
                    	<div class="page_heading">Dashboard</div>
						<div style="float: left;width: 95%;margin: 20px 0 0 15px;">
							<jsp:include page="fragment_trans_page.jsp" />
						</div>
                        <div>
                        <!--Played Game-->
                        <div class="db_box fl">
                        	<div class="brown_heading">Latest Played Game</div>
                            <div class="db_table_box">
                            	<table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#eee">
                                    <th width="47%" scope="col">Date / Time</th>
                                    <th width="53%" scope="col">Game Name</th> 
                                  </tr>
                                  <c:choose>
                                  <c:when test="${not empty pgLst}">
                                  <c:forEach items="${pgLst}" var="playedgame" varStatus="status">
                                  <tr>
                                  	<td>${playedgame.lastUpdatedTime}</td>
                                  	<td>$${playedgame.playedGameAmount}</td>
                                  </tr>
                                  </c:forEach>
                                  </c:when>
                                  <c:otherwise>
                                  <tr>
                                  	<td align="center" colspan="2">No Data Available.</td>
                                  </tr>
								  </c:otherwise>
								</c:choose>
                                </table>
                                <a href="${pageContext.request.contextPath}/happ/site/game_history_page" class="fr">More &raquo;</a>
                                <div class="clearfix"></div>
                               </div>
                        </div>
                       <!--end Played Game--> 
                       <!--Redeem-->
                        <div class="db_box fr">
                        	<div class="brown_heading">Latest Redeem</div>
                            <div class="db_table_box">
                            	<table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#eee">
                                    <th width="47%" scope="col">Date / Time</th>
                                    <th width="53%" scope="col">Amount</th> 
                                  </tr>
                                  <c:choose>
                                  <c:when test="${not empty rdLst}">
                                  <c:forEach items="${rdLst}" var="redeem" varStatus="status">
                                  <tr>
                                  	<td>${redeem.lastUpdatedTime}</td>
                                  	<td>$<fmt:formatNumber pattern="0.00" value="${redeem.redeemAmount}"/></td>
                                  </tr>
                                  </c:forEach>
                                  </c:when>
                                  <c:otherwise>
                                  <tr>
                                  	<td align="center" colspan="2">No Data Available.</td>
                                  </tr>
								  </c:otherwise>
								</c:choose>
                                </table>
                                <a href="${pageContext.request.contextPath}/happ/site/redeem" class="fr">More &raquo;</a>
                                <div class="clearfix"></div>
                               </div>
                        </div>
                       <!--end Redeem-->
                       <div class="clearfix"></div>
                       </div>
                       <div>
                       <!--Balance-->
                        <div class="db_box fr">
                        	<div class="brown_heading">Available Balance</div>
                           	<div class="total_ball">
                            	<img src="${pageContext.request.contextPath}/images/balance.png" align="absmiddle">&nbsp;&nbsp;$<fmt:formatNumber pattern="0.00" value="${uab.totalAvailableBalance}"/>
                            </div>
                        </div>
                       <!--end Balance-->
                       <div class="clearfix"></div>
                       </div>
                    </div>
               