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
	<nav class="navbar navbar-expand-lg ">
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
		<nav class="navbar navbar-expand-lg ">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">

					<li><a class="navTwo" href="/songs/playlists">Your Library</a>
					<li><a class="navTwo" href="/playlists/new">Create
							Playlist</a>
					<li>
					<li class="nav-item active"><a class="navTwo"
						href="/songs/new"> Add Song </a></li>

				</ul>
			</div>
		</nav>

	</div>



	<div
		class="container d-flex justify-content-center bd-highlight mb-5 mt-5 ">

		<div class="cardFav">
			<h3 class="greeting">${user.username }'s Liked Songs</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${user.songLikers}" var="song">
					
								<tr>
									<td><c:out value="${song.title}" /></td>

								</tr>
					</c:forEach>
				</tbody>
			</table>

			<h3 class="greeting">${user.username }'s Liked Playlists</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${user.playlistLikers}" var="playlist">

						<tr>
							<td><c:out value="${playlist.name}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="card">
			<h3 class="greeting">${user.username }'s Top Songs</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Title</th>
						<th scope="col">Artist</th>
						<th scope="col">Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${songs}" var="song">
						<c:choose>
							<c:when test="${song.adder.id == userId }">
								<tr>
									<td><a href="/songs/${song.id}"> <c:out
												value="${song.title}" />
									</a></td>
									<td><c:out value="${song.artist}" /></td>
									<td><c:out value="${song.rating}" /></td>
								</tr>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="card d-flex justify-content-center bd-highlight mb-5">
			<h3 class="greeting">${user.username }'s Top Playlists</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${playlists}" var="playlist">
						<c:choose>
							<c:when test="${playlist.creator.id == userId }">
								<tr>
									<td><a href="/playlists/${playlist.id}"> <c:out
												value="${playlist.name}" />
									</a></td>
									<td><c:out value="${playlist.rating}" /></td>
								</tr>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>