<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <script>
  	function submitSearchForm(){
  		document.searchUserForm.action = "${pageContext.request.contextPath}/happ/admin/kyl";
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
       	<div class="main_heading">KYL Request</div>
           <div class="create_room"><a href="${pageContext.request.contextPath}/happ/admin/sendPN">Send Email/Push Notification</a></div>
           <div class="show">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries
               </div>                               
               <div class="clearfix"></div>              
           </div>
           
           <form name="searchUserForm" action="">
           		<div align="right" style="margin-top: -2.5%">
           			<input type="text" placeholder="Search..." name="search1" id="search"/>
           			<input type="button" value="Search" onclick="submitSearchForm();" />
           			<a href="kyl?v=a" style="margin-left: 1%">ViewAll</a>
           		</div> 
           </form>  
               
                <!-- <a href="kyl/view" style="margin-left: 1%">ViewAll</a> -->         
                
           
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse" >
                 <tr class="fbold wtxt">
                   <th width="10%">Request Date/Time</th>
                   <th width="27%">Query</th>
                   <th width="13%">Type Of KYL/Other</th>
                   <th width="24%">Suggestion</th>
                   <th width="11%">Satus</th>
                   <th width="8%">Reply</th>
                   <!-- <th width="10%">Detail</th> -->
                 </tr>
                 <c:forEach items="${kylList}" var="kyl" varStatus="status">
					<tr>
						<td>${kyl.reqDateTime}</td>
						<td>${kyl.request}</td>
						<td>${kyl.kylType.message}</td>
						<td>${kyl.suggestion}</td>
						<td>${kyl.kylStatus.message}</td>
						<td><a href="kyl/reply/${kyl.id}">Reply</a></td>
						<%-- <td><a href="kyl/viewall/${kyl.userProfile.username}">ViewAll</a></td> --%>
	 				</tr>
				 </c:forEach>
               </table>
               
              <form>
               	<table style="width:20%" align="right">
					<tr>
					<td id="pagenation">Page
							<% if(request.getParameter("p")==null){%>
								<%out.print(1);}
							else{%>
							<%=request.getParameter("p") %><%}
						%>&nbsp;of&nbsp;${pageCount}
						<input  style="width: 25%" type="text" name="p" id="p" value="<%=request.getParameter("p") %>" onkeypress="return runScript(event)"/></td>	
					 <c:forEach begin="1" end="${pageCount}" varStatus="loop">
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/HWebapp/happ/admin/kyl?p=${loop.index}">
					  <c:out value="${loop.index}"/></c:forEach></a></td> 
					 
			  		</tr>
			  </table></form>

           </div>
           <!--end table-->
         </div>
       </div>
