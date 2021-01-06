package com.example.explore.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.explore.JwtRequestHelper;
import com.example.explore.domain.JobScope;
import com.example.explore.domain.JobScopeRating;
import com.example.explore.dto.RatingDTO;
import com.example.explore.service.JobScopeRatingService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobRatingAPITest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JwtRequestHelper jwtRequestHelper;

	@MockBean
	private JobScopeRatingService serviceMock;

	@Mock
	private JobScopeRating jobRatingMock;

	@Mock
	private JobScope jobMock;

	private RatingDTO ratingDto = new RatingDTO(SCORE, COMMENT, CUSTOMER_ID);

	private static final int JOB_ID = 999;
	private static final int CUSTOMER_ID = 1000;
	private static final int SCORE = 3;
	private static final String COMMENT = "comment";
	private static final String JOB_RATINGS_URL = "/jobs/" + JOB_ID + "/ratings";

	@BeforeEach
	void setup() {
		Mockito.when(jobRatingMock.getComment()).thenReturn(COMMENT);
		Mockito.when(jobRatingMock.getScore()).thenReturn(SCORE);
		Mockito.when(jobRatingMock.getClientId()).thenReturn(CUSTOMER_ID);
		Mockito.when(jobRatingMock.getJobScope()).thenReturn(jobMock);
		Mockito.when(jobMock.getId()).thenReturn(JOB_ID);

	}

	@Test
	public void createNewRating() throws Exception {
		this.restTemplate.exchange(JOB_RATINGS_URL, HttpMethod.POST,
				new HttpEntity<>(ratingDto, jwtRequestHelper.withRole("ROLE_USER")), Void.class);
		Mockito.verify(this.serviceMock).createNew(JOB_ID, CUSTOMER_ID, SCORE, COMMENT);
	}

	@Test
	public void delete() throws Exception {
		this.restTemplate.exchange(JOB_RATINGS_URL + "/" + CUSTOMER_ID, HttpMethod.DELETE,
				new HttpEntity<>(jwtRequestHelper.withRole("ROLE_USER")), Void.class);
		Mockito.verify(serviceMock).delete(JOB_ID, CUSTOMER_ID);
	}

	@Test
	public void getAllJobRatings() throws Exception {
		List<JobScopeRating> listOfTourRatings = Arrays.asList(jobRatingMock);
		Page<JobScopeRating> page = new PageImpl<>(listOfTourRatings, PageRequest.of(0, 10), 1);
		Mockito.when(serviceMock.lookupRatings(anyInt(), any(Pageable.class))).thenReturn(page);

		ResponseEntity<String> response = restTemplate.getForEntity(JOB_RATINGS_URL, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Mockito.verify(serviceMock).lookupRatings(anyInt(), any(Pageable.class));

	}

}
