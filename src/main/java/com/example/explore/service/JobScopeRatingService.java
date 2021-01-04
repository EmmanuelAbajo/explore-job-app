package com.example.explore.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.explore.domain.JobScope;
import com.example.explore.domain.JobScopeRating;
import com.example.explore.repository.JobScopeRatingRepository;
import com.example.explore.repository.JobScopeRepository;

@Service
@Transactional
public class JobScopeRatingService {

	private final JobScopeRepository jobRepo;
	private final JobScopeRatingRepository jobRatingRepo;

	public JobScopeRatingService(JobScopeRepository jobRepo, JobScopeRatingRepository jobRatingRepo) {
		super();
		this.jobRepo = jobRepo;
		this.jobRatingRepo = jobRatingRepo;
	}

	public void createNew(int jobId, Integer clientId, Integer score, String comment) throws NoSuchElementException {
		jobRatingRepo.save(new JobScopeRating(verifyJobScope(jobId), clientId, score, comment));
	}

	public Page<JobScopeRating> lookupRatings(int jobId, Pageable pg) throws NoSuchElementException {
		return jobRatingRepo.findByJobScopeId(verifyJobScope(jobId).getId(), pg);
	}

	public JobScopeRating update(int jobId, Integer clientId, Integer score, String comment)
			throws NoSuchElementException {
		JobScopeRating rating = verifyJobScopeRating(jobId, clientId);
		rating.setScore(score);
		rating.setComment(comment);
		return jobRatingRepo.save(rating);
	}

	public JobScopeRating updateSome(int jobId, Integer clientId, Integer score, String comment)
			throws NoSuchElementException {
		JobScopeRating rating = verifyJobScopeRating(jobId, clientId);
		if (score != null) {
			rating.setScore(score);
		}
		if (comment != null) {
			rating.setComment(comment);
		}
		return jobRatingRepo.save(rating);
	}

	public void delete(int jobId, Integer clientId) throws NoSuchElementException {
		JobScopeRating rating = verifyJobScopeRating(jobId, clientId);
		jobRatingRepo.delete(rating);
	}

	public Double getAverageScore(int jobId) throws NoSuchElementException {
		List<JobScopeRating> ratings = jobRatingRepo.findByJobScopeId(verifyJobScope(jobId).getId());
		OptionalDouble average = ratings.stream().mapToInt((rating) -> rating.getScore()).average();
		return average.isPresent() ? average.getAsDouble() : null;
	}
	
	 /**
     * Service for many clients to give the same score for a job scope
     *
     * @param jobId
     * @param score
     * @param clients
     */
    public void rateMany(int jobId,  int score, Integer [] clients) {
    	jobRepo.findById(jobId).ifPresent(job -> {
            for (Integer c : clients) {
            	jobRatingRepo.save(new JobScopeRating(job, c, score));
            }
        });
    }


	/**
	 * Verify and return the JobScopeRating for a particular jobId and Client
	 * 
	 * @param jobId    job identifier
	 * @param clientId client identifier
	 * @return the found JobScopeRating
	 * @throws NoSuchElementException if no JobScopeRating found
	 */
	private JobScopeRating verifyJobScopeRating(int jobId, int clientId) throws NoSuchElementException {
		return jobRatingRepo.findByJobScopeIdAndClientId(jobId, clientId).orElseThrow(() -> new NoSuchElementException(
				"Job Scope Rating pair for request(" + jobId + " for client " + clientId + ")"));
	}

	private JobScope verifyJobScope(int id) throws NoSuchElementException {
		return jobRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Job Scope does not exist " + id));
	}

}
