<%@page import="com.prolifics.util.ApplicationProperties"%>
<%@page import="com.prolifics.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User - Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<%
UserDTO user = (UserDTO)session.getAttribute("user");
String error=(String)request.getParameter("error");
error = (error==null) ? "": error;
String success=(String)request.getParameter("success");
success = (success==null) ? "": success;
%>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary">
	  <a class="navbar-brand" href="#">Sample Java Web App</a>
	<img src="<%=request.getContextPath()+"/images/logo.png"%>"/>  
	</nav>
	
	<p align="right"> <a href="DownloadServlet">My Files</a> | <a href="LoginServlet?action=logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;</p>
	<h1>My Profile</h1>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<ul class="list-group">
		  <%if(ApplicationProperties.getProperty("show.firstName").equals("Y")){ %>	
		  	<li class="list-group-item"><b>First Name</b><span style="float:right"><%=user.getFirstName() %></span></li>
		  <%} %>
		  
		  <%if(ApplicationProperties.getProperty("show.firstName").equals("Y")){ %>	
		  	<li class="list-group-item"><b>Last Name</b><span style="float:right"><%=user.getLastName() %></span></li>
		  <%} %>
		  
		  <%if(ApplicationProperties.getProperty("show.gender").equals("Y")){ %> 	
		  	<li class="list-group-item"><b>Gender</b><span style="float:right"><%=user.getGender() %></span></li>
		  <%} %>
		  
		  <%if(ApplicationProperties.getProperty("show.phone").equals("Y")){ %>
		  	<li class="list-group-item"><b>Phone</b><span style="float:right"><%=user.getPhone() %></span></li>
		  <%} %>
		  
		  <%if(ApplicationProperties.getProperty("show.status").equals("Y")){ %>
		  	<li class="list-group-item"><b>Status</b><span style="float:right"><%=user.getStatus() %></span></li>
		  <%} %>	
		</ul>
		
		<hr/>
		<h2>Upload File</h2>
		<p id="success" style="color:GREEN"><%=success%></p>
		<p id="error" style="color:RED"><%=error%></p>
		<form method="post" action="UploadServlet" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="uploadAFile">Upload a File</label>
		    <input type="file" class="form-control-file" name="fl">
		    
		  </div>
		  <p>&nbsp;</p>
		  <button type="submit" class="btn btn-primary" onclick="document.getElementById('success').innerHTML ='';document.getElementById('error').innerHTML ='';">Submit</button>
		  
		  
		</form>
	</div>
</body>
</html>