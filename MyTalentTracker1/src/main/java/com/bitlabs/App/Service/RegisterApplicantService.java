package com.bitlabs.App.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.bitlabs.App.Entity.JobApplicant;


public interface RegisterApplicantService {
	
	public JobApplicant findByEmailAddress(String userEmail);
	public ResponseEntity<String> saveApplicant(JobApplicant applicant);
	public List<JobApplicant> getAllApplicants();

}
