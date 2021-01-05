package com.example.explore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.explore.dto.RatingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class JobRatingControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	RatingDTO request = new RatingDTO(4,"good work",55);
	
	@Test
	public void testCreateRatingWithMockMvc() throws Exception {
		this.mockMvc.perform(post("/jobs/1/ratings").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
				.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testGetRatingsWithMockMvc() throws Exception {
		this.mockMvc.perform(get("/jobs/1/ratings?page=0&size=5&sort=score,asc")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteRatingWithMockMvc() throws Exception {
		this.mockMvc.perform(delete("/jobs/1/ratings/55")).andExpect(status().is4xxClientError());
	}
	
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
