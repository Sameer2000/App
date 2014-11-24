<%@page import="com.housee.app.model.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%><!-- FOR PAGINATION   -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <script>
  	function submitSearchForm(){
  		document.searchUserForm.action = "${pageContext.request.contextPath}/happ/admin/users";
  		document.searchUserForm.submit();
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
       	<div class="main_heading">User</div>
           <!-- <div class="create_room"><a href="#">Create New Room</a></div> -->
          
           <div class="">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <a href="createUser"style="text-decoration: none;"><b>Create New User</b></a>
               </div>
               
              
               <form name="searchUserForm" action="">
               
               <div align="right">
               <input type="text" placeholder="Search..." name="search1" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
               </form> 
           </div>
           
           
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="20%">Name</th>
                   <th width="20%">Email</th>
                   <!-- <th width="15%">password</th> -->
                   <th width="20%">phone</th>
                   <th width="10%">Country</th>
                   <th width="10%">active</th>
                   <th width="10%">UserType</th>
                   <th width="10%">Detail</th>
                 <!--   <th width="14%">Delete</th> -->
                 </tr>
                 <c:forEach items="${usersList.users}" var="user" varStatus="status">
				<tr>
					<td >${user.name}</td>
					<td>${user.email}</td>
					<%-- <td>${user.password}</td> --%>
					<td>${user.phone}</td>
					<td>${user.country}</td>
					<td>true</td>
					<td>${user.type.message}</td>
					<td><!-- <img src="${pageContext.request.contextPath}/images/edit.png" align="absmiddle"><a href="editUserType/${user.id}">View</a>-->
							<a href="detailUsers/${user.id}">View</a></td>
					<%-- <td><img src="${pageContext.request.contextPath}/images/delete.png" align="absmiddle"> <a href="deleteUserFromList/${user.id}">Delete</a></td> --%>
 				</tr>
 				
			</c:forEach>
			

			
		
               </table>
               	<!-- PAGINATION   -->
			  <form  >
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
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/happ/admin/users?p=${loop.index}">
					  <c:out value="${loop.index}"/></c:forEach></a></td> 
					 
			  		</tr>
			  </table> </form>

			<!-- // PAGINATION  -->

           </div>
           <!--end table-->
         </div>
       </div>