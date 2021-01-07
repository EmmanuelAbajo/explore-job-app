package com.example.explore.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.explore.domain.User;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

	@Autowired
	private UserService service;

	@Test
	public void signup() {
		Optional<User> user = service.signup("dummyUsername", "dummypassword", "john", "doe");
		assertNotEquals("dummypassword",user.get().getPassword());
	}

}
