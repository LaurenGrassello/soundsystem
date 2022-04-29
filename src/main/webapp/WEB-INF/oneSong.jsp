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
				<li class="nav-item active"><a class="navTwo"
						href="/home"> Home </a></li>
					<li><a class="navTwo" href="/playlists/new">Create
							Playlist</a></li>
					<li class="nav-item active"><a class="navTwo"
						href="/songs/playlists"> Your Library </a></li>
				</ul>
			</div>
		</nav>
		</div>
		
		
	<div class="containerOneSong">
		<table class="table ">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Artist</th>
					<th scope="col">Rating</th>
					<th scope="col">Like</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${song.title}" /></td>
					<td><c:out value="${song.artist}" /></td>
					<td><c:out value="${song.rating}" /></td>
								<td>
						<c:choose>
								<c:when test="${ song.songLikers.contains(user)}">
									<form action="/songs/${song.id }/unlike" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn"><h3>&hearts;</h3></button>
									</form>
								</c:when>
								<c:otherwise>
									<form action="/songs/${song.id }/like" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn" ><h3>&#9825;</h3></button>
									</form>

								</c:otherwise>
							</c:choose></td>
					<td>
						<form action="/songs/${song.id}" method="post">
							<input type="hidden" name="_method" value="delete" /> <input
								type="submit" class="btn btn-outline-light m-2" value="Delete" />
						</form>
					</td>
				</tr>

			</tbody>
		</table>
	</div>
</body>
</html>