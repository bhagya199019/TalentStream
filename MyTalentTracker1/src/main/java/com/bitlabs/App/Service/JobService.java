package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.Job;


public interface JobService {

	 public Job postJob(Job job);
	// public List<Job> searchJobsByKeyword(String keyword);
	// public List<Job> searchJobsByKeywordAndjobId(String keyword, Long jobId);
	 
	// public List<Job> searchJobsByKeyword(
	//		    String keyword, Long id, Integer minExperience, Integer maxExperience, Double maxSalary);
	 
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
}
