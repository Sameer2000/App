<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all"  />
  <script>
  	function submitSearchForm(){
  		document.searchGameForm.action = "${pageContext.request.contextPath}/happ/admin/games";
  		document.searchGameForm.submit();
  	}
  </script>
   <div style="height:25px"></div>
   	<div id="bottom_container">
       <div class="inner_cont">
       	<div class="main_heading">Game&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       	<input style="border: none;" readonly="readonly" type="text" value="${game.restarted}"/>
       	<input style="border: none;" readonly="readonly" type="text" value="${game.id}"/></div>
           <!-- <div class="create_room"><a href="#">Create New Room</a></div> -->
          <div class="">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
               </div>
               
              
               <form name="searchGameForm" action="">
               
               <div align="right">
               <input type="text" placeholder="Search..." name="searchGame" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
               </form> 
           </div>
           <form:form name="gameForm" method="POST" modelAttribute="gameForm">
			   	
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="7%">Id</th>
                   <th width="10%">Name</th>
                   <th width="10%">Status</th>
                   <th width="15%">Message</th>
                   <th width="15%">Start Time</th>
                   <th width="11%">Scheduled On</th>
                   <th width="20%">Winner</th>
                   <th width="14%">Detail View</th>
                 </tr>
                 <c:forEach items="${gamesList.games}" var="game" varStatus="status">
					<tr>
						<td>${game.id}</td>
						<td>Game-${game.id}</td>
						<td>${game.status}</td>
						<td>${game.message}</td>
						<td>${game.startTime}</td>
						<td>${game.scheduledOn}</td>
						<td>${game.winner}</td>
						<td><a href="${pageContext.request.contextPath}/happ/admin/detailGames/${game.id}">View</a>    |    <a href="${pageContext.request.contextPath}/happ/admin/restartGames/${game.id}">Restart</a></td>
					</tr>
				</c:forEach>
				
               </table>
               <!-- PAGINATION   -->
			  
               	<table align="right">
					<tr>
						
					 <c:forEach begin="1" end="${pageCount}" varStatus="loop">
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/happ/admin/games?pageNumber=${loop.count}">
					  <c:out value="${loop.count}"/></c:forEach></a></td> 
					 
			  		</tr>
			  </table> 
               

           </div>
           </form:form>
           <!--end table-->
         </div>
       </div>
