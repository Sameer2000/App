<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
						$(document).ready(function(	){

						$('body').on('click', '.delStartTime', function () {
						$(this).parents("tr").find(':checkbox').removeAttr('checked');
					});

						$("#addTimeAction").click(function(){
						/* $("#add_start_time ul").append("<li><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00</li>"); */
						$(".time_added_table").append("<tr><td><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"</td><td><img src=\"${pageContext.request.contextPath}/images/delete.png\" align=\"absmiddle\">&nbsp;<a class=\"delStartTime\">Delete</a></td></tr>");
						});
					});

</script>
<!-- <script type="text/javascript">
function addStartTimeJS(){
}

$(document).ready(function(){
	  $("#addTimeAction").click(function(){
		  /* $("#add_start_time ul").append("<li><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00</li>"); */
		  $(".time_added_table").append("<tr><td><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"</td><td><img src=\"${pageContext.request.contextPath}/images/delete.png\" align=\"absmiddle\">&nbsp;<a href=\"#\">Delete</a></td></tr>");
	  });
	});

</script> -->
       <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Create New Room</div>
                <div id="create_room_form_box">
			   	<form:form name="crateRoomForm" method="POST" modelAttribute="createRoomForm" action="saveRoom">
			   		<form:errors path="*" cssClass="errorblock" element="div" />
                	<div class="sepbox">
                        <form:label path="name">Room Name : </form:label>
                        <form:input path="name" cssClass="text" />
                        <form:errors path="name" cssClass="error"></form:errors>
                    </div>
                    <div class="sepbox">
                        <form:label path="price">Price : </form:label>
                        <form:input path="price" cssClass="text" />
                        <form:errors path="price" cssClass="error"></form:errors>
                    </div>
                   
                     <div class="sepbox">  
                         <form:label path="winningChargeInPercentage">Room Charge % : </form:label>
                        <form:input path="winningChargeInPercentage" cssClass="text" />
                        <form:errors path="winningChargeInPercentage" cssClass="error"></form:errors>
                    </div>
                     <div class="sepbox">
                        <form:label path="active">Active : </form:label>
                        <form:checkbox path="active" value="1"/>
                        <form:errors path="active" cssClass="error"></form:errors>
                     </div>
                    <div class="sepbox">
                        <form:label path="category">Category : </form:label>
                        <form:select path="category">
                        		<form:options items="${roomCategory}" />
                        </form:select>
                    </div>
                     <div class="sepbox">
                        <form:label path="maxUserCount">Max Users : </form:label>
                        <form:select path="maxUserCount">
                             	<form:option value="20" label="20"/>
                             	<form:option value="30" label="30"/>
                             	<form:option value="40" label="40"/>
								<form:option value="50" label="50"/>																
                        </form:select> 
                    </div>
                    <div class="sepbox">
                        <form:label path="allowCountryCode">Country Code : </form:label>
                        <form:select path="allowCountryCode">
                             	<form:option value="0" label="Open for all"/>
                             	<form:option value="1" label="U.S. Only"/>
                             	<form:option value="2" label="U.K. Only"/>
								<form:option value="3" label="Asia Only"/>																
                        </form:select> 
                    </div>
                    <div class="sepbox">
                        <form:label path="level1">Level : </form:label>
                        <form:select path="level1">
                        		<form:options items="${gameLevel}" />
                        </form:select>
                    </div>
                    <div class="sepbox">
                        <form:label path="scheduleType">Schedule : </form:label>
                        <!-- <input type="radio" name="scheduleType" value="0">&nbsp;<b>Daily</b>
                        <input type="radio" name="scheduleType" value="1">&nbsp;<b>Weekly</b> -->
	                    <form:errors path="scheduleOn" cssClass="error"></form:errors>
                        <div class="option">
	                        <input type="radio" name="scheduleType" value="0">&nbsp;<b>Daily</b>
                        	<input type="radio" name="scheduleType" value="1">&nbsp;<b>Weekly</b>
	                         <div class="option_list">
	                        	<ul>
	                        		<li><input type="checkbox" name="scheduleOn" value="Monday" >&nbsp;Monday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Tuesday">&nbsp;Tuesday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Wednesday">&nbsp;Wednessday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Thursday">&nbsp;Thursday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Friday">&nbsp;Friday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Saturday">&nbsp;Satday</li>
	                                <li><input type="checkbox" name="scheduleOn" value="Sunday">&nbsp;Sunday</li>
	                            </ul>
	                        </div>
                        </div>
                        <div class="clearfix"></div> 
                    </div>
                    <div class="sepbox">
                        <label>Start Time :</label>
                        <%-- <form:select path="HH">
                        		<form:options items="${hours}" />
                        </form:select>
                        <form:select path="MM">
                        		<form:options items="${minutes}" />
                        </form:select> --%>
                        <input type="text" id="HH" placeholder="HH" style="width:40px; margin-left:15px">
                        <input type="text" id="MM" placeholder="MM" style="width:40px; margin-left:5px">
                        <select name="Locale" id="Locale">
                        	<option value="GMT">GMT</option>
                        	<option value="IST" selected="selected">IST</option>
                        </select>
                        <input type="button" id="addTimeAction" value="Add">
                        <!-- <div id="add_start_time" class="option_list">
                        	<ul>
                           </ul>
                        </div> -->
                       	<div class="time_added">
                        	<table class="time_added_table" width="100%" border="1" bordercolor="#dbdbdb" cellspacing="0" cellpadding="0">
                              <tr bgcolor="#8c8c8c" class="wtxt fbold">
                                <td>Added Time</td>
                                <td>&nbsp;</td>
                              </tr>
                              <!-- <tr>
                                <td>10.30</td>
                                <td><img src="${pageContext.request.contextPath}/images/delete.png" align="absmiddle">&nbsp;<a href="#">Delete</a></td>
                              </tr> -->
                            </table>
                        </div>
                    </div>
                    
                    <input type="button" value="Create Room" class="red_btn" onclick="crateRoomForm.submit();">
				</form:form>
                
                </div>
            </div>
            </div>
	</section>
