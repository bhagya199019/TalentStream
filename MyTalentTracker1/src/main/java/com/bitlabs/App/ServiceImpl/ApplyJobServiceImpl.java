package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.ApplyJobRepository;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Service.ApplyJobservice;

@Service
public class ApplyJobServiceImpl implements ApplyJobservice {
	
	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobApplicantRepository jobApplicantRepository;
	
	public String applicantApplyForJob(long applicantId, long jobId) {
		JobApplicant jobApplicant=jobApplicantRepository.findById(applicantId);
		Job job=jobRepository.findById(jobId).orElse(null);
		
		if(jobApplicant==null && job==null) {
			return "Applicant Id or job Id not null";
		}
		
		else {
			if(!applyJobRepository.existsByJobApplicantAndJob(jobApplicant, job)) {
				ApplyJob applyJob = new  ApplyJob();
		           applyJob.setJobApplicant(jobApplicant);
		           applyJob.setJob(job);
		           applyJobRepository.save(applyJob);
		           return "Job Applied Successfully";
			}
			else {
	    		   return "Job has already been applied by the applicant";
	            }
		}
		
	}
	
	

}
