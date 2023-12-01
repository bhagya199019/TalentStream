package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.SavedJob;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Repository.SavedJobrepository;
import com.bitlabs.App.Service.SavedJobService;

@Service
public class SavedJobServiceImpl implements SavedJobService {
	
	 @Autowired
	 private JobApplicantRepository jobApplicantRepository;
	
	 @Autowired
	 private SavedJobrepository savedJobRepository;
	
     @Autowired
     private JobRepository jobRepository;
 
 public void saveJobForApplicant(Long applicantId,Long jobId) throws Exception {
	 JobApplicant jobApplicant=jobApplicantRepository.findById(applicantId).orElse(null);;
	Job job=jobRepository.findById(jobId).orElse(null);;
	 
	 if(jobApplicant==null || job==null) {
		 throw new Exception ("Applicant Id or Job Id not found");
	 }
	 
	 else {
		 if(!savedJobRepository.existsByJobApplicantAndJob(jobApplicant,job)){
			 SavedJob savedJob=new SavedJob();
			 savedJob.setJobApplicant(jobApplicant);
			 savedJob.setJob(job);
			 savedJobRepository.save(savedJob);
			 }
		 else {
			 throw new Exception("job has already been saved by the applicant");
		 }
	 }
	 
  }
 
}
