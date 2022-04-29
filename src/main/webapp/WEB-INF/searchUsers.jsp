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
<body>

	<nav class="navbar navbar-expand-lg navbar-light">
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
			<form action="/users/search/results" method="get"
				class="d-flex align-items-center">
				<div>
					<input type="text" class="form-control" name="username">
				</div>
				<button type="submit" class="btn btn-dark m-2">Search</button>
			</form>
		</div>
	</nav>
	<div class="navDivHomeTwo">
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="navTwo" href="/home">
							Home </a></li>
					<li><a class="navTwo" href="/playlists/new">Create
							Playlist</a></li>
					<li class="nav-item active"><a class="navTwo"
						href="/songs/new"> Add Song </a></li>
				</ul>
			</div>
		</nav>
		<div class="container">
			<h3 class="greeting">Search Friends</h3>
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Add Friend</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${friends}" var="friend">
						<tr>
							<td><c:out value="${friend.username}" /></td>


							<td><c:choose>
									<c:when test="${ user.userFriend.contains(friend)}">
										<form action="/users/${friend.id }/unfriend" method="post">
											<input type="hidden" name="userId" value="${friend.id }" />
											<input type="hidden" name="_method" value="put" />
											<button class="btn btn">Unfriend</button>
										</form>
									</c:when>
									<c:otherwise>
										<form action="/users/${friend.id }/addfriend" method="post">
											<input type="hidden" name="userId" value="${friend.id }" />

											<input type="hidden" name="_method" value="put" />
											<button class="btn btn">Add Friend</button>
										</form>

									</c:otherwise>
								</c:choose></td>


						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
</body>
</html>