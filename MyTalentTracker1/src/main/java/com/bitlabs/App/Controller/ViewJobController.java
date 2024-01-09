package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Repository.JobRepository;

@RestController
public class ViewJobController {
	
	@Autowired
	private JobRepository jobRepository;

	   @GetMapping("/applicants/viewJob/{jobId}")
	 public ResponseEntity<?>viewJobDetailsByApplicant(@PathVariable Long jobId){
		  Job job=jobRepository.findById(jobId).orElse(null);
		  
		  if(job!=null) {
			  return ResponseEntity.ok(job);
		  }
		  else {
			  return ResponseEntity.notFound().build();
		  }
		 
	 }
}
