<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<%
String error=(String)request.getAttribute("error");
error = (error==null) ? "": error;
%>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary">
	  <a class="navbar-brand" href="#">Sample Java Web App</a>
	  <img src="<%=request.getContextPath()+"/images/logo.png"%>"/>
	</nav>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>Login</h1>
		<p style="color:RED" id="err"><%=error%></p>
		<form action="LoginServlet" method="post">
		
			
			<div class="form-group">
				<label for="emailId">Email ID(02/09 04:24):</label> <input type="email"
					class="form-control" id="emailId" maxlength="50" placeholder="Enter Email Id"
					name="username" required>
			</div>

			<button type="submit" class="btn btn-primary" onclick="document.getElementById('err').innerHTML ='';">Submit</button>
		</form>
	</div>
</body>
</html>
