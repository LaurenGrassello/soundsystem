package com.laurenodalen.lookify.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SongService songService;
	@Autowired
	private PlaylistService playlistService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		List<Song> songs = songService.findTopTen();
		model.addAttribute("songs", songs);
		List<Playlist> playlists = playlistService.findTopTenPlaylists();
		model.addAttribute("playlists", playlists);
		return "home.jsp";

	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		userService.register(newUser, result);

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("userId", newUser.getId());
		session.setAttribute("email", newUser.getEmail());
		return "redirect:/home";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}

		session.setAttribute("userId", user.getId());
		session.setAttribute("email", user.getEmail());

		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String endSession(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/users/search")
	public String searchUsers(HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}else {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUser(userId);
			model.addAttribute("user", user);
			return "searchUsers.jsp";
		}
	}
	
	// search users
	@GetMapping("/users/search/results")
	public String searchUsersResults(@RequestParam(value = "username", required = false) String username, Model model,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!username.isEmpty()) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUser(userId);
			model.addAttribute("user", user);
			List<User> users = userService.searchfriends(username);
			model.addAttribute("friends", users);
			model.addAttribute("friendsFound", username);
			model.addAttribute("friends", userService.allUsers());
			return "searchUsers.jsp";
		} else {
			return null;
		}
	}

	@PutMapping("/users/{id}/addfriend")
	public String addFriend(@PathVariable("id") Long userId, HttpSession session, 
			@RequestParam("userId") Long friendId, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long sessionUser = (Long) session.getAttribute("userId");
		User user = userService.findUser(sessionUser);
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		userService.addFriend( userId, friendId);

		return "redirect:/users/search";
	}

	@PutMapping("/users/{id}/unfriend")
	public String unFriend(@PathVariable("id") Long userId, HttpSession session, 
			@RequestParam("userId")Long friendId) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		userService.unFriend(userId, friendId);
		return "redirect:/users/search";
	}

	

}
