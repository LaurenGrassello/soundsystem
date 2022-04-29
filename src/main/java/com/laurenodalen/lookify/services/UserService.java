package com.laurenodalen.lookify.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.laurenodalen.lookify.models.LoginUser;
import com.laurenodalen.lookify.models.User;
import com.laurenodalen.lookify.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;

	public User register(User newUser, BindingResult result) {
		// TO-DO: Additional validations!
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		if (optionalUser.isPresent()) {
			result.rejectValue("email", "uniqueness", "Email already in use");
		}
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "match", "password must match");
		}

		if (result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}

	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> optionalUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!optionalUser.isPresent()) {
			result.rejectValue("email", "uniqueness", "Incorrect email or password");
			return null;
		}
		User user = optionalUser.get();
		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "match", "Incorrect email or password");
		}
		if (result.hasErrors()) {
			return null;
		}
		return user;
	}

	public User findUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}

// search through users
	public List<User> searchfriends(String username) {
		List<User> users = this.userRepo.searchfriends(username);
		return users;
	}

	// all users
	public List<User> allUsers() {
		return userRepo.findAll();
	}

	
	//add friend
	public void addFriend(Long friendId, Long userId) {
		User user = userService.findUser(userId);
		User friend = this.findUser(friendId);
		user.getUserFriend().add(friend);
		userRepo.save(user);
	}

	// unfriend
	public void unFriend(Long friendId, Long userId) {
		User user = userService.findUser(userId);
		User friend = this.findUser(friendId);
		user.getUserFriend().remove(friend);
		userRepo.save(user);
	}
}
