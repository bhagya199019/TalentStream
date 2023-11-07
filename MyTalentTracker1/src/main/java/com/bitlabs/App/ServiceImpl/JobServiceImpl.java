package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Service.JobService;

@Service
public class JobServiceImpl implements JobService {
  
	 @Autowired
	private JobRepository jobRepository;
	 
	 public Job postJob(Job job) {
		return jobRepository.save(job);
	 }
	
}
