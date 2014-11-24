<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script>
  	function submitSearchForm(){
  		document.searchQueryForm.action = "/HWebapp/happ/admin/cntUsQuery";
  		document.searchQueryForm.submit();
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
       	<div class="main_heading">View Query</div>
           <!-- <div class="create_room"><a href="#">Create New Room</a></div> -->
           <div class="show">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries
               </div></div>
               <form name="searchQueryForm" action="">
               
               <div align="right">
               <input type="text" placeholder="Search..." name="search1" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
               </form>
               <div class="clearfix"></div>
            
           <!--table-->
           <div class="table">
           	<table width="100%" border="1" cellspacing="0" bordercolor="#cfcfcf" cellpadding="0" style="border-collapse:collapse">
                 <tr class="fbold wtxt">
                   <th width="20%">Name</th>
                   <th width="23%">Email</th>
                   <th width="15%">phone</th>
                   <th width="30%">Message</th>
                   <th width="12%">Response</th>
                   <th width="12%">Reply</th>
                   
                 </tr>
                 <c:forEach items="${contactusList}" var="contactus"
				varStatus="status">
				<tr>
					<td >${contactus.name}</td>
					<td>${contactus.email}</td>
					<td>${contactus.phone}</td>
					<td>${contactus.message}</td>
					<td>${contactus.reply}</td>
					<td><a href="cntUsQuery/reply/${contactus.id}">Reply</a></td>
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
						
					 <c:forEach begin="${min}" end="${max}" varStatus="loop">
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/happ/admin/cntUsQuery?p=${loop.index}">
					  <c:out value="${loop.index}"/></c:forEach></a></td> 
					 
			  		</tr>
			  </table> </form>
           </div>
           <!--end table-->
         </div>
       </div>
