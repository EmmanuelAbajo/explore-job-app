package com.example.explore.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.explore.domain.ExperienceLevel;
import com.example.explore.service.FreelancerService;
import com.example.explore.service.JobScopeService;


@Component
public class DataLoader implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
	
	private final FreelancerService freelancerService;
	private final JobScopeService jobService;
	
	

	public DataLoader(FreelancerService freelancerService, JobScopeService jobService) {
		super();
		this.freelancerService = freelancerService;
		this.jobService = jobService;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		jobService.createJob("Law firm website", "Build a law firm website on the MEAN stack", 500, null);
		jobService.createJob("Netflix rebuild", "Migrate netflix's architecture to micro services based", 2000, null);
		jobService.createJob("Cloud based implementation", "Migrate paystack's payment architecture to cloud", 1500, null);
		
		freelancerService.createFreelancer("Jack", "Don", ExperienceLevel.INTERMEDIATE);
		freelancerService.createFreelancer("Richard", "Hendricks", ExperienceLevel.EXPERT);
		freelancerService.createFreelancer("Billy", "biggeti", ExperienceLevel.BEGINNER);
		
		log.info("Data loaded successfully");
	}

}
