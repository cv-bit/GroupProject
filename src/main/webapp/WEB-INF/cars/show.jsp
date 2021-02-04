<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Details</title>
<link rel="stylesheet" type = "text/css" href = "/css/show.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body id="body">
	<nav>
		Return to --><a href="/">Login Page</a> |	<a href="/cars">Welcome Page</a>
	</nav>

	<div class="container">
		<h1>Details for ${car.model}</h1>
		<h2>Owner: <a href="/cars/user/${car.owner.id}">${car.owner.firstName} ${car.owner.lastName}<a/></h2>
		<h3>Number of Likes: ${car.likers.size()}</h3>
		<h3>Liked By:</h3>
		<ol>
		<c:forEach items="${car.likers}" var="user">
			<li>${user.firstName} ${user.lastName}</li>
		</c:forEach>
		</ol>
		<hr>
		<c:set var="found" value="false"/>
			<c:forEach items="${car.ratings }" var="rating">
				<c:if test="${rating.user.id == userId }">
					<c:set var="found" value="true"/>
					<h3>You Rated this car <c:out value="${rating.rating }"/></h3>
					<hr>
				</c:if>
			</c:forEach>
		<c:if test="${found == false }">
			<h3>Add A Rating</h3>
			<form method ="POST" action="/cars/rate/${car.id }" class="row align-center">
				<div class="form-group">
					<select name="rating">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<input type="submit" value="Rate!" class="btn btn-danger"/>
				</div>
			</form>
		</c:if>
		<h6>Users Who Rated The Car:</h6>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th><th>Rating</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach items = "${car.ratings }" var="rating">
				<tr>
					<td><c:out value="${rating.user.firstName }"/> <c:out value="${rating.user.lastName }"/></td>
					<td><c:out value="${rating.rating }"/></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<p>Make: ${car.make}</p>
		<p>Year: ${car.year}</p>
		<p>Color: ${car.color}</p>
		<p>Transmission: ${car.transmission}</p>
		<hr>
		<h2>After Market Accessories</h2>
		<ol>
			<c:forEach items="${car.accessories}" var="acc">
				<li>Name: ${acc.name}</li>
				<ul>
					<li>Description: ${acc.description}</li>
					<li>Price: ${acc.price }</li>
				</ul>
			</c:forEach>
		</ol>
		<hr>
		<h2>Register This Car!</h2>
		<c:choose>
		<c:when test="${car.title != null}">
			<p>Vin Number: ${car.title.vin}</p>
			<p>Registered City: ${car.title.city}</p>
			<p>Registered State: ${car.title.state}</p>
		</c:when>
		<c:otherwise>
		<form:form method="POST" action="/cars/addTitle" modelAttribute="title">
			<div class="form-group">
				<form:label path="vin">Vin Number
				<form:errors path="vin"/>
				<form:input path="vin"/></form:label>
			</div>
			<div class="form-group">
				<form:label path="city">City
				<form:errors path="city"/>
				<form:input path="city"/></form:label>
			</div>
			<div class="form-group"> State
				<form:label path="state">
				<form:errors path="state"/>
				<form:input path="state"/></form:label>
			</div>
		<form:input type="hidden" value="${car.id}" path="car"/>
			<button class="btn btn-danger">Register This Vehicle!</button>
		</form:form>
	</c:otherwise>
	</c:choose>
	<hr>
	<c:if test="${car.owner.id == userId }">
		<h3>Edit Car</h3>
		<form:form method="POST" action="/cars/edit/${car.id}" modelAttribute="car">
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
			<button class="btn btn-danger">Save Changes</button>
		</form:form>	
			<a href="/cars/delete/${car.id}" class="btn btn-danger">Delete This Car</a>
	</div>
	</c:if>
</body>
</html>
