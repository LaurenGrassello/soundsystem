package com.laurenodalen.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurenodalen.lookify.models.Playlist;
import com.laurenodalen.lookify.models.Song;
import com.laurenodalen.lookify.models.User;
import com.laurenodalen.lookify.repositories.PlaylistRepository;

@Service
public class PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private SongService songService;
	@Autowired
	private UserService userService; 

	// all playlists
	public List<Playlist> allPlaylists() {
		return playlistRepository.findAll();
	}

	// top ten playlists
	public List<Playlist> findTopTenPlaylists() {
		return playlistRepository.findTop10ByOrderByRatingDesc();
	}

	// add playlist
	public Playlist createPlaylist(Playlist playlist) {
		return playlistRepository.save(playlist);
	}

	// get playlist
	public Playlist findPlaylist(Long id) {
		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
		if (optionalPlaylist.isPresent()) {
			return optionalPlaylist.get();
		} else {
			return null;
		}
	}

	// get playlist by name
	public List<Playlist> searchName(String name) {
		List<Playlist> playlists = this.playlistRepository.searchname(name);
		return playlists;
	}

	// delete playlist
	public void deletePlaylist(Long id) {
		playlistRepository.deleteById(id);
	}

	// add song to playlist
	public void addSongToPlaylist(Long playlistId, Long songId) {
		Song song = songService.findSong(songId);
		Playlist playlist = this.findPlaylist(playlistId);
		playlist.getSongWithPlaylist().add(song);
		playlistRepository.save(playlist);
	}

	// remove song from playlist
	public void removeSongFromPlaylist(Long playlistId, Long songId) {
		Song song = songService.findSong(songId);
		Playlist playlist = this.findPlaylist(playlistId);
		playlist.getSongWithPlaylist().remove(song);
		playlistRepository.save(playlist);
	}

	// like playlist
	public void likePlaylist(Long playlistId, Long userId) {
		User user = userService.findUser(userId);
		Playlist playlist = this.findPlaylist(playlistId);
		playlist.getPlaylistLikers().add(user);
		playlistRepository.save(playlist);
	}

	// unlike playlist
	public void unlikePlaylist(Long playlistId, Long userId) {
		User user = userService.findUser(userId);
		Playlist playlist = this.findPlaylist(playlistId);
		playlist.getPlaylistLikers().remove(user);
		playlistRepository.save(playlist);
	}

}
