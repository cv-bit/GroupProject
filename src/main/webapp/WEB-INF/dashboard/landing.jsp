<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Landing Page</title>
<link rel="stylesheet" type = "text/css" href = "css/landing.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body id="body">
	<div class ="container">
			<iframe width="95%" height="250rem" class="video"
				src="https://www.youtube.com/embed/ogQZE4yXBcY?autoplay=1&mute=1&playlist=ogQZE4yXBcY&loop=1">
			</iframe>
			<div class="header">
				<h1>My Car</h1>
			</div>
		<div class= "row">
		<div class = "col">

			<h3>Register</h3>
			<form:form action="/register" method= "post" modelAttribute="user">
				<div class = "form-group">
					<form:label path="firstName">First Name:</form:label>
					<form:errors path ="firstName"/>
					<form:input class = "form-controls" path="firstName" type="text"/>
				</div>
				<div class = "form-group">
					<form:label path="lastName">Last Name:</form:label>
					<form:errors path ="lastName"/>
					<form:input class = "form-controls" path="lastName" type="text"/>
				</div>
				<div class = "form-group">
					<form:label path="email">e-mail:</form:label>
					<form:errors path ="email"/>
					<form:input class = "form-controls" path="email" type="email"/>
				</div>
				<div class = "form-group">
					<form:label path="password">Password:</form:label>
					<form:errors path ="password"/>
					<form:input class = "form-control" path="password" type="password"/>
				</div>
				<div class = "form-group">
					<form:label path="passwordConfirmation">Confirm Password:</form:label>
					<form:errors path ="passwordConfirmation"/>
					<form:input class = "form-control" path="passwordConfirmation" type="passwordConfirmation"/>
				</div>
				<input class="btn btn-danger" type="submit" value="Register!"/>

			</form:form>
		</div>
			<div class="col">
				<h2>Login</h2>
				<p>${loginError}</p>
				<form method="POST" action="/login">
					<div class="form-group">
						<label>Email:</label>
						<input class = "form-control" type="email" name="myemail">
					</div>
				<div class="form-group">
					<label>Password:</label>
					<input class = "form-control" type="password" name="mypassword">
				</div>
			<button class="btn btn-success">Login</button>
		</form>
	</div>
	</div>
</div>

</body>
</html>