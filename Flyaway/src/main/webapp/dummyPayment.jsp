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
 		<form action="success" method ="post"> 
 		
 			<h4> Hello ${customer.firstName} ${customer.lastName}  </h4><br>
			
			<h4 style="color:Tomato;"> Your Flight Details <br>
 				From ${route.fromCity}
 			  	  To ${route.toCity}
 			  	  by ${route.airline}
 			      cost ${route.price}<br><br>
			 </h4>
 	
			<h4> Payment Details </h4>
 		
 			Full Name <input type="text" id="fullName"name="fullName" placeholder="Fulle Name" required><br>
 			Card Number<input type="text" id="cardNumber"name="cardNumber" placeholder="Card Number" required><br>
			Expiry Date <input type="text" id="expiryDate"name="expiryDate" placeholder="Expiry Date" required><br>
			CVS number <input type="text" id="cvsNo"name="cvsNo" placeholder="CVS Number" required><br>
			<button type="submit"> Pay </button> &nbsp;
			<a href="${pageContext.request.contextPath}/">Cancel</a> <br>
		</form>	
		
 	</div>
</body>
</html>