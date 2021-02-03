<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" type = "text/css" href = "css/style.css">
</head>
<body>
<div class="container">
<h2>User Details for ${user.firstName} ${user.lastName}</h2>
<hr>
<h3>Cars Owned:</h3>
<ul>
<c:forEach items="${user.cars}" var="car">
<li><a href="/cars/${car.id}" >${car.make} - ${car.model}></a></li>

</c:forEach>
</ul>
</div>
</body>
</html>