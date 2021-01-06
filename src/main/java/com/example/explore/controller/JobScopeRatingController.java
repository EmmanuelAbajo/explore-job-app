package com.example.explore.controller;

import java.util.AbstractMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.explore.domain.JobScopeRating;
import com.example.explore.dto.RatingDTO;
import com.example.explore.service.JobScopeRatingService;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

//@Api(description = "API for Job Scope Ratings")
@RestController
@RequestMapping(path = "/jobs/{jobId}/ratings")
public class JobScopeRatingController {

	private final static Logger logger = LoggerFactory.getLogger(JobScopeRatingController.class);
	private final JobScopeRatingService jobRatingService;

	public JobScopeRatingController(JobScopeRatingService jobRatingService) {
		super();
		this.jobRatingService = jobRatingService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_USER')")
//	@ApiOperation(value = "Create New Rating")
//	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created") })
	public void createJobRating(@PathVariable(value = "jobId") int jobId, @RequestBody @Validated RatingDTO ratingDto) {
		logger.info("POST /jobs/{}/ratings", jobId);
		jobRatingService.createNew(jobId, ratingDto.getClientId(), ratingDto.getScore(), ratingDto.getComment());

	}

	@PostMapping("/{score}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public void createManyTourRatings(@PathVariable(value = "jobId") int jobId,
			@PathVariable(value = "score") int score, @RequestParam("clients") Integer clients[]) {
		logger.info("POST /tours/{}/ratings/{}", jobId, score);
		jobRatingService.rateMany(jobId, score, clients);
	}

	@GetMapping
//	@ApiOperation(value = "Find all ratings")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
//			@ApiResponse(code = 404, message = "Ratings not found for job id") })
	public Page<RatingDTO> getAllRatingsForJob(@PathVariable(value = "jobId") int jobId, Pageable pg) {
		logger.info("GET /jobs/{}/ratings", jobId);
		Page<JobScopeRating> ratings = jobRatingService.lookupRatings(jobId, pg);
		return new PageImpl<>(ratings.get().map(RatingDTO::new).collect(Collectors.toList()), pg,
				ratings.getTotalElements());
	}

	@GetMapping(path = "/average")
//	@ApiOperation(value = "Get average of ratings for specific job")
	public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "jobId") int jobId) {
		logger.info("GET /jobs/{}/ratings/average", jobId);
		return new AbstractMap.SimpleEntry<String, Double>("average", jobRatingService.getAverageScore(jobId));
	}

	@PutMapping // This updates every field in the rating object while patch updates specified
				// fields
	@PreAuthorize("hasRole('ROLE_USER')")
//	@ApiOperation(value = "Update job rating")
	public RatingDTO updateWithPut(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
		logger.info("PUT /jobs/{}/ratings", jobId);
		return new RatingDTO(
				jobRatingService.update(jobId, ratingDto.getClientId(), ratingDto.getScore(), ratingDto.getComment()));
	}

	/**
	 * Update score or comment of a Job scope Rating
	 *
	 * @param jobId     job identifier
	 * @param ratingDto rating Data Transfer Object
	 * @return The modified Rating DTO.
	 */
	@PatchMapping
	@PreAuthorize("hasRole('ROLE_USER')")
//	@ApiOperation(value = "Update specified fields in job rating")
	public RatingDTO updateWithPatch(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
		logger.info("PATCH /jobs/{}/ratings", jobId);
		return new RatingDTO(jobRatingService.updateSome(jobId, ratingDto.getClientId(), ratingDto.getScore(),
				ratingDto.getComment()));
	}

	/**
	 * Delete a Rating of a job scope made by a client
	 *
	 * @param jobId    job identifier
	 * @param clientId client identifier
	 */
	@DeleteMapping(path = "/{clientId}")
	@PreAuthorize("hasRole('ROLE_USER')")
//	@ApiOperation(value = "Delete job rating provided by a client")
	public void delete(@PathVariable(value = "jobId") int jobId, @PathVariable(value = "clientId") int clientId) {
		logger.info("DELETE /jobs/{}/ratings/{}", jobId, clientId);
		jobRatingService.delete(jobId, clientId);
	}

}
