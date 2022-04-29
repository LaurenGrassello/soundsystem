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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="songs")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Artist must be included")
	@Size(min=2, max=20, message = "Name must be at least two character")
	private String artist;
	
	@NotNull(message = "Title must be included")
	@Size(min = 1, max = 20, message = "Must be at least one character")
	private String title;
	
	@NotNull(message = "Rating must be included")
	@Min(value = 1, message = "Must be a positive number")
	private Integer rating;
	
  
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adder_id")
	private User adder;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "has_playlist", joinColumns = @JoinColumn(name = "playlist_id"),
	inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Playlist> songWithPlaylist;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "songLikes", joinColumns = @JoinColumn(name = "song_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> songLikers;

       
    
    public Song() {
	}
    

	public Song(String artist, String title, Integer rating,
			User adder, List<Playlist> songWithPlaylist, List<User> songLikers) {
		this.artist = artist;
		this.title = title;
		this.rating = rating;
		this.adder = adder;
		this.songWithPlaylist = songWithPlaylist;
		this.songLikers = songLikers;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public User getAdder() {
		return adder;
	}

	public void setAdder(User adder) {
		this.adder = adder;
	}

	public List<Playlist> getSongWithPlaylist() {
		return songWithPlaylist;
	}

	public void setSongWithPlaylist(List<Playlist> songWithPlaylist) {
		this.songWithPlaylist = songWithPlaylist;
	}


	public List<User> getSongLikers() {
		return songLikers;
	}

	public void setSongLikers(List<User> songLikers) {
		this.songLikers = songLikers;
	}
	
	
	
}
