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
                   <th width="10%">Request Date/Time</th>
                   <th width="27%">Query</th>
                   <th width="13%">Type Of KYL/Other</th>
                   <th width="24%">Suggestion</th>
                   <th width="11%">Satus</th>
                   <th width="8%">Reply</th>
                   <th width="10%">ViewAll</th>
                 </tr>
                 <c:forEach items="${kylList}" var="kyl" varStatus="status">
					<tr>
						<td >${kyl.reqDateTime}</td>
						<td>${kyl.request}</td>
						<td>${kyl.kylType.message}</td>
						<td>${kyl.suggestion}</td>
						<td>${kyl.kylStatus.message}</td>
						<td><a href="/kyl/reply/${kyl.userProfile.username}">Reply</a></td>
						<td><a href="/kyl/viewall/${kyl.userProfile.username}">ViewAll</a></td>
	 				</tr>
				 </c:forEach>
				
               </table>

           </div>
           <!--end table-->
         </div>
       </div>
