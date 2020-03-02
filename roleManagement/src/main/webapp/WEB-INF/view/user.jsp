<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">User Dashboard</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">User<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/allUser">ALL User </a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="/messages/save">Message</a></li>
					</ul></li>
				<li><a href="user">User</a></li>
				<li><a href="registration">Registration</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<h3>Welcome User</h3>
		<table>
			<form:form action="/messages/save" method="post" modelAttribute="message">
				<textarea name="content" cols="50" rows="5"></textarea>
				<br>
				<input type="submit" value="Submit" />
			</form:form>
		</table>

		<c:if test="${not empty userList}">

			<ul>
				<c:forEach var="listValue" items="${userList}">
					<table>
						<tr>
							<td><c:out value="${listValue.username}" /></td>
							<%-- <li>${listValue}</li> --%>
						</tr>
					</table>
				</c:forEach>
			</ul>

		</c:if>


	</div>

</body>
</html>
