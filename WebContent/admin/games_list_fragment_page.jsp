<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

 <script type="text/javascript">
 function fn(gameId){
	 var cmt = confirm("Do you want restart this game !");
	 if (cmt == true) {
		 doAjaxPostLoadFragmentGamePage(gameId);
         return true;
     }
     else {
         return false;
     }
 }

</script>
<script type="text/javascript">

	function doAjaxPostLoadFragmentGamePage(gameId) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/happ/admin/restartGames/"+gameId,
            data:"",
            success: function(response) {
                $("#subViewDiv").html(response);
            }
        });
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

			<div id="subViewDiv">	   	
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
						<td><a href="${pageContext.request.contextPath}/happ/admin/detailGames/${game.id}">View</a>    |    <a href="#" onclick="fn('${game.id}')">Restart</a></td>
					</tr>
				</c:forEach>
				
               </table>
               <!-- PAGINATION   -->
			  
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
					 <td id="pagenation"><a href="${pageContext.request.contextPath}/happ/admin/games?p=${loop.index}">
					  <c:out value="${loop.index}"/></a></td> 
					 </c:forEach>
			  		</tr>
			  </table> </form>
               </div>

           </div>
