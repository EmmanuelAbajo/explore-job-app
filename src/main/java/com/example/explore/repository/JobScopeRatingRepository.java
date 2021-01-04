package com.example.explore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.explore.domain.JobScopeRating;
import com.example.explore.domain.JobScopeRatingPk;

@RepositoryRestResource(exported = false)
public interface JobScopeRatingRepository extends CrudRepository<JobScopeRating, JobScopeRatingPk> {
	
	Optional<JobScopeRating> findByPkJobScopeIdAndPkClientId(Integer jobScopeId, Integer clientId);
	
	List<JobScopeRating> findByPkJobScopeId(Integer jobScopeId);
	
	Page<JobScopeRating> findByPkJobScopeId(Integer jobScopeId, Pageable pg);

}
