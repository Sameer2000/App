<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript">
function addStartTimeJS(){
}

$(document).ready(function(){
	  $("#addTimeAction").click(function(){
		  /* $("#add_start_time ul").append("<li><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00</li>"); */
		  $(".time_added_table").append("<tr><td><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00</td><td><img src=\"${pageContext.request.contextPath}/images/delete.png\" align=\"absmiddle\">&nbsp;<a href=\"#\">Delete</a></td></tr>");
	  });
	});

</script>

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
            	<div align="left" class="main_heading">User Detail:&nbsp;&nbsp;&nbsp;${usersDetails.name}</div>
                        	<div id="flip" align="left" style="height: 50%; width: 100%; background: #DFD9C3; " ><b>User Profile Detail</b></div>
                            <div id="panel">
                            	<table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#eee">
                                    <th width="15%" scope="col">User&nbsp;Name</th>
                                    <th width="15%" scope="col">Name</th>
                                    <th width="15%" scope="col">Phone</th>
                                    <th width="20%" scope="col">Email</th>
                                    <th width="10%" scope="col">State/Province</th>
                                    <th width="10" scope="col">Country</th>
                                    <th width="10%" scope="col">Zipcode</th>
                                  </tr>
                                  <tr>
                                    <td>${usersDetails.username}</td>
                                    <td>${usersDetails.name}</td>
                                     <td>${usersDetails.phone}</td>
                                     <td>${usersDetails.email}</td>
                                   <td>${usersDetails.state}</td>
                                  	 <td>${usersDetails.country}</td>
                                  <td>${usersDetails.zipcode}</td>
                                  </tr>
                                 <tr bgcolor="#eee">
                                    <th width="15%" scope="col">Age</th>
                                    <th width="15%" scope="col">Gender</th>
                                    <th width="15%" scope="col">Marital Status</th>
                                    <th width="20%" scope="col">Occupation</th>
                                    <th width="10%" scope="col">Address</th>
                                    <th width="5%" scope="col">Registration Date</th>
                                    <th width="5%" scope="col">Active</th>
                                  </tr>
                                  <tr>
                                    <td>${usersDetails.age}</td>
                                    <td>${usersDetails.gender}</td>
                                     <td>${usersDetails.maritalStatus}</td>
                                     <td>${usersDetails.occupation}</td>
                                   <td>${usersDetails.address1} <br> ${usersDetails.address2}</td>
                                  	 <td>${usersDetails.registrationDate}</td>
                                  <td>${usersDetails.active}</td>
                                  </tr>
                                 <tr bgcolor="#eee">
                                    <th width="15%" scope="col">DOB</th>
                                    <th width="15%" scope="col">DOB Time</th>
                                    <th width="15%" scope="col">DOB Place</th>
                                    <th width="20%" scope="col">DOB Country</th>
                                    <th width="10%" scope="col">Longitude</th>
                                    <th width="5%" scope="col">Latitude</th>
                                    <th width="5%" scope="col">UserType</th>
                                  </tr>
                                  <tr>
                                    <td>${usersDetails.dob}</td>
                                    <td>${usersDetails.dobtime}</td>
                                     <td>${usersDetails.dobplace}</td>
                                     <td>${usersDetails.dobcountry}</td>
                                  <td>${usersDetails.longitude}</td>
                                    <td>${usersDetails.latitude}</td>
                                    <td>${usersDetails.type}</td>
                                  </tr>
                                 
                                </table>
                                <!--  <a href="#">More &raquo;</a> -->
                               
                             </div> 
                              <div style="background:#C0B8B6; height: 10px "  align="left"> <table> <tr style="background: #CE2106;"></tr></table></div>
                       
                        	<div  id="flip2" align="left" style="height: 50%; width: 100%; background: #DFD9C3; " ><b>User Transaction Detail</b></div>
                            <div id="panel2">
                            
	                                <jsp:include page="user_detail_fragment_pptrans.jsp"/>
                                 
                             </div> 
                           <div style="background:#C0B8B6; height: 10px "  align="left"> <table> <tr  style="background: #CE2106;"></tr></table></div>


 
                        	<div  id="flip3" align="left" style="height: 50%; width: 100%; background: #DFD9C3; " ><b>User Account Detail</b></div>
                            <div id="panel3">
                            	<jsp:include page="user_detail_fragment_account.jsp" />
                             </div> 
                           <div style="background:#C0B8B6; height: 10px "  align="left"> <table> <tr style="background: #CE2106;"> </tr></table></div>
                     
                 
             
            </div>
	</section>
