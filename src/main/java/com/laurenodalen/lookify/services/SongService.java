package com.laurenodalen.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurenodalen.lookify.models.Song;
import com.laurenodalen.lookify.models.User;
import com.laurenodalen.lookify.repositories.LookifyRepository;

@Service
public class SongService {
	@Autowired
	private LookifyRepository lookifyRepository;
	@Autowired
	private UserService userService;

	// all songs
	public List<Song> allSongs() {
		return lookifyRepository.findAll();
	}

	// top ten
	public List<Song> findTopTen() {
		return lookifyRepository.findTop10ByOrderByRatingDesc();
	}

	// add song
	public Song createSong(Song song) {
		return lookifyRepository.save(song);
	}

	// get song
	public Song findSong(Long id) {
		Optional<Song> optionalSong = lookifyRepository.findById(id);
		if (optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
	}

	// get song by artist
	public List<Song> searchArtist(String artist) {
		System.out.println(artist);
		List<Song> songs = this.lookifyRepository.searchartist(artist);
		return songs;
	}

	// get song by title
	public List<Song> searchSong(String song) {
		System.out.println(song);
		List<Song> songs = this.lookifyRepository.searchsong(song);
		return songs;
	}

	// delete song
	public void deleteSong(Long id) {
		lookifyRepository.deleteById(id);
	}

	// like playlist
	public void likeSong(Long songId, Long userId) {
		User user = userService.findUser(userId);
		Song song = this.findSong(songId);
		song.getSongLikers().add(user);
		lookifyRepository.save(song);
	}

	// unlike playlist
	public void unlikeSong(Long songId, Long userId) {
		User user = userService.findUser(userId);
		Song song = this.findSong(songId);
		song.getSongLikers().remove(user);
		lookifyRepository.save(song);
	}
}
