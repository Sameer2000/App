<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

  
    	<div class="right-Container2">
        	<div class="page_heading">System Settings</div>
				<div id="login">
	               <form:form method="post" action="">
	                 <p><label>Trial Game Win Message</label><input type="password" name="password" name="oldPassword" value="" placeholder="Old Password"></p>
	                 <p><label>Payed Game Win Message</label><input type="password" name="password" name="newPassword" value="" placeholder="New Password"></p>
	                 <p><label>Lock Time</label><input type="password" name="password" name="confirmPassword" value="" placeholder="Confirm Password"></p>
	                 <p class="submit"><input type="submit" name="commit" value="Change"></p>
	               </form:form>
	            </div>
                    
        </div>
