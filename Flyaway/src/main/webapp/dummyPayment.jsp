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
 		
 			<h4> Congratulations ${customer.firstName} ${customer.lastName}  </h4><br>
			
 			
 			Route Id <input type="text" id="routeId"name="routeId" value="${route.routeId}" STYLE="background-color:lightblue;"  readonly><br>
			From City <input type="text" id="fromCity"name="fromCity" value="${route.fromCity}" STYLE="background-color:lightblue;" readonly><br>
			To City <input type="text" id="toCity" 	name="toCity" value="${route.toCity}" STYLE="background-color:lightblue;" readonly><br>
			Airline <input type="text" id="airline" name="airline" value="${route.airline}" STYLE="background-color:lightblue;" readonly><br>
			Price <input type="text" id="airline" name="price" value="${route.price}" STYLE="background-color:lightblue;" readonly><br><br>

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