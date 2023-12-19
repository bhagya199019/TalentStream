package com.bitlabs.App.ServiceImpl;

import java.util.List;

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
	 
	 
	 public List<Job> getJobsByRecruiter(Long jobRecruiterId) {
	        
	        List<Job> jobs = jobRepository.findByJobRecruiterId(jobRecruiterId);

	        return jobs;
	    }
	   
	 


	 
	 public List<Job> searchJobsByFilters(
		        String jobTitle,
		        Long id,
		        Integer minExperience,
		        Integer maxExperience,
		        Double maxSalary,
		        String location,
		        String employeeType,
		        String industryType,
		        String minimumQualification,
		        String specialization
		    ) {
		        return jobRepository.searchJobsByFilters(
		            jobTitle,
		            id,
		            minExperience,
		            maxExperience,
		            maxSalary,
		            location,
		            employeeType,
		            industryType,
		            minimumQualification,
		            specialization
		        );
		    }
	
}

