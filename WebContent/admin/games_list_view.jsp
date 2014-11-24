<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

  <script>
  	function submitSearchForm(){
  		document.searchGameForm.action = "${pageContext.request.contextPath}/happ/admin/games";
  		document.searchGameForm.submit();
  	}
  </script>
   <div style="height:25px"></div>
   	<div id="bottom_container">
       <div class="inner_cont">
       	<div class="main_heading">Game</div>
           <!-- <div class="create_room"><a href="#">Create New Room</a></div> -->
          <div class="">
           	<div class="show_entry">Show 
               	<select>
                   	<option>10</option>
                   </select>
                   entries&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
               </div>
               
              
               <form name="searchGameForm" action="">
               
               <div align="right">
               <input type="text" placeholder="Search..." name="searchGame" id="search"/>
               <input type="button" value="Search" onclick="submitSearchForm();" />
               </div>
               </form> 
               
               <jsp:include page="games_list_fragment_page.jsp" />
               
               
           </div>
           <!--end table-->
         </div>
       </div>
