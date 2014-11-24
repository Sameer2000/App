<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script>
  	function submitSearchForm(){
  		document.searchRoomForm.action = "${pageContext.request.contextPath}/happ/admin/rooms";
  		document.searchRoomForm.submit();
  	}
  	
  	function runScript(e) {
  	    if (e.keyCode == 13) {//Enter key
  	    	if(document.getElementById("p").value.match(/^\+?(|[1-9]\d*)$/)){
  			}else{
  				alert('Please input numeric characters only'); 
  				document.getElementById("p").value="";
  				 return false;
  			}
  	        return true;
  	    }
  	}
  </script>
 <script type="text/javascript">
 
 function blankData() {
	 document.getElementById("p").value="";
	 }
 
 </script> 
 <body onload="blankData()">

   <div style="height:25px"></div>
   	<div id="bottom_container">
       <div class="inner_cont">
       	<div class="main_heading">Room</div>
           <div class="create_room"><a href="${pageContext.request.contextPath}/happ/admin/createRoom">Create New Room</a></div>
           <div class="show">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries
               </div></div>
             
               <form name="searchRoomForm" action="${pageContext.request.contextPath}/happ/admin/rooms?p=${loop.count}">

               
               <div align="right">
               <input type="text" placeholder="Search..." name="search1" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
               </form>
               
               <div class="clearfix"></div>
           
           
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="17%">Name</th>
                   <th width="7%">Price</th>
                   <th width="5%">Max Player</th>
                   <th width="13%">Category</th>
                   <th width="7%">Active</th>
                   <th width="11%">Country</th>
                   <th width="9%">Keyword</th>
                   <th width="10%">Level1</th>
                   <th width="5%">Room Charge %</th>
                   <th width="8%">Edit</th>
                 </tr>
                 <c:forEach items="${roomsList.rooms}" var="room"
				varStatus="status">
				<tr>
					<td >${room.name}</td>
					<td>$<fmt:formatNumber pattern="0.00" value="${room.price}"/></td>
					<td>${room.allowUserCount}</td>
					<td>${room.category.message}</td>
					<td>${room.active}</td>
					<td>${room.country}</td>
					<td>${room.keywork}</td>
					<td>${room.level1.message}</td>
					<td>${room.winningChargeInPercentage}%</td>
					<td><img src="${pageContext.request.contextPath}/images/edit.png" align="absmiddle"> <a href="${pageContext.request.contextPath}/happ/admin/editRoom/${room.id}">Edit</a></td>
 				</tr>
			</c:forEach>
				
               </table>
			  
               <form name="form2">
               	<table style="width:20%" align="right">

					<tr>
					<td id="pagenation">Page
							<% if(request.getParameter("p")==null){%>
								<%out.print(1);}
							else{%>
							<%=request.getParameter("p") %><%}
						%>&nbsp;of&nbsp;${pageCount}
						<input  style="width: 25%" type="text" name="p" id="p" value="<%=request.getParameter("p") %>" onkeypress="return runScript(event)"/></td>	
					 <c:forEach begin="${min}" end="${max}" varStatus="loop">
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/happ/admin/rooms?p=${loop.index}">
					  <c:out value="${loop.index}"/></a></td> 
					 </c:forEach>
			  		</tr>
			  </table></form> 
			 
           </div>
         </div>
       </div>
