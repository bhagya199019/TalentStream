package com.bitlabs.App.Entity;

import java.util.List;
import java.util.stream.Collectors;

import com.bitlabs.App.DTO.JobDTO;

public class JobMapper {
	
	
	 public static JobDTO mapToDTO(Job job) {
	        JobDTO jobDTO = new JobDTO();
	        jobDTO.setId(job.getId());
	        jobDTO.setJobTitle(job.getJobTitle());
	        jobDTO.setMinimumExperience(job.getMinimumExperience());
	        jobDTO.setMaximumExperience(job.getMaximumExperience());
	        jobDTO.setMaxSalary(job.getMaxSalary());
	        jobDTO.setMinSalary(job.getMinSalary());
	        jobDTO.setLocation(job.getLocation());
	        jobDTO.setEmployeeType(job.getEmployeeType());
	        jobDTO.setIndustryType(job.getIndustryType());
	        jobDTO.setMinimumQualification(job.getMinimumQualification());
	        jobDTO.setSpecialization(job.getSpecialization());
	        jobDTO.setJobHighlights(job.getJobHighlights());
	        jobDTO.setDescription(job.getDescription());
	        
	        return jobDTO;
	 } 
	        
	        public static List<JobDTO> mapToDTOs(List<Job> jobs) {
	            return jobs.stream().map(JobMapper::mapToDTO).collect(Collectors.toList());
	        }

}
