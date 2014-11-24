    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calendar1.css" media="all">
<script type="text/javascript">
		  $(function() {
		  if ($.browser.msie && $.browser.version.substr(0,1)<7)
		  {
		  $('li').has('ul').mouseover(function(){
		    $(this).children('ul').css('visibility','visible');
		    }).mouseout(function(){
		    $(this).children('ul').css('visibility','hidden');
		    })
		  }
		}); 
</script>
<style>
.error {
  color: #ff0000;
}
 
.errorblock {
  color: #000;
  background-color: #ffEEEE;
  border: 3px solid #ff0000;
  padding: 8px;
  margin: 16px;
}
</style>

<script> 
$(document).ready(function(){
  $("#flip").click(function(){
    $("#panel").slideToggle("slow");
  });
});
</script>
<script> 
$(document).ready(function(){
    $("#flip2").click(function(){
      $("#panel2").slideToggle("slow");
    });
  });
</script>
<script> 
$(document).ready(function(){
    $("#flip3").click(function(){
      $("#panel3").slideToggle("slow");
    });
  });
</script>
       <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
          <div id="bottom_container">
            <div align="center" class="inner_cont">
              <div align="left" class="main_heading">Game Detail:&nbsp;&nbsp;&nbsp;${playerList}</div>
                  <div id="flip" align="left" class="detail_header" ><b>Game Detail</b></div>
                    <div id="panel">
                      <table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                          <tr bgcolor="#eee">
                            <th width="10%" scope="col">GameId</th>
                            <th width="10%" scope="col">Status</th>
                            <th width="15%" scope="col">Message</th>
                            <th width="20%" scope="col">Start Time</th>
                            <th width="10%" scope="col">Scheduled On</th>
                            <th width="10%" scope="col">Player Count</th>
                            <th width="10%" scope="col">Category</th>
                          </tr>
                          <tr>
                            <td>${gameDetails.id}</td>
                            <td>${gameDetails.status}</td>
                            <td>${gameDetails.message}</td>
                            <td>${gameDetails.startTime}</td>
                            <td>${gameDetails.scheduledOn}</td>
                            <td>${gameDetails.playerCount}</td>
                            <td>${gameDetails.room.level1}</td>
                          </tr>
                        </table>
                         <!-- <a href="#">More &raquo;</a> -->
                     </div> 
                      <div class="detail_footer"  align="left"> <table> <tr  ></tr></table></div>

                  <div  id="flip2" align="left" class="detail_header" ><b>Player Detail</b></div>
                    <div id="panel2">
                      <table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                          <tr bgcolor="#eee">
							<th width="20%" scope="col">Name</th>
							<th width="15%" scope="col">Email Id</th>
							<th width="15%" scope="col">Confirmed</th>
							<th width="15%" scope="col">WinCount</th>
							<th width="15%" scope="col">WinAmount</th>
							<th width="10%" scope="col">Status</th>
                          </tr>
                            <c:forEach items="${gameDetails.players}" var="player">
                              <tr>
								<td  width="20%">${player.user.name}</td>
								<td  width="20%">${player.user.email}</td>
								<td  width="20%">${player.confirmed}</td>
								<td  width="20%">${player.winCount}</td>
								<td  width="20%">$<fmt:formatNumber pattern="0.00" value="${player.winAmount}"/></td>
								<td  width="20%" >${statusDetails.confirmed}</td>
                              </tr>
                            </c:forEach>
                           <tr bgcolor="#C0B8B6"></tr>
                        </table>
                         <!-- <a href="#">More &raquo;</a> -->
                         
                     </div> 
                   <div class="detail_footer"  align="left"> <table> <tr  ></tr></table></div>

                  <div  id="flip3" align="left" class="detail_header" ><b>Winner's</b></div>
                    <div id="panel3">
                      <table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                          <tr bgcolor="#eee">
							<th width="20%" scope="col">Name</th>
							<th width="20%" scope="col">Username</th>
							<th width="15%" scope="col">Win Amount</th>
							<th width="15%" scope="col">Win Fee</th>
							<th width="15%" scope="col">Win</th>
							<th width="15%" scope="col">Trans Status</th>
                          </tr>
                             <c:forEach items="${gameDetails.winners}" var="winDetail">
                                <tr>
                                  <td>${winDetail.playerName}</td>
                                 	<td>${winDetail.username}</td>
                                	<td>$<fmt:formatNumber pattern="0.00" value="${winDetail.winAmount}"/></td>
                                	<td>$<fmt:formatNumber pattern="0.00" value="${winDetail.winFeeAmount}"/></td>
                                 	<td  width="15%">${winDetail.rowWinner}</td>
                                  <td  width="15%">${winDetail.transStatus}</td>
                                </tr>
                           </c:forEach> 
                           <tr bgcolor="#C0B8B6"></tr>
                        </table>
                         <!-- <a href="#">More &raquo;</a> -->
                         
                     </div> 
                   <div class="detail_footer"  align="left"> <table> <tr  ></tr></table></div>

                  <div  id="flip4" align="left" class="detail_header" ><b>Room Detail</b></div>
                    <div id="panel4">
                      <table  width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                      
                          <tr bgcolor="#eee">
                            <th width="20%" scope="col">Name</th>
                            <th width="10%" scope="col">Price</th>
                             <th width="10%" scope="col">Room Charges %</th>
                            <th width="15%" scope="col">Schedule On</th>
                            <th width="15%" scope="col">Start Time</th>
                            <th width="10%" scope="col">Allow User Count</th>
                            <th width="10%" scope="col">Active</th>
                             <th width="10%" scope="col">Level</th>
                          </tr>
                          <tr>
                              <td>${gameDetails.room.name}</td>
                              <td>$<fmt:formatNumber pattern="0.00" value="${gameDetails.room.price}"/></td>
                              <td>${gameDetails.room.winningChargeInPercentage}</td>
                               <td>${gameDetails.room.scheduleOn}</td>
                              <td>${gameDetails.room.startTime }</td>
                               <td>${gameDetails.room.allowUserCount }</td>
                                <td>${gameDetails.room.active }</td>
                                <td>${gameDetails.room.level1}</td>
                          </tr>
                        </table>
                        <!-- <a href="#">More &raquo;</a> -->
                     </div> 
                   <div class="detail_footer" align="left"> <table> <tr  > </tr></table></div>
                     
                 
             
            </div>
  </section>
