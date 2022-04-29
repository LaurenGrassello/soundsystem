package com.laurenodalen.lookify.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="playlists")
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message="Playlist name must be included")
	@Size(min=1, max=20, message = "Playlist name must be at least one character")
	private String name;
	
	@NotNull(message = "Rating must be included")
	@Min(value = 1, message = "Must be a positive number")
	private Integer rating;
	

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "creator_id")
   	private User creator;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "has_playlist", joinColumns = @JoinColumn(name = "song_id"),
	inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Song> songWithPlaylist;

    
    @ManyToMany(fetch = FetchType.LAZY)
 	@JoinTable(name = "playlistLikes", joinColumns = @JoinColumn(name = "playlist_id"), 
 	inverseJoinColumns = @JoinColumn(name = "user_id"))
 	private List<User> playlistLikers;
    
    
    public Playlist() {}


	public Playlist(String name,Integer rating,User creator, 
			List<Song> songWithPlaylist, List<User> playlistLikers) {
		this.name = name;
		this.rating = rating;
		this.creator = creator;
		this.songWithPlaylist = songWithPlaylist;
		this.playlistLikers = playlistLikers;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getRating() {
		return rating;
	}



	public void setRating(Integer rating) {
		this.rating = rating;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public List<Song> getSongWithPlaylist() {
		return songWithPlaylist;
	}



	public void setSongWithPlaylist(List<Song> songWithPlaylist) {
		this.songWithPlaylist = songWithPlaylist;
	}



	public List<User> getPlaylistLikers() {
		return playlistLikers;
	}



	public void setPlaylistLikers(List<User> playlistLikers) {
		this.playlistLikers = playlistLikers;
	}
	
	
    
}
