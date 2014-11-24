<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

 <div class="right-Container2">
                    	<div class="page_heading">Games Played</div>
                       
                        <!--Know Winning History-->
                        	<div class="kyl_box">
                               <div class="brown_heading">Detail</div>
                               <table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#e5e5e5">
                                    <th width="5%" scope="col">Id</th>
                                    <th width="10%" scope="col">Date/Time</th>
                                    <th width="10%" scope="col">Status</th>
                                    <th width="10%" scope="col">Total Players</th>
                                    <th width="20%" scope="col">Winner</th>
                                    <th width="10%" scope="col">Room Price</th>
                                    <th width="10%" scope="col">Level</th>
                                  </tr>
                                  <c:forEach items="${wingamelist}" var="wingame" varStatus="status">
										<tr>
											<td>Game-${wingame.id}</td>
											<td>${wingame.startTime}</td>
											<td>${wingame.message}</td>
											<td>${wingame.playerCount}</td>
											<td>
											  <table>
											  <tr><th>Player Name</th><th>Win Amount</th><th>Message</th></tr>
			                                  <c:forEach items="${wingame.winners}" var="winner" varStatus="status1">
													<tr>
														<td nowrap="nowrap">
														 ${winner.playerName}
														</td>
														<td>
															$<fmt:formatNumber type="number" maxFractionDigits="2" value="${winner.winAmount}" /></td>
														<td>
															${winner.rowWinner}
														</td>
													</tr>
													
												</c:forEach>		
											  </table>
											</td>
											<td>$<fmt:formatNumber pattern="0.00" value="${wingame.room.price}"/></td>
											<td>${wingame.room.level1.desc}</td>
						 				</tr>
								   </c:forEach>
                              </table>
								<!-- <div class="pagination">
                                	<ul>
                                    	<li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">&raquo;</a></li>
                                    </ul>
                                </div> -->
	                            <div style="display: ${next};">
		                            <a href="${pageContext.request.contextPath}/happ/site/game_history_page?p=next" class="fr" >Next <!-- &raquo; --></a>
	                            </div>
	                            <div style="display: ${pre};">
		                            <a href="${pageContext.request.contextPath}/happ/site/game_history_page?p=pre" class="fr" >Pre <!-- &raquo; --></a>
	                            </div>
                                <div class="clearfix"></div>
                            </div>
                        <!--end Winning History-->
                    </div>