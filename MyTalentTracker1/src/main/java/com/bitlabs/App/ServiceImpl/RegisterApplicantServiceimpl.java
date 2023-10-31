package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.RegisterApplicantService;


@Service
public class RegisterApplicantServiceimpl implements RegisterApplicantService{
	
	@Autowired
	private JobApplicantRepository jobApplicantrepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	
	public JobApplicant findByEmailAddress(String userEmail) {
		return jobApplicantrepository.findByEmail(userEmail);
		
	}

	
	public ResponseEntity<String> saveApplicant(JobApplicant jobApplicant){
		
		if(jobApplicantrepository.existsByEmail(jobApplicant.getEmail())){
		return ResponseEntity.badRequest().body("Email already registered");
		}
		
		 jobApplicant.setPassword(passwordEncoder.encode(jobApplicant.getPassword()));
			jobApplicantrepository.save(jobApplicant);
			 return ResponseEntity.ok("Applicant registered successfully");
			
	}
	
}
