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
 		<form> 
 			<h4 style="color:Tomato;"> Congratulations Booking confirmed  </h4><br><br>
 			
 			<h4> Hello ${customer.firstName} ${customer.lastName}  </h4><br>
			
			<h4 style="color:Tomato;"> Your Flight Details <br>
 				From ${route.fromCity}<br>
 			  	  To ${route.toCity}<br>
 			  	  by ${route.airline}v
 			      cost ${route.price}<br><br>
			 </h4>
			 
			<a href="${pageContext.request.contextPath}/">Home</a> <br>
		</form>	
		
 	</div>
</body>
</html>