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
<h2>Registration</h2>

	<div>
 		<form action="${pageContext.request.contextPath }/book-process" method ="post"> 
 		
 			Route Id:<input type="text" id="routeId"name="routeId" value="${route.routeId}" STYLE="background-color:lightblue;"  readonly>
			From City:<input type="text" id="fromCity"name="fromCity" value="${route.fromCity}" STYLE="background-color:lightblue;" readonly>
			To City <input type="text" id="toCity" 	name="toCity" value="${route.toCity}" STYLE="background-color:lightblue;" readonly>
			Airline <input type="text" id="airline" name="airline" value="${route.airline}" STYLE="background-color:lightblue;" readonly>
			Price <input type="text" id="airline" name="price" value="${route.price}" STYLE="background-color:lightblue;" readonly><br><br>

			<h4> To Book Your Flight - Please register below </h4>
 		
			e-mail Id:<input type="email" id="emailId"name="emailId" placeholder="example@gmail.com" required><br>
			PhoneNo:<input type="text" id="phoneNo"name="phoneNo" placeholder="Phone No" required><br>
			Password&nbsp;:<input type="password" id="password" name="password" placeholder="password" required><br><br>
			<button type="submit">Register</button> &nbsp;
			<a href="${pageContext.request.contextPath}/">Cancel</a> <br>
		</form>	
		
 	</div>
</body>
</html>