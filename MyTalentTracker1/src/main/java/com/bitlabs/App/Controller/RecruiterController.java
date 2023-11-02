package com.bitlabs.App.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.OtpVerification;
import com.bitlabs.App.Entity.SendOtp;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RecruiterService;

@RestController
public class RecruiterController {
	
	@Autowired
 private  RecruiterService recruiterService;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;
	
	 private Map<String, Boolean> otpVerificationMap = new HashMap<>();
	
	@PostMapping("/recruiter/send-otp")
 public ResponseEntity<String>sendOtp(@RequestBody SendOtp sendOtpRequest){
		
		String recruiterEmail=sendOtpRequest.getEmail();
		JobRecruiter jobRecruiter=recruiterService.findByEmailAddress(recruiterEmail);
		if(jobRecruiter==null) {
			String otp=otpService.generateOtp(recruiterEmail);
			emailService.sendOtpEmail(recruiterEmail,otp);
			 otpVerificationMap.put(recruiterEmail, true); // Mark OTP as verified
	            return ResponseEntity.ok("OTP sent to your email.");
			
		 }
        else {
        	 
        	return ResponseEntity.badRequest().body("Email is already  registered.");
        }
		
	}
	
	@PostMapping("/recruiter/verify-otp")
	 public ResponseEntity<String>verifyOtp(@RequestBody OtpVerification otpVerificationRequest){
		
		String otp=otpVerificationRequest.getOtp();
		String email=otpVerificationRequest.getEmail();
		if(otpService.validateOtp(otp,email)){
			 return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Incorrect OTP.");
        }
		
	}

}
