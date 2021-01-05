package com.example.explore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.explore.domain.Role;
import com.example.explore.domain.User;
import com.example.explore.repository.RoleRepository;
import com.example.explore.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Authentication signin(String username, String password) {
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
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
