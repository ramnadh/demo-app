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
String[] files=(String[])request.getAttribute("myFiles");
%>	
</head>
<body>
	<nav class="navbar navbar-dark bg-primary">
	  <a class="navbar-brand" href="#">Sample Java Web App
	  
	  
	  </a>
	  <img src="<%=request.getContextPath()+"/images/logo.png"%>"/>
	  	
	</nav>
	<p align="right"> <a href="home.jsp">My Profile</a> | <a href="LoginServlet?action=logout">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;</p>
	<h1>My Files</h1>
	<p>&nbsp;</p>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<%if(files!=null && files.length >0){ %>
			<table class="table">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">Name</th>
			      <th scope="col">Download</th>
			    </tr>
			  </thead>
			  <tbody>
			  <%for(int i=0; i<files.length;i++){ %>
			    <tr>
			      <th scope="row"><%=i+1%></th>
			      <td><%=files[i]%></td>
			      <td><a href="DownloadServlet?fileName=<%=files[i]%>">
			      <img alt="Download" height="40px" width="40px" src="<%=request.getContextPath()+"/images/download.png"%>">
			      </a></td>
			    </tr>
			  <%}%>  
			   
			  </tbody>
			</table>
		<%}else{%>
		
		<div class="card">
		  <div class="card-body">
		    No Files Uploaded for this User yet!
		  </div>
		</div>
		
		<%}%>
	
	</div>
</body>
</html>