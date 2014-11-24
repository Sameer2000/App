<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

  <script>
  	function submitSearchForm(){
  		document.searchUserForm.action = "/HWebapp/happ/site/kyl_page";
  		document.searchUserForm.submit();
  	}
  </script>
<script type="text/javascript">
 
// Form validation code will come here.
function validate()
{
 
	 var cmt = confirm("Every KYL Request will charge you $${KYLFee} USD. Please Confirm?");
	 if (cmt == true) {
         return true;
     }
     else {
         return false;
     }
}
 
</script>
                	<div class="right-Container2">
                    	<div class="page_heading">Know Your Luck</div>
                    	 <h3>&nbsp;&nbsp;Every KYL Request will charge you $${KYLFee} USD.</h3> 
                        <h2 class="red_text">New Request</h2>
                        	<form:form class="contact_form" onsubmit="return validate();" action="savekyl" method="POST" modelAttribute="kylForm"  name="kyl_form" style="margin-left:0">
                    <ul>
                      
                        <li>
                            	<label>Know Your Luck:</label>
                                <select class="kyl">
                                    <option selected value="0"> KYL Request</option>
                                    <option value="1">Other</option>
                                </select>
                        </li>
                                               
                        <li>
                            <label for="message">Message:</label>
                            <textarea style="color: #859191;"  name="message" cols="40" rows="6"  maxlength="1000" required>Please Know my lucky number and Time to Play in this Week.</textarea>
                       		Max 1000
                        </li>
                        <li>
                            <button class="red_btn" type="submit" >Submit</button>
                        </li>
                    </ul>
                </form:form>
                <form name="searchUserForm" action="kyl_page">               
               			<div align="right" style="margin-right: 2% ">
               				<input type="text" placeholder="Search..." name="search1" id="search"/>
              				<input type="button" value="Search" onclick="submitSearchForm();" />
               			</div>
                </form>
                 <!--Know Your Luck Request-->
                        	<div class="kyl_box" >
                               <div class="brown_heading">Know Your Luck Request</div>
                               <table width="100%" border="1" bordercolor="#d9d9d9" cellspacing="0" cellpadding="0">
                                  <tr bgcolor="#e5e5e5">
                                    <th width="18%" scope="col">Request Date/Time</th>
                                    <th width="25%" scope="col">Query</th>
                                    <th width="17%" scope="col">Type Of KYL/Other</th>
                                    <th width="30%" scope="col">Suggestion</th>
                                    <th width="10%" scope="col">Status</th>
                                  </tr>
                                  <c:forEach items="${kyllist}" var="kyl" varStatus="status">
										<tr>
											<td >
								           	<c:set var="now1" value="${kyl.reqStrDateTime} ${kyl.timeZone}" />
					                       	<fmt:parseDate value="${now1}" var="parsedEmpDate" 
					                   							pattern="yyyy-MM-dd HH:mm:ss z" />
					                   		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"  
 											value="${parsedEmpDate}" />
											</td>
											<td>${kyl.request}</td>
											<td>${kyl.kylType.message}</td>
											<td>${kyl.suggestion}</td>
											<td>${kyl.kylStatus.message}</td>
						 				</tr>
									</c:forEach>
                                </table>
							<table align="right">
								<tr>
					
									<c:forEach begin="1" end="${pageCount}" varStatus="loop">
										<td style="background-color: #DFD9C3;"><a
											href="${pageContext.request.contextPath}/happ/site/kyl_page?pageNumber=${loop.count}"> <c:out
													value="${loop.count}" />
									</c:forEach>
									</a>
									</td>
					
								</tr>
							</table>
					
							<div class="clearfix"></div>
                            </div>
                        <!--end Know Your Luck Request-->
                        <br>
                    </div>
                