<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

   <div style="height:25px"></div>
   	<div id="bottom_container">
       <div class="inner_cont">
       	<div class="main_heading">Room</div>
           <div class="create_room"><a href="#">Create New Room</a></div>
           <div class="show">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries
               </div>
               <input type="search" placeholder="Search...">
               <div class="clearfix"></div>
           </div>
           
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="20%">id</th>
                   <th width="27%">confirmed</th>
                   <th width="15%"></th>
                   <th width="13%">phone</th>
                   <th width="10%">Country</th>
                   <th width="11%">active</th>
                   <th width="8%">View</th>
                   <th width="14%">Delete</th>
                 </tr>
                 <c:forEach items="${playerList.players}" var="player"
				varStatus="status">
				<tr>
					<td >${player.name}</td>
					<td>${player.email}</td>
					<td>${player.password}</td>
					<td>${player.phone}</td>
					<td>${player.country}</td>
					<td>true</td>
					<td><img src="${pageContext.request.contextPath}/images/edit.png" align="absmiddle"> <a href="#">Edit</a></td>
					<td><img src="${pageContext.request.contextPath}/images/delete.png" align="absmiddle"> <a href="#">Delete</a></td>
 				</tr>
			</c:forEach>
				
               </table>

           </div>
           <!--end table-->
         </div>
       </div>
