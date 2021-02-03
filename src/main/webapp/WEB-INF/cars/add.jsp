<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Car</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<nav>
		Return to --><a href="/">Login Page</a> |	<a href="/cars">Welcome Page</a>
	</nav>
<div class="container">
<h1>Add New Car</h1>

<c:forEach items="${errors}" var="err">
<p>${err}</p>
</c:forEach>

<hr>
<form:form method="POST" action="/cars/add" modelAttribute="car">
<div class="form-group">
	<form:label path="make">Make:
	<form:errors path="make"/>
	<form:input path="make"/></form:label>
</div>
<div class="form-group">
	<form:label path="model">Model:
	<form:errors path="model"/>
	<form:input path="model"/></form:label>
</div>
<div class="form-group">
	<form:label path="color">Color:
	<form:errors path="color"/>
	<form:input path="color"/></form:label>
</div>
<div class="form-group">
	<form:label path="year">Year:
	<form:errors path="year"/>
	<form:input path="year"/></form:label>
</div>
<div class="form-group">
	<form:label path="transmission">Transmission:
	<form:errors path="transmission"/>
	<form:input path="transmission"/></form:label>
</div>
<button class="btn btn-dark">Create New Car</button>

</form:form>
<hr>

</div>
</body>
</html>
