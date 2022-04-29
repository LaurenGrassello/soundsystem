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
		<nav class="navbar navbar-expand-lg ">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="navTwo" href="/home">
							Home </a></li>
					<li><a class="navTwo" href="/playlists/new">Create
							Playlist</a></li>
					<li class="nav-item active"><a class="navTwo"
						href="/songs/playlists"> Your Library </a></li>
				</ul>
			</div>
		</nav>
	</div>

	<div class="container row align-items-center mt-5">
	<div class="card">
		<h3 class="greeting">
			<c:out value="${playlist.name}" />
			's Songs
		</h3>
		<h5 class="greeting">
			Total Songs:
			<c:out value="${playlist.songWithPlaylist.size()}" />
		</h5>
		<ul>
			<c:forEach items="${playlist.songWithPlaylist}" var="song">
				<li>${song.title} by ${song.artist }</li>
			</c:forEach>
		</ul>

		<c:choose>
			<c:when test="${ playlist.playlistLikers.contains(user)}">
				<form action="/playlists/${playlist.id }/unlike" method="post">
					<input type="hidden" name="_method" value="put" />
					<button class="btn btn"><h3>&hearts;</h3></button>
				</form>
			</c:when>
			<c:otherwise>
				<form action="/playlists/${playlist.id }/like" method="post">
					<input type="hidden" name="_method" value="put" />
					<button class="btn btn"><h3>&#9825;</h3></button>
				</form>

			</c:otherwise>
		</c:choose>



		<form action="/playlists/${playlist.id}" method="post">
			<input type="hidden" name="_method" value="delete" /> <input
				type="submit" class="btn btn-outline-light m-2"
				value="Delete Playlist" />
		</form>

	</div>




	
		<div class="card m-5">
		<h3 class="greeting">
			Search artists to add to
			<c:out value="${playlist.name}" />
		</h3>
		<form action="/playlists/${playlist.id}/search/songs" method="get"
			class="d-flex align-items-center ">
			<div>
				<input type="text" class="form-control" name="artist">
			</div>
			<button type="submit" class="btn btn-dark">Search</button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>Artist</th>
					<th>Song Title</th>
					<th>Rating</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${songs}" var="song">

					<tr>
						<td><c:out value="${song.artist}" /></td>
						<td><c:out value="${song.title}" /></td>
						<td><c:out value="${song.rating}" /></td>

						<td><c:choose>
								<c:when test="${ playlist.songWithPlaylist.contains(song)}">
									<form action="/playlists/${playlist.id }/remove" method="post">
										<input type="hidden" name="songId" value="${song.id }" /> <input
											type="hidden" name="_method" value="put" />
										<button class="btn btn-outline-light m-2">Remove</button>
									</form>
								</c:when>
								<c:otherwise>
									<form action="/playlists/${playlist.id }/add" method="post">
										<input type="hidden" name="songId" value="${song.id }" /> <input
											type="hidden" name="_method" value="put" />
										<button class="btn btn-outline-light m-2">Add</button>
									</form>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</body>
</html>