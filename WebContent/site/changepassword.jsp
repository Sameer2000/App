<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

 							 <script language="javascript"> 
								
								function alert1()
								  {
								   
								  if(document.getElementById("confirmPassword1").value==document.getElementById("newPassword1").value){
									 
								  }
								  
								  else  alert("New password and confirm password not same");
								  }
								</script>
							
							
                	<div class="right-Container2">
                    	<div class="page_heading">Change Password</div>
                        	<div id="login">
                        	
                                 <form:form class="contact_form" action="cpwd" method="POST" modelAttribute="cpwdAttri"  name="cpwd_form" style="margin-left:0">
                               	
                                  <p><label>Old Password</label><input type="password"  name="oldPassword"   placeholder="Old Password" id="oldPassword" required></p>
                                  <p><label>New Password</label><input type="password"  name="newPassword"   placeholder="New Password" id="newPassword1" required></p>
                                  <p><label>Confirm Password</label><input type="password" name="confirmPassword" value="" placeholder="Confirm Password" id="confirmPassword1" required></p>
                                  <p class="submit"><input type="submit" name="commit" value="Change" onclick="alert1()"/></p>
                                </form:form>
                              </div>
                            
                    </div>

