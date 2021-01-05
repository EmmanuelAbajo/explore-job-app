package com.example.explore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.explore.domain.User;
import com.example.explore.dto.LoginDTO;
import com.example.explore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signin")
	public Authentication login(@RequestBody @Valid LoginDTO loginDto) {
		return userService.signin(loginDto.getUsername(), loginDto.getPassword());
	}

	@PostMapping("/signup")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User signup(@RequestBody @Valid LoginDTO loginDto) {
		return userService
				.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(), loginDto.getLastName())
				.orElseThrow(() -> new RuntimeException("User already exists"));
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

}
