package com.bitlabs.App.ServiceImpl;

import java.util.List;

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
	private JobApplicantRepository jobApplicantRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	
	public JobApplicant findByEmailAddress(String userEmail) {
		return jobApplicantRepository.findByEmail(userEmail);
		
	}

	
	public ResponseEntity<String> saveApplicant(JobApplicant jobApplicant){
		
		if(jobApplicantRepository.existsByEmail(jobApplicant.getEmail())){
		return ResponseEntity.badRequest().body("Email already registered");
		}
		
		
	jobApplicant.setPassword(passwordEncoder.encode(jobApplicant.getPassword()));
		
			jobApplicantRepository.save(jobApplicant);
			 return ResponseEntity.ok("Applicant registered successfully");
			
	}
	
		public List<JobApplicant> getAllApplicants(){
			
			return jobApplicantRepository.findAll();
			
			
		}
	
		public JobApplicant viewApplicantById(Long applicantId) {
			
			return jobApplicantRepository.getJobApplicantById(applicantId);
		}
}
