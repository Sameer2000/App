<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

   <div style="height:25px"></div>
   	<div id="bottom_container" style="margin-left: 100">
       <div class="inner_cont">
       	<div class="main_heading">KYL Response</div>
                    	<div align="center">
                    		<h2>Know Your Luck</h2>
                    	</div>  
                    	<form:form name="savekylRequest_form" method="POST" modelAttribute="kylrequestForm" action="${pageContext.request.contextPath}/happ/admin/updateKYLRequest">
                    	<input type="hidden" name="id" value="${kyl.id}"/>
                    	<div  style="margin-left:35%; margin-top: 4%">
                    		<label style="font: bold;">Type of KYL :</label>
                    		<input type="text" name="name"  style="margin-left: 2%" value="${kyl.kylType.message}" />
                    	</div>    
                    	
                    	<div style="margin-left:35%; margin-top: 3%">
                    		<label style="font: bold;">Query :</label>
                    		<textarea  name="message" cols="30" rows="3" required style="margin-left: 7.2%" readonly="readonly" >${kyl.request}</textarea> 
                    	</div>  
                    	
                    	<div style="margin-left:35%; margin-top: 2.5%">
                    		<label style="font: bold;">Suggestion :</label>
                    		<textarea name="suggestion" cols="30" rows="3" style="margin-left: 2%" maxlength="1000" >${kyl.suggestion}</textarea>Max 1000
                    	</div>
                    	
                    	<div style="margin-left:25.5%; margin-top: 2%">
                    	    <input type="button" value="Submit" class="red_btn" onclick="savekylRequest_form.submit();"/>
  	                  	</div> 
  	                  	</form:form>    
           
        </div>
       </div>
