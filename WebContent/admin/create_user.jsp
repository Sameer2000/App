<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
       <!--Start Mid container-->
        <section class="full-size bottom-bg">
        <div style="height:25px"></div>
        	<div id="bottom_container">
            <div class="inner_cont">
            	<div class="main_heading">Create New User</div>
                <form:form class="contact_form" name="User_form" method="POST" modelAttribute="createNewUserForm" action="createUser">
                    <ul class="registration">
                      
                        <li>
                            <label for="email">Email:</label>
                            <input type="text" name="emailId"  placeholder="name@something.com" required />
                            <span class="form_hint">Proper format "name@something.com"</span>
                        </li>
                        <li>
                            <label for="password">Password:</label>
                            <input type="password" name="password" placeholder="Password" required />
                        </li>
                        <li>
                            <label for="name">Name:</label>
                            <input type="text" name="fullName" placeholder="Full Name"/>
                            <span class="form_hint">Proper format "Full Name"</span>
                        </li>
                        <li>
                            <label for="country">Country:</label>
                            <select name="country">
                            	<option>India</option>
                            	<option>Germany</option>
                            </select>
                        </li>
                        <li>
                            <label for="phone">Phone:</label>
                            <input type="text" name="phone" placeholder="9847438483"/>
                            <span class="form_hint">Proper format "9847438483"</span>
                        </li>
                        <li>
                            <label for="usertype">Usertype:</label>
                            <select name="usertype">
                            	<option value="Admin">Admin</option>
                            	<option value="Dummy">Dummy</option>
                            </select>
                        </li>
                        <li>
                            <button class="red_btn" type="submit">Submit</button>
                        </li>
                        <li>
                            
                        </li>
                        <li>
                            <a href="#" class="terms">Read Terms & conditions</a>
                        </li>
                    </ul>
                </form:form>
            </div>
            </div>
	</section>
