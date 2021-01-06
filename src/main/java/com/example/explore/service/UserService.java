package com.example.explore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.explore.domain.Role;
import com.example.explore.domain.User;
import com.example.explore.repository.RoleRepository;
import com.example.explore.repository.UserRepository;
import com.example.explore.security.JwtProvider;

@Service
public class UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder,JwtProvider jwtProvider) {
		super();
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}

	public Optional<String> signin(String username, String password) {
		logger.info("New user attempting to sign in");
		Optional<String> token = Optional.empty();
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				token = Optional.of(jwtProvider.createToken(username, user.get().getRoles()));
			} catch (AuthenticationException e) {
				logger.info("Log in failed for user {}", username);
			}
		}
		return token;
	}

	public Optional<User> signup(String username, String password, String firstName, String lastName) {
		if (!userRepository.findByUsername(username).isPresent()) {
			Optional<Role> role = roleRepository.findByRoleName("ROLE_USER");
			return Optional.of(userRepository
					.save(new User(username, passwordEncoder.encode(password), role.get(), firstName, lastName)));
		}
		return Optional.empty();
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

}
