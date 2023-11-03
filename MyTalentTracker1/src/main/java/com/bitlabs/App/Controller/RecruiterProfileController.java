package com.bitlabs.App.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.RecruiterProfile;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.RecruiterProfileService;

@RestController
public class RecruiterProfileController {
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository; 
	@Autowired
	private RecruiterProfileService recruiterProfileService;
      
	 @PostMapping("/recruiter/create-recruiter-profile/{jobRecruiterId}")
	public ResponseEntity<String>createRecruiterProfile(@RequestBody RecruiterProfile recruiterProfile,@PathVariable Long jobRecruiterId){
		JobRecruiter jobRecruiter=jobRecruiterRepository.findByRecruiterId(jobRecruiterId);
		
		if(jobRecruiter!=null) {
			recruiterProfile.setJobRecruiter(jobRecruiter);
			recruiterProfileService.saveRecruiterProfile(recruiterProfile);
			return ResponseEntity.status(HttpStatus.OK).body("Recruiter company profile saved successfully");
			}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("JobRecruiter id "+jobRecruiterId+"not found");
	}
	 
	 @GetMapping("/recruiter/getRecruiterProfile/{id}")
	    public ResponseEntity<RecruiterProfile> getRecruiterProfileById(@PathVariable Long id) {
	        Optional<RecruiterProfile> companyProfile = recruiterProfileService.getRecruiterProfileById(id);
	        return companyProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
}
