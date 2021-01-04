package com.example.explore.controller;

import java.util.AbstractMap;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.explore.domain.JobScopeRating;
import com.example.explore.dto.RatingDTO;
import com.example.explore.service.JobScopeRatingService;

@RestController
@RequestMapping(path = "/jobs/{jobId}/ratings")
public class JobScopeRatingController {

	private final JobScopeRatingService jobRatingService;

	public JobScopeRatingController(JobScopeRatingService jobRatingService) {
		super();
		this.jobRatingService = jobRatingService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createJobRating(@PathVariable(value = "jobId") int jobId, @RequestBody @Validated RatingDTO ratingDto) {
		jobRatingService.createNew(jobId, ratingDto.getClientId(), ratingDto.getScore(), ratingDto.getComment());

	}

	@GetMapping
	public Page<RatingDTO> getAllRatingsForJobByPage(@PathVariable(value = "jobId") int jobId, Pageable pg) {
		Page<JobScopeRating> ratings = jobRatingService.lookupRatings(jobId, pg);
		return new PageImpl<>(ratings.get().map(RatingDTO::new).collect(Collectors.toList()), pg,
				ratings.getTotalElements());
	}

	@GetMapping(path = "/average")
	public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "jobId") int jobId) {
		return new AbstractMap.SimpleEntry<String, Double>("average", jobRatingService.getAverageScore(jobId));
	}

	// This updates every field in the rating object while patch updates specified
	// fields
	@PutMapping
	public RatingDTO updateWithPut(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
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
	public RatingDTO updateWithPatch(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
		return new RatingDTO(
				jobRatingService.updateSome(jobId, ratingDto.getClientId(), ratingDto.getScore(), ratingDto.getComment()));
	}

	/**
	 * Delete a Rating of a job scope made by a client
	 *
	 * @param jobId    job identifier
	 * @param clientId client identifier
	 */
	@DeleteMapping(path = "/{clientId}")
	public void delete(@PathVariable(value = "jobId") int jobId, @PathVariable(value = "clientId") int clientId) {
		jobRatingService.delete(jobId, clientId);
	}

}
