<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



                	<div class="right-Container2">
                    	<div class="page_heading">Played Game</div>
                        <h2 class="red_text">List of all Played Games by users:</h2>
                        <div class="table_box">
                        	<table width="100%" border="1" cellspacing="0" bordercolor="#d9d9d9" cellpadding="0">
                                  <tr>
                                    <th width="5%" scope="col">Id</th>
                                    <th width="11%" scope="col">Game Name</th>
                                    <th width="11%" scope="col">Date/Time</th>
                                    <th width="15%" scope="col">Game Status</th>
                                    <th width="11%" scope="col">Winner</th>
                                    <th width="13%" scope="col">Category</th>
                                  </tr>
                                  <c:forEach items="${playedgamelist}" var="playedgame" varStatus="status">
										<tr>
											<td>${playedgame.id}</td>
											<td>Game-${playedgame.id}</td>
											<td>${playedgame.startTime}</td>
											<td>${playedgame.message}</td>
											<td>${playedgame.winners}</td>
											<td>${playedgame.room.level1.desc}</td>
						 				</tr>
								   </c:forEach>
                                  <!-- <tr>
                                    <td>24/08/2013</td>
                                    <td>Single</td>
                                    <td>John Carter</td>
                                    <td>Roulet</td>
                                    <td>Trial</td>
                                  </tr>
                                   <tr>
                                    <td>26/08/2013</td>
                                    <td>Multi Player</td>
                                    <td>Petter Carter</td>
                                    <td>Poker</td>
                                    <td>Payed</td>
                                  </tr> -->
                                </table>
							
                    </div> 
                    <!-- <h2 class="red_text">My Ticket:</h2>
                    <div class="ticket">
                    	<img src="images/ticket.gif">
                    </div> -->
                    </div>
                