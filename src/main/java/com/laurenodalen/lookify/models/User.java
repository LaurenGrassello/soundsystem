package com.laurenodalen.lookify.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Username is required!")
	@Pattern(regexp = "[A-Z].+", message = "Username Must begin with a capital.")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	private String username;

	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email!")
	private String email;

	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;

	@Transient
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;

	

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="friend_id")
	private User friend;
	
	@OneToMany(mappedBy="friend")
	private Set<User> userFriend = new HashSet<User>();

	@OneToMany(mappedBy="adder", fetch = FetchType.LAZY)
	private List<Song> userSongs;
	
	@OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
	private List<Playlist> userPlaylists;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "songLikes", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> songLikers;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "playlistLikes", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Playlist> playlistLikers;



	public User() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Song> getUserSongs() {
		return userSongs;
	}

	public void setUserSongs(List<Song> userSongs) {
		this.userSongs = userSongs;
	}

	public List<Playlist> getUserPlaylists() {
		return userPlaylists;
	}

	public void setUserPlaylists(List<Playlist> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}

	public List<Song> getSongLikers() {
		return songLikers;
	}

	public void setSongLikers(List<Song> songLikers) {
		this.songLikers = songLikers;
	}

	public List<Playlist> getPlaylistLikers() {
		return playlistLikers;
	}

	public void setPlaylistLikers(List<Playlist> playlistLikers) {
		this.playlistLikers = playlistLikers;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public Set<User> getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(Set<User> userFriend) {
		this.userFriend = userFriend;
	}
	
}

