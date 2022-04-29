package com.laurenodalen.lookify.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laurenodalen.lookify.models.LoginUser;
import com.laurenodalen.lookify.models.Playlist;
import com.laurenodalen.lookify.models.Song;
import com.laurenodalen.lookify.models.User;
import com.laurenodalen.lookify.services.PlaylistService;
import com.laurenodalen.lookify.services.SongService;
import com.laurenodalen.lookify.services.UserService;

@Controller
public class SongController {
	@Autowired
	private SongService songService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlaylistService playlistService;

	// get one song
	@GetMapping("/songs/{id}")
	public String oneSong(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "oneSong.jsp";
	}

	// render new song form
	@GetMapping("/songs/new")
	public String newSongForm(@ModelAttribute("song") Song song, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "newSong.jsp";
	}

	// add new song
	@PostMapping("/songs/new")
	public String newSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session,
			Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "newSong.jsp";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUser(userId);
			model.addAttribute("user", user);
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			songService.createSong(song);
			return "redirect:/songs/playlists";
		}
	}

	// search songs
	@GetMapping("/songs/search")
	public String searchArtist(@RequestParam(value = "artist", required = false) String artist, Model model,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!artist.isEmpty()) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUser(userId);
			model.addAttribute("user", user);
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			System.out.println(artist);
			List<Song> songs = songService.searchArtist(artist);
			model.addAttribute("songs", songs);
			model.addAttribute("artistFound", artist);
			model.addAttribute("playlists", playlistService.allPlaylists());
			return "search.jsp";
		} else {
			return "search.jsp";
		}
	}

	// delete song
	@DeleteMapping("/songs/{id}")
	public String destroySong(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		songService.deleteSong(id);
		return "redirect:/home";
	}

	// top ten songs
	@GetMapping("/songs/search/topten")
	public String topTenSongs(@ModelAttribute("song") Song song, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Song> songs = songService.findTopTen();
		model.addAttribute("songs", songs);
		return "home.jsp";
	}

	// get all users songs and play lists
	@GetMapping("/songs/playlists")
	public String userLibrary(@ModelAttribute("song") Song song, @ModelAttribute("playlists") Playlist playlist,
			Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		List<Song> songs = songService.allSongs();
		model.addAttribute("songs", songs);
		List<Playlist> playlists = playlistService.allPlaylists();
		model.addAttribute("playlists", playlists);
		return "yourLibrary.jsp";

	}

	// get one play list
	@GetMapping("/playlists/{id}")
	public String onePlaylist(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		Playlist playlist = playlistService.findPlaylist(id);
		model.addAttribute("playlist", playlist);


		return "onePlaylist.jsp";
	}

	// search songs for play list
	@GetMapping("/playlists/{id}/search/songs")
	public String onePlaylistSearch(@PathVariable("id") Long id,
			@RequestParam(value = "artist", required = false) String artist, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		List<Song> songs = songService.searchArtist(artist);
		model.addAttribute("songs", songs);
		model.addAttribute("artistFound", artist);
		Playlist playlist = playlistService.findPlaylist(id);
		model.addAttribute("playlist", playlist);
		return "onePlaylist.jsp";
	}

	// render new play list form
	@GetMapping("/playlists/new")
	public String newPlaylistForm(@ModelAttribute("playlist") Playlist playlist, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "newPlaylist.jsp";
	}

	// add new play list
	@PostMapping("/playlists/new")
	public String newPlaylist(@Valid @ModelAttribute("playlist") Playlist playlist,
			@RequestParam(value = "artist", required = false) String artist, BindingResult result, HttpSession session,
			Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "newPlaylist.jsp";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUser(userId);
			model.addAttribute("user", user);
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			playlistService.createPlaylist(playlist);
			return "onePlaylist.jsp";
		}
	}

	// top ten play lists
	@GetMapping("/playlists/search/topten")
	public String topTenPlaylists(@ModelAttribute("song") Playlist playlist, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Playlist> playlists = playlistService.findTopTenPlaylists();
		model.addAttribute("playlists", playlists);
		;
		return "home.jsp";
	}

	// add to play list
	@PutMapping("/playlists/{id}/add")
	public String addSongToPlaylist(@PathVariable("id") Long playlistId, HttpSession session,
			@RequestParam("songId") Long songId, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		playlistService.addSongToPlaylist(playlistId, songId);
		return "redirect:/playlists/{id}";
	}

	// remove from play list
	@PutMapping("/playlists/{id}/remove")
	public String removeSongToPlaylist(@PathVariable("id") Long playlistId, HttpSession session,
			@RequestParam("songId") Long songId) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		playlistService.removeSongFromPlaylist(playlistId, songId);
		return "redirect:/playlists/{id}";
	}

	// delete play list
	@DeleteMapping("/playlists/{id}")
	public String destroyPlaylist(@PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		playlistService.deletePlaylist(id);
		return "redirect:/home";
	}

	//////// likes and unlikes for both song and playlists here//////////

	@PutMapping("/songs/{id}/like")
	public String likeSong(@PathVariable("id") Long songId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		songService.likeSong(songId, userId);

		return "redirect:/songs/playlists";
	}

	@PutMapping("/songs/{id}/unlike")
	public String unlikeGift(@PathVariable("id") Long songId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		songService.unlikeSong(songId, userId);
		return "redirect:/songs/playlists";
	}

	@PutMapping("/playlists/{id}/like")
	public String likePlaylist(@PathVariable("id") Long playlistId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		playlistService.likePlaylist(playlistId, userId);

		return "redirect:/songs/playlists";
	}

	@PutMapping("/playlists/{id}/unlike")
	public String unlikePlaylist(@PathVariable("id") Long playlistId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		playlistService.unlikePlaylist(playlistId, userId);
		return "redirect:/songs/playlists";
	}

}
