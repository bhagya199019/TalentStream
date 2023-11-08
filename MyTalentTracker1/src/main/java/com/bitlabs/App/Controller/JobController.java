package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.JobService;

import jakarta.validation.Valid;

@RestController
public class JobController {
      
	@Autowired
	 private JobService jobService;
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	
	
	@PostMapping("/recruiter/post-job/{jobRecruiterid}")
 public ResponseEntity<String> postJob(@RequestBody  @Valid Job job,@PathVariable Long jobRecruiterid){
		JobRecruiter jobRecruiter=jobRecruiterRepository.findByRecruiterId(jobRecruiterid);
		
		if(jobRecruiter!=null) {
			job.setJobRecruiter(jobRecruiter);
			jobService.postJob(job);
			
		return ResponseEntity.status(HttpStatus.OK).body("job posted successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job recruiter with id"+jobRecruiterid+"not found");
		}
		
	 }
	
	
}
