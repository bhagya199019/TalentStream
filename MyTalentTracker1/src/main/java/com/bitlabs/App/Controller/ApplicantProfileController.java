package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.ApplicantProfileService;

public class ApplicantProfileController {

	@Autowired
 private JobApplicantRepository jobApplicantRepository;
	
	@Autowired
 private ApplicantProfileService applicantProfileService;
	
@PostMapping("/applicant/createProfile/{applicantId}")
public ResponseEntity<ApplicantProfile>createOrUpdateProfile(@RequestBody ApplicantProfile applicantProfile,@PathVariable long applicantId){
	
	JobApplicant jobApplicant=jobApplicantRepository.getJobApplicantById(applicantId);
	
	if(jobApplicant==null) {
		return ResponseEntity.notFound().build();
	}
	else {
		applicantProfile.setApplicant(jobApplicant);
		ApplicantProfile createdProfile=applicantProfileService.createOrUpdateProfile(applicantProfile);
		return ResponseEntity.ok(createdProfile);
		
	}
}
	
	
	
	
	
}
