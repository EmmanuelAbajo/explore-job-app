package com.example.explore.service;

import org.springframework.stereotype.Service;

import com.example.explore.domain.ExperienceLevel;
import com.example.explore.domain.Freelancer;
import com.example.explore.repository.FreelancerRepository;

@Service
public class FreelancerService {
	
	private final FreelancerRepository repo;

	public FreelancerService(FreelancerRepository repo) {
		super();
		this.repo = repo;
	}
	
	public Freelancer createFreelancer(String firstName, String lastName, ExperienceLevel experienceLevel) {
		return repo.save(new Freelancer(firstName, lastName, experienceLevel));
	}

}
