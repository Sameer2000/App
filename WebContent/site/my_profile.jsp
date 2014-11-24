<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script>

	function tabSwitch(new_tab, new_content) {  
	      
	    document.getElementById('content_1').style.display = 'none';  
	    document.getElementById('content_2').style.display = 'none';  
	    document.getElementById('content_3').style.display = 'none';          
	    document.getElementById(new_content).style.display = 'block';     
	      
	  
	    document.getElementById('tab_1').className = '';  
	    document.getElementById('tab_2').className = '';  
	    document.getElementById('tab_3').className = '';          
	    document.getElementById(new_tab).className = 'active';        
	  
	} 
	
	function tabSwitch_2(active, number, tab_prefix, content_prefix) {  
	      
	    for (var i=1; i < number+1; i++) {  
	      document.getElementById(content_prefix+i).style.display = 'none';  
	      document.getElementById(tab_prefix+i).className = '';  
	    }  
	    document.getElementById(content_prefix+active).style.display = 'block';  
	    document.getElementById(tab_prefix+active).className = 'active';      
	      
	}  
	
</script>

<div class="right-Container2">
				<form:form method="POST" action="my_profile_page" modelAttribute="userProfileForm">
                    	<div class="page_heading">User Profile</div>
                       
                            
                       <!--Detail View-->
                        <h2 class="red_text">Detail View:</h2>
                        <div class="table_box">
                        	<div class="brown_heading">Personal Details</div>
                            <div id="tabbed_box_1" class="tabbed_box">   
                                <div class="tabbed_area">  
                                  
                                    <ul class="tabs">  
                                        <li><a href="javascript:tabSwitch('tab_1', 'content_1');" id="tab_1" class="active">User Detail</a></li>  
                                        <li><a href="javascript:tabSwitch('tab_2', 'content_2');" id="tab_2">Contact Detail</a></li>  
                                        <li><a href="javascript:tabSwitch('tab_3', 'content_3');" id="tab_3">Know Your Luck Detail</a></li>  
                                    </ul>   
                                      
                                    <div id="content_1" class="content">  
                                        <div class="div_box">
                                        	<div class="ud_title">User Name :</div>
                                            <div class="ud_value">${usrprf.username}
<!--                                             	<a href="#">Save</a>
                                                <a href="#">Cancel</a>	
 -->                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Name :</div>
                                            <div class="ud_value">
                                               <input name="name" value="${usrprf.name}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Phone :</div>
                                            <div class="ud_value">
                                            	<input name="phone" value="${usrprf.phone}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Email :</div>
                                            <div class="ud_value">
                                            	<input name="email" value="${usrprf.email}" />
                                                
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Age :</div>
                                            <div class="ud_value">
                                            	<input name="age" value="${usrprf.age}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Sex :</div>
                                            <div class="ud_value">
                                            	<input name="gender" value="${usrprf.gender}" />
                                                
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Marital Status :</div>
                                            <div class="ud_value">
                                            	<input name="maritalStatus" value="${usrprf.maritalStatus}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Work :</div>
                                            <div class="ud_value">
                                            	<input name="occupation" value="${usrprf.occupation}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <input type="submit" value="Submit" class="red_btn">
                                    </div>  
                                    <div id="content_2" class="content"> 
                                    	<div class="div_box">
                                        	<div class="ud_title">Address :</div>
                                            <div class="ud_value">
                                            	<textarea rows="6" cols="50" name="address1">${usrprf.address1}</textarea>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">State :</div>
                                            <div class="ud_value">
                                            	<input name="state" value="${usrprf.state}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">Country :</div>
                                            <div class="ud_value">
                                            	<input name="country" value="${usrprf.country}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="div_box">
                                        	<div class="ud_title">ZipCode :</div>
                                            <div class="ud_value">
                                            	<input name="zipcode" value="${usrprf.zipcode}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <input type="submit" value="Submit" class="red_btn"> 
                                    </div>  
                                    <div id="content_3" class="content">  
                                        <div class="div_box">
                                        	<div class="ud_title">Date of Birth :</div>
                                            <div class="ud_value">
                                            	<input name="dob" value="${usrprf.dob}" />
                                                <!-- <a href="#">Change</a> -->
                                            </div>
                                            <div class="clearfix"></div>
                                        </div> 
                                        <div class="div_box">
                                        	<div class="ud_title">DOB Time :</div>
                                            <div class="ud_value">
                                            	<input name="dobtime" value="${usrprf.dobtime}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div> 
                                        <div class="div_box">
                                        	<div class="ud_title">DOB Place :</div>
                                            <div class="ud_value">
                                            	<input name="dobplace" value="${usrprf.dobplace}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div> 
                                        <div class="div_box">
                                        	<div class="ud_title">DOB Country :</div>
                                            <div class="ud_value">
                                            	<input name="dobcountry" value="${usrprf.dobcountry}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div> 
                                        <div class="div_box">
                                        	<div class="ud_title">latitude :</div>
                                            <div class="ud_value">
                                            	<input name="latitude" value="${usrprf.latitude}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div> 
                                        <div class="div_box">
                                        	<div class="ud_title">longitude :</div>
                                            <div class="ud_value">
                                            	<input name="longitude" value="${usrprf.longitude}" />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <input type="submit" value="Submit" class="red_btn">
                                    </div>  
                                  
                                </div>  
                              
                            </div>  
                        </div>
                        <!--end Detail View-->
                        
					</form:form>
                    </div>
               