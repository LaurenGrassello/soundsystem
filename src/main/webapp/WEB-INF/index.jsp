<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=BIZ+UDMincho&family=Bangers&display=swap');
</style>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=BIZ+UDMincho&family=Bangers&display=swap" rel="stylesheet">
<title>SoundSystem</title>
</head>
<body>
	<div class="container">
	
		<h1 class="welcome mt-5">Welcome to your...</h1>
		<h1 class="logo m-2">Sound System</h1>
		
		<div class="formIdx" style="display: flex; flex-direction: row;">
			<div class="regForm">
			<h4>Register Account</h4>
				<form:form class="form-horizontal" role="form" align="center"
					action="/register" method="POST" modelAttribute="newUser">
					<div class="form-group  m-2" align="center">
						<form:label path="username">Username:</form:label>
						<form:errors path="username" class="text-danger"></form:errors>
						<form:input align="center" type='text' path="username" />
					</div>
					<div class="form-group  m-2" align="center">
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"></form:errors>
						<form:input align="center" type='text' path="email" />
					</div>
					<div class="form-group  m-2" align="center">
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"></form:errors>
						<form:password align="center" path="password" />
					</div>
					<div class="form-group  m-2" align="center">
						<form:label path="confirm">Confirm Password:</form:label>
						<form:errors path="confirm" class="text-danger"></form:errors>
						<form:password align="center" path="confirm" />
					</div>
					<button class="btn btn-outline-light m-2">Register</button>
				</form:form>
			</div>

			<div class="loginForm">
			<h4>User Sign in</h4>
				<form:form class="form-horizontal" role="form" align="center"
					action="/login" method="POST" modelAttribute="newLogin">
					<div class="form-group m-2" align="center">
						<form:label path="email">Email:</form:label>
						<form:errors path="email" class="text-danger"></form:errors>
						<form:input align="center" type='text' path="email" />
					</div>
					<div class="form-group  m-2" align="center">
						<form:label path="password">Password:</form:label>
						<form:errors path="password" class="text-danger"></form:errors>
						<form:password align="center" path="password" />
					</div>
					<button class="btn btn-outline-light m-2">Login</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>