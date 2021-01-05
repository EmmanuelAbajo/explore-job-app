package com.example.explore.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.explore.domain.JobScopeRating;

@SpringBootTest
@Transactional // ensures that all db changes are rolled back after the test
public class JobScopeRatingServiceIntegrationTest {

	private static final int CLIENT_ID = 60;
	private static final int JOB_ID = 1;
	private static final int NOT_A_JOB_ID = 123;

	@Autowired
	private JobScopeRatingService service;

	@Test
	public void createNew() {
		service.createNew(JOB_ID, CLIENT_ID, 2, "it was fair");

		// Verify New Job Rating created.
		JobScopeRating rating = service.verifyJobScopeRating(JOB_ID, CLIENT_ID);
		assertThat(rating.getJobScope().getId()).isEqualTo(JOB_ID);
		assertThat(rating.getClientId()).isEqualTo(CLIENT_ID);
		assertThat(rating.getScore()).isEqualTo(2);
		assertThat(rating.getComment()).isEqualTo("it was fair");
	}

	@Test
	public void createNewException() {
		Exception exception = assertThrows(NoSuchElementException.class, () -> {
			service.createNew(NOT_A_JOB_ID, CLIENT_ID, 2, "it was fair");
	    });

	    String expectedMessage = "Job Scope does not exist";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}

}
