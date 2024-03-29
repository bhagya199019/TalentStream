package com.bitlabs.App.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bitlabs.App.Entity.Alert;
import com.bitlabs.App.Entity.JobApplicant;


public interface ApplicantService {
	
	public JobApplicant findByEmailAddress(String userEmail);
	public ResponseEntity<String> saveApplicant(JobApplicant applicant);
	public List<JobApplicant> getAllApplicants();
	 public List<JobApplicant> getApplicantsByStatus(String applicantStatus);
	 
	  public JobApplicant login(String email, String password);

}
