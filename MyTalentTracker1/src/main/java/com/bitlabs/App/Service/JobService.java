package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.Job;


public interface JobService {

	 public Job postJob(Job job);
		    
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
		    );
	 
	 public List<Job> getJobsByRecruiter(Long jobRecruiterId);
}
