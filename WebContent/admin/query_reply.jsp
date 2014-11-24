<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<<script type="text/javascript">
function save_queryRequest_form(){
	alert("hi");
}
</script>
   <div style="height:25px"></div>   
   	<div id="bottom_container">
       <div class="inner_cont">
       	<div class="main_heading">Query Response</div>
           <!-- <div class="create_room"><a href="#">Create New Room</a></div> -->
           <form:form name="save_queryRequest_form" method="POST" modelAttribute="queryrequestForm" action="${pageContext.request.contextPath}/happ/admin/updateQueryRequest">
                    	<input type="hidden" name="id" value="${contact.id}"/>
                    	<div  style="margin-left:35%; margin-top: 4%">
                    		<label style="font: bold;">Name :</label>
                    		<input type="text" name="name"  style="margin-left: 7%" value="${contact.name}" />
                    	</div>    
                    	
                    	<div  style="margin-left:35%; margin-top: 4%">
                    		<label style="font: bold;">Email :</label>
                    		<input type="text" name="email"  style="margin-left: 7%" value="${contact.email}" />
                    	</div>
                    	
                    	<div  style="margin-left:35%; margin-top: 4%">
                    		<label style="font: bold;">phone :</label>
                    		<input type="text" name="phone"  style="margin-left: 7%" value="${contact.phone}" />
                    	</div>
                    	
                    	<div style="margin-left:35%; margin-top: 3%">
                    		<label style="font: bold;">Query :</label>
                    		<textarea  name="message" cols="30" rows="3" readonly="readonly" style="margin-left: 7.2%" >${contact.message}</textarea>
                    	</div>  
                    	
                    	<div style="margin-left:35%; margin-top: 2.5%">
                    		<label style="font: bold;">Response :</label>
                    		<textarea name="reply" cols="30" rows="3" style="margin-left: 2%;" maxlength="1000"   >${contact.reply}</textarea>max 1000
                    	</div>
                    	
                    	<div style="margin-left:25.5%; margin-top: 2%">
                    	    <input type="button" value="Submit" class="red_btn" onclick="save_queryRequest_form.submit();"/>
  	                  	</div> 
  	             </form:form>
  
       </div>
       </div>
