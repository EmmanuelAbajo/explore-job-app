package com.example.explore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.explore.domain.ExperienceLevel;
import com.example.explore.domain.Freelancer;

public interface FreelancerRepository extends PagingAndSortingRepository<Freelancer, Integer> {
	
	Page<Freelancer> findByExperienceLevel (@Param("level") ExperienceLevel experienceLevel, Pageable pageable);

	@Override
	@RestResource(exported=false)
	default void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported=false)
	default void delete(Freelancer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported=false)
	default void deleteAll(Iterable<? extends Freelancer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported=false)
	default void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	

}
