package com.example.explore.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

import com.example.explore.domain.JobScope;
import com.example.explore.domain.JobScopeRating;
import com.example.explore.domain.JobScopeRatingPk;
import com.example.explore.dto.RatingDTO;
import com.example.explore.repository.JobScopeRatingRepository;
import com.example.explore.repository.JobScopeRepository;

@RestController
@RequestMapping(path = "/jobs/{jobId}/ratings")
public class JobScopeRatingController {

	private final JobScopeRepository jobRepo;
	private final JobScopeRatingRepository jobRatingRepo;

	public JobScopeRatingController(JobScopeRepository jobRepo, JobScopeRatingRepository jobRatingRepo) {
		super();
		this.jobRepo = jobRepo;
		this.jobRatingRepo = jobRatingRepo;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createJobRating(@PathVariable(value = "jobId") int jobId, @RequestBody @Validated RatingDTO ratingDto) {
		JobScope job = verifyJobScope(jobId);
		jobRatingRepo.save(new JobScopeRating(new JobScopeRatingPk(job, ratingDto.getClientId()), ratingDto.getScore(),
				ratingDto.getComment()));
	}

	@GetMapping
	public List<RatingDTO> getAllRatingsForJob(@PathVariable(value = "jobId") int jobId) {
		verifyJobScope(jobId);
		return jobRatingRepo.findByPkJobScopeId(jobId).stream().map(RatingDTO::new).collect(Collectors.toList());
	}
	
	@GetMapping(path = "/page")
	public Page<RatingDTO> getAllRatingsForJobByPage(@PathVariable(value = "jobId") int jobId, Pageable pg) {
		verifyJobScope(jobId);
		Page<JobScopeRating> ratings = jobRatingRepo.findByPkJobScopeId(jobId, pg);
		return new PageImpl<>(
				ratings.get().map(RatingDTO::new).collect(Collectors.toList()),
				pg,
				ratings.getTotalElements()
				);
	}

	@GetMapping(path = "/average")
	public Map<String, Double> getAverage(@PathVariable(value = "jobId") int jobId) {
		verifyJobScope(jobId);
		return Map.of("average", jobRatingRepo.findByPkJobScopeId(jobId).stream().mapToInt(JobScopeRating::getScore)
				.average().orElseThrow(() -> new NoSuchElementException("Job Scope has no rating")));
	}

	// This updates every field in the rating object while patch updates specified fields
	@PutMapping
	public RatingDTO updateWithPut(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
		JobScopeRating rating = verifyJobScopeRating(jobId, ratingDto.getClientId());
		rating.setScore(ratingDto.getScore());
		rating.setComment(ratingDto.getComment());
		return new RatingDTO(jobRatingRepo.save(rating));
	}

	/**
	 * Update score or comment of a Job scope Rating
	 *
	 * @param jobId    job identifier
	 * @param ratingDto rating Data Transfer Object
	 * @return The modified Rating DTO.
	 */
	@PatchMapping
	public RatingDTO updateWithPatch(@PathVariable(value = "jobId") int jobId,
			@RequestBody @Validated RatingDTO ratingDto) {
		JobScopeRating rating = verifyJobScopeRating(jobId, ratingDto.getClientId());
		if (ratingDto.getScore() != null) {
			rating.setScore(ratingDto.getScore());
		}
		if (ratingDto.getComment() != null) {
			rating.setComment(ratingDto.getComment());
		}
		return new RatingDTO(jobRatingRepo.save(rating));
	}

	/**
	 * Delete a Rating of a job scope made by a client
	 *
	 * @param jobId     job identifier
	 * @param clientId client identifier
	 */
	@DeleteMapping(path = "/{clientId}")
	public void delete(@PathVariable(value = "jobId") int jobId, @PathVariable(value = "clientId") int clientId) {
		JobScopeRating rating = verifyJobScopeRating(jobId, clientId);
		jobRatingRepo.delete(rating);
	}

	/**
	 * Verify and return the JobScopeRating for a particular jobId and Client
	 * 
	 * @param jobId     job identifier
	 * @param clientId client identifier
	 * @return the found JobScopeRating
	 * @throws NoSuchElementException if no JobScopeRating found
	 */
	private JobScopeRating verifyJobScopeRating(int jobId, int clientId) throws NoSuchElementException {
		return jobRatingRepo.findByPkJobScopeIdAndPkClientId(jobId, clientId)
				.orElseThrow(() -> new NoSuchElementException(
						"Job Scope Rating pair for request(" + jobId + " for client " + clientId + ")"));
	}

	private JobScope verifyJobScope(int id) throws NoSuchElementException {
		return jobRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Job Scope does not exist " + id));
	}

}
