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
 		<form action="insert" method ="post"> 
			<input type="text" id="fromCity"name="fromCity" placeholder="From City" required>
			<input type="text" id="toCity"name="toCity" placeholder="To City" required>
			<input type="text" id="airline"name="airline" placeholder="Airline" required>
			<input type="text" id="capacity"name="capacity" placeholder="Capacity" required>
			<input type="date" id="fromDate"name="fromDate" placeholder="From Date" required>
			<input type="date" id="toDate"name="toDate" placeholder="To Date" required>
			<input type="text" id="price" name="price" placeholder="price" required><br>

			<button type="submit">Add</button> &nbsp;
			<a href="${pageContext.request.contextPath}/airlines">Airlines</a> &nbsp;
			<a href="${pageContext.request.contextPath}/places">Places</a> &nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/">Back</a> <br>
		</form>	
		
		<div>
		<form action="" method="post" > 
		<table>
		  <tr>
		  	<th>Route Id</th>
		    <th>From City</th>
		    <th>To City</th>
		    <th>Airline</th>
		    <th>Capacity</th>
		    <th>From Date </th>
		    <th>To Date </th>
		    <th>Price</th>
		    <th>Action</th>
		  </tr>
		  <c:forEach items="${routes}" var="route">
		    <tr>
		      <td>${route.routeId}</td>
		      <td>${route.fromCity}</td>
		      <td>${route.toCity}</td>
		      <td>${route.airline}</td>
		      <td>${route.capacity}</td>
		      <td>${route.fromDate}</td>
		      <td>${route.toDate}</td>
		      <td>&#36;${route.price}</td>
		      <td><a href="edit?routeId=<c:out   value='${route.routeId}'/>">Edit</a></td>    
		      <td><a href="delete?routeId=<c:out value='${route.routeId}'/>">Delete</a></td>		       
		    </tr>
		  </c:forEach>
		</table>
		</form>
		</div>
 	</div>
</body>
</html>