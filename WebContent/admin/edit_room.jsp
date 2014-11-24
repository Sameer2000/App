<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

$(document).ready(function(){
	
	$('body').on('click', '.delStartTime', function () {
    	$(this).parents("tr").find(':checkbox').removeAttr('checked');
    });

	$("#addTimeAction").click(function(){
		  /* $("#add_start_time ul").append("<li><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00</li>"); */
		  $(".time_added_table").append("<tr><td><input type='checkbox' checked='checked' id='startTime' name='startTime' value='"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"'/>&nbsp;"+$("#HH").val()+":"+$("#MM").val()+":00 "+$("#Locale").val()+"</td><td><img src=\"${pageContext.request.contextPath}/images/delete.png\" align=\"absmiddle\">&nbsp;<a class=\"delStartTime\">Delete</a></td></tr>");
	  });
	});

</script>
       <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Edit Room</div>
                <div id="create_room_form_box">
			   	<form:form name="crateRoomForm" method="POST" modelAttribute="createRoomForm" action="${pageContext.request.contextPath}/happ/admin/updateRoom/${room.id}">
			   		<form:errors path="*" cssClass="errorblock" element="div" />
                	<div class="sepbox">
                        <form:label path="name">Room Name : </form:label>
                        <form:input path="name" value="${room.name}" cssClass="text" />
                        <form:errors path="name" cssClass="error"></form:errors>
                    </div>
                    <div class="sepbox">
                        <form:label path="price">Price : </form:label>
                        <form:input path="price" value="${room.price}" cssClass="text" />
                        <form:errors path="price" cssClass="error"></form:errors>
                    </div>
                    <div class="sepbox">
                        <form:label path="winningChargeInPercentage">Room Charges % : </form:label>
                        <form:input path="winningChargeInPercentage" value="${room.winningChargeInPercentage}" cssClass="text" />
                        <form:errors path="winningChargeInPercentage" cssClass="error"></form:errors>
                    </div>
                    
                    <div class="sepbox">
                        <form:label path="active">Active : </form:label>
		                <c:choose>
		                    <c:when test="${room.active}">
		                        <input type="checkbox" name="active"  checked="checked">
		                    </c:when>
		                    <c:otherwise>
		                        <input type="checkbox" name="active" >
		                    </c:otherwise>
		                </c:choose>
                        <form:errors path="active" cssClass="error"></form:errors>
                    </div>
                    <div class="sepbox">
                        <form:label path="category">Category : </form:label>
                        <form:select path="category">
	                        <c:forEach items="${roomCategory}" var="category">
				                <c:set var="isSelected" value="false" />
			                    <c:if test="${room.category.code==category.key}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
				                <c:choose>
				                    <c:when test="${isSelected}">
				                        <option value="${category.key}" selected="selected">${category.value}</option>
				                    </c:when>
				                    <c:otherwise>
				                        <option value="${category.key}">${category.value}</option>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
                        </form:select>
                    </div>
                     <div class="sepbox">
                        <form:label path="maxUserCount">Max Users : </form:label>
                        <form:select path="maxUserCount">
	                        <c:forEach items="${allowedUserCount}" var="usrCnt">
				                <c:set var="isSelected" value="false" />
			                    <c:if test="${room.allowUserCount==usrCnt.key}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
				                <c:choose>
				                    <c:when test="${isSelected}">
				                        <option value="${usrCnt.key}" selected="selected">${usrCnt.value}</option>
				                    </c:when>
				                    <c:otherwise>
				                        <option value="${usrCnt.key}">${usrCnt.value}</option>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
                        </form:select> 
                    </div>
                    <div class="sepbox">
                        <form:label path="allowCountryCode">Country Code : </form:label>
                        <form:select path="allowCountryCode">
                        		<form:options items="${allowedCountryCode}"  />
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
	                        <input type="radio" name="scheduleType" value="0" checked="checked">&nbsp;<b>Daily</b>
                        	<input type="radio" name="scheduleType" value="1">&nbsp;<b>Weekly</b>
	                         <div class="option_list">
	                        	<ul>
			                        <c:forEach items="${weekDays}" var="weekday">
						                <c:set var="isSelected" value="false" />
					                    <c:forEach items="${room.scheduleOn}" var="schon">
						                    <c:if test="${fn:toLowerCase(weekday.key)==fn:toLowerCase(schon)}">
						                        <c:set var="isSelected" value="true" />
						                    </c:if>
						                </c:forEach>	
						                <c:choose>
						                    <c:when test="${isSelected}">
						                        <li><input type="checkbox" name="scheduleOn" value="${weekday.key}" checked="checked">${weekday.value}</li>
						                    </c:when>
						                    <c:otherwise>
						                        <li><input type="checkbox" name="scheduleOn" value="${weekday.key}">${weekday.value}</li>
						                    </c:otherwise>
						                </c:choose>
						            </c:forEach>
	                            </ul>
	                        </div>
                        </div>
                        <div class="clearfix"></div> 
                    </div>
                    <div class="sepbox">
                        <label>Start Time :</label>
                        <input type="text" id="HH" placeholder="HH" style="width:40px; margin-left:15px">
                        <input type="text" id="MM" placeholder="MM" style="width:40px; margin-left:5px">
                        <select name="Locale" id="Locale">
                        	<option value="GMT">GMT</option>
                        	<option value="IST" selected="selected">IST</option>
                        </select>
                        <input type="button" id="addTimeAction" value="Add">
                       	<div class="time_added">
                        	<table class="time_added_table" width="100%" border="1" bordercolor="#dbdbdb" cellspacing="0" cellpadding="0">
                              <tr bgcolor="#8c8c8c" class="wtxt fbold">
                                <td>Added Time</td>
                                <td>&nbsp;</td>
                              </tr>
                              <c:forEach items="${room.startTime}" var="starttime">
                                <tr>
                                <td><input type="checkbox" checked="checked" id="startTime" name="startTime" value="${starttime}"/>&nbsp;${starttime}</td>
                                <td><img src="${pageContext.request.contextPath}/images/delete.png" align="absmiddle">&nbsp;<a class="delStartTime">Delete</a></td>
                                </tr>
					          </c:forEach>
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
