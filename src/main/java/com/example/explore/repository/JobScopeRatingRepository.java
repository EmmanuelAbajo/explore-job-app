package com.example.explore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.explore.domain.JobScopeRating;

@RepositoryRestResource(exported = false)
public interface JobScopeRatingRepository extends CrudRepository<JobScopeRating, Integer> {
	
	Optional<JobScopeRating> findByJobScopeIdAndClientId(Integer jobScopeId, Integer clientId);
	
	List<JobScopeRating> findByJobScopeId(Integer jobScopeId);
	
	Page<JobScopeRating> findByJobScopeId(Integer jobScopeId, Pageable pg);

}
