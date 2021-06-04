<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./commonstyles.jsp" %>
</head>
<body>
<h1>Flyaway - Your well deserved vacation a click away</h1>
<h2>Admin Maintanance module</h2>

	<div>
 		<form action="${pageContext.request.contextPath }/password-process" method ="post"> 
			Current Password <input type="password" id="currentPassword"name="currentPassword" placeholder="Current Password" required><br>
			New Password &nbsp;&nbsp;&nbsp;<input type="password" id="newPassword"name="newPassword" placeholder="New Password" required><br>
			New Password &nbsp;&nbsp;&nbsp;<input type="password" id="newPassword1"name="newPassword1" placeholder="New Password" required><br>
			<button type="submit">Change</button> &nbsp;
			<a href="${pageContext.request.contextPath}/">Cancel</a> <br>
		</form>	
 	</div>
</body>
</html>