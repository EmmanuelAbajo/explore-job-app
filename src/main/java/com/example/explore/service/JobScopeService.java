package com.example.explore.service;

import org.springframework.stereotype.Service;

import com.example.explore.domain.Freelancer;
import com.example.explore.domain.JobScope;
import com.example.explore.repository.JobScopeRepository;

@Service
public class JobScopeService {
	
	private final JobScopeRepository repo;

	public JobScopeService(JobScopeRepository repo) {
		super();
		this.repo = repo;
	}
	
	public JobScope createJob(String name, String description, Integer renumeration, Freelancer freelancer) {
		return repo.save(new JobScope(name, description, renumeration, freelancer));
	}

}
