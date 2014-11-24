<%@page import="java.util.TimeZone"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
   
     <script>
  	function submitSearchForm(){
  		document.searchRoomForm.action = "${pageContext.request.contextPath}/happ/admin/redeems";
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
       	<div class="main_heading">Re-Deem Report</div>
         <!--   <div class="create_room"></div> -->
            
          
          <div class="">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </div>
           </div>
           
           
           <form name="searchRoomForm" action="">
               
               <div align="right">
               <input type="text" placeholder="Search..." name="searchredeem" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
           </form>
           
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="5%">Id</th>
                     <th width="10%">User</th>
                   <th width="5%">Payer Id</th>
                   <th width="10%">Requested Time</th>
                   <th width="5%">Type</th>
                   <th width="10%">Completed Time</th>
                   <th width="10%">Trans Status</th>
                  
                    <th width="10%">Amount</th>
                   <th width="10%">Edit</th>
                 <!--   <th width="14%">Delete</th> -->
                 </tr>
                 <c:forEach items="${redeemList}" var="redee"
				varStatus="status">
				<tr>
					<td >${redee.id}</td>
					<td>${redee.username}</td>
					<td>${redee.payerId}</td>
					<td>
			           	<c:set var="now1" value="${redee.requestedTime} ${redee.timeZone}" />
                       	<fmt:parseDate value="${now1}" var="parsedEmpDate"	pattern="yyyy-MM-dd HH:mm:ss z" />
                   		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${parsedEmpDate}" />
					</td>
					<td>${redee.redeemType}</td>
					<td>${redee.completedTime}</td>
					<td>${redee.transStatus}</td>
					<td>$<fmt:formatNumber pattern="0.00" value="${redee.amount}"/></td>
					<td><img src="${pageContext.request.contextPath}/images/edit.png" align="absmiddle">
					 <a href="${pageContext.request.contextPath}/happ/admin/editRedeem/${redee.id}">Edit</a></td>
 				</tr>
			</c:forEach>
				
               </table>
               
               <!-- PAGINATION   -->
			  <form  >
               	<table style="width:20%" align="right">
					<tr >
						<td id="pagenation">Page
							<% if(request.getParameter("p")==null){%>
								<%out.print(1);}
							else{%>
							<%=request.getParameter("p") %><%}
						%>&nbsp;of&nbsp;${pageCount}
						<input  style="width: 25%" type="text" name="p" id="p" value="<%=request.getParameter("p") %>" onkeypress="return runScript(event)"/></td>	
						 <c:forEach begin="${min}" end="${max}" varStatus="loop">
							 <td id="pagenation">
								 <a href="${pageContext.request.contextPath}/happ/admin/redeems?p=${loop.index}">
								 	<c:out value="${loop.index}"/>
								 </a>
							 </td> 
						 </c:forEach>
			  		</tr>
			  </table> </form>
 

			<!-- // PAGINATION  -->

           </div>
           <!--end table-->
         </div>
       </div></body>

      
       
       