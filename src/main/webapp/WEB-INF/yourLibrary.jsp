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
					<li class="nav-item active"><a class="navTwo" href="/home">
							Home </a></li>
					<li><a class="navTwo" href="/playlists/new">Create
							Playlist</a></li>
					<li class="nav-item active"><a class="navTwo"
						href="/songs/new"> Add Song </a></li>
				</ul>
			</div>
		</nav>

	</div>

	<div
		class="containers d-flex justify-content-center bd-highlight mb-2 mt-5">
		<div class="card">
			<h3 class="greeting">${user.username }'s Songs</h3>
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Artist</th>
							<th scope="col">Rating</th>
							<th scope="col">Like</th>
							<th scope="col">Actions</th>
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
										<td><c:choose>
												<c:when test="${ song.songLikers.contains(user)}">
													<form action="/songs/${song.id }/unlike" method="post">
														<input type="hidden" name="_method" value="put" />
														<button class="btn btn">
															<h3>&hearts;</h3>
														</button>
													</form>
												</c:when>
												<c:otherwise>
													<form action="/songs/${song.id }/like" method="post">
														<input type="hidden" name="_method" value="put" />
														<button class="btn btn">
															<h3>&#9825;</h3>
														</button>
													</form>

												</c:otherwise>
											</c:choose></td>
										<td><form action="/songs/${song.id}" method="post">
												<input type="hidden" name="_method" value="delete" /> <input
													type="submit" class="btn btn-outline-light m-2"
													value="Delete" />
											</form></td>
									</tr>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	
			<div class="card">
			<h3 class="greeting">${user.username }'s Playlists</h3>
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Title</th>
							<th scope="col">Rating</th>
							<th scope="col">Like</th>
							<th scope="col">Actions</th>
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
										<td><c:choose>
												<c:when test="${ playlist.playlistLikers.contains(user)}">
													<form action="/playlists/${playlist.id }/unlike"
														method="post">
														<input type="hidden" name="_method" value="put" />
														<button class="btn btn"><h3>&hearts;</h3></button>
													</form>
												</c:when>
												<c:otherwise>
													<form action="/playlists/${playlist.id }/like"
														method="post">
														<input type="hidden" name="_method" value="put" />
														<button class="btn btn"><h3>&#9825;</h3></button>
													</form>

												</c:otherwise>
											</c:choose></td>
										<td><form action="/playlists/${playlist.id}"
												method="post">
												<input type="hidden" name="_method" value="delete" /> <input
													type="submit" class="btn btn-outline-light m-2"
													value="Delete" />
											</form></td>
									</tr>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>