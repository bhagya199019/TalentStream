package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.ApplicantProfileService;

@CrossOrigin("*")
@RestController
public class ApplicantProfileController {

	@Autowired
 private JobApplicantRepository jobApplicantRepository;
	
	@Autowired
 private ApplicantProfileService applicantProfileService;
	
	
      @PostMapping("/applicant/createProfile/{applicantId}")
  public ResponseEntity<ApplicantProfile>createOrUpdateProfile(@RequestBody ApplicantProfile applicantProfile,
		  @PathVariable long applicantId){
	
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
	
	 @GetMapping("/applicant/getapplicantdetails/{profileid}")
	   public ResponseEntity<ApplicantProfile> getApplicantProfileById(@PathVariable int profileid) {
	       ApplicantProfile applicantProfile = applicantProfileService.viewApplicantById(profileid);
	       if (applicantProfile!= null) {
	           return ResponseEntity.ok(applicantProfile);
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }
	      
	 
	 @DeleteMapping("/applicant/deletedetails/{applicantId}")
	       public ResponseEntity<Void> deleteApplicantProfile(@PathVariable int applicantId) {
	           applicantProfileService.deleteApplicantById(applicantId);
	           return ResponseEntity.noContent().build();
	       }
		
}
	
	
	
	
	


