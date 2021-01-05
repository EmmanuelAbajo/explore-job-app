package com.example.explore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.explore.domain.JobScope;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobScopeRepository extends CrudRepository<JobScope, Integer> {

	@Override
	@RestResource(exported = false)
	default <S extends JobScope> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RestResource(exported = false)
	default <S extends JobScope> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RestResource(exported = false)
	default void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	@RestResource(exported = false)
	default void delete(JobScope entity) {
		// TODO Auto-generated method stub

	}

	@Override
	@RestResource(exported = false)
	default void deleteAll(Iterable<? extends JobScope> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	@RestResource(exported = false)
	default void deleteAll() {
		// TODO Auto-generated method stub

	}

}
