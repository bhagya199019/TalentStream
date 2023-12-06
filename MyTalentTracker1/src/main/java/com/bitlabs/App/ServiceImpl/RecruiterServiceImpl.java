package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.RecruiterService;


@Service
public class RecruiterServiceImpl implements RecruiterService{
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public JobRecruiter findByEmailAddress(String recruiterEmail) {
		return jobRecruiterRepository.findByEmail(recruiterEmail);
	}
	
	
	public ResponseEntity<String> saveRecruiter(JobRecruiter jobRecruiter) {
	    try {
	        if (jobRecruiterRepository.existsByEmail(jobRecruiter.getEmail())) {
	            return ResponseEntity.badRequest().body("Email already registered");
	        }

	        jobRecruiter.setPassword(passwordEncoder.encode(jobRecruiter.getPassword()));
	        jobRecruiterRepository.save(jobRecruiter);

	        return ResponseEntity.ok("Recruiter registered successfully");
	    } catch (Exception e) {
	        // Log the exception or handle it as needed
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
	    }
	}




}
