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
@import
	url('https://fonts.googleapis.com/css2?family=BIZ+UDMincho&family=Bangers&display=swap')
	;
</style>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=BIZ+UDMincho&family=Bangers&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>SoundSystem</title>
</head>
<nav class="navbar navbar-expand-lg">
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<div class="navGreetDiv">
			<p class="topNavGreeting">Welcome, ${user.username }</p>
			<ul class="navbar-nav mr-auto">
				<li class="logoHome">Sound System</li>
			</ul>
		</div>
	</div>
	<div class="navDivHome">

		<p class="nav-item active">
			<a class="navThree" href="/logout">Logout</a>
		</p>
		<form action="/songs/search" method="get"
			class="d-flex align-items-center">
			<div>
				<input type="text" class="form-control" name="artist">
			</div>
			<button type="submit" class="btn btn-dark m-2">Search</button>
		</form>
	</div>
</nav>
<div class="navDivHomeTwo">
	<nav class="navbar navbar-expand-lg">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="navTwo" href="/home">
						Home </a></li>
				<li><a class="navTwo" href="/songs/playlists">Your Library</a></li>
				<li class="nav-item active"><a class="navTwo" href="/songs/new">
						Add Song </a></li>
			</ul>
		</div>
	</nav>
</div>
	<h2 class="greeting mt-5">Create Playlist</h2>

		<div class="createPL">
			<form:form action="/playlists/new" method="post"
				modelAttribute="playlist" class="pt-3">
				<form:hidden path="creator" value="${userId }" />
				<p>
					<form:label path="name">Title</form:label>
					<form:errors path="name" />
					<form:input path="name" class="form-control" />
				</p>
				<p>
					<form:label path="rating">Rating</form:label>
					<form:errors path="rating" />
					<form:select path="rating" class="form-control">
						<option>10</option>
						<option>9</option>
						<option>8</option>
						<option>7</option>
						<option>6</option>
						<option>5</option>
						<option>4</option>
						<option>3</option>
						<option>2</option>
						<option>1</option>
					</form:select>
				<button class="btn btn-outline-light mt-2">Create</button>
				</p>

			</form:form>

		</div>

	</body>
</html>