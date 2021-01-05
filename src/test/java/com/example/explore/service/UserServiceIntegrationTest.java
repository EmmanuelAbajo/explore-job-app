package com.example.explore.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(user.get().getPassword()).isNotEqualTo("dummypassword");
		System.out.println("Encoded Password = " + user.get().getPassword());
	}

}
