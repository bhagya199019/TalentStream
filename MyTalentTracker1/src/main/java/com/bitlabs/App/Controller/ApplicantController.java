package com.bitlabs.App.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.OtpVerification;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RegisterApplicantService;




@RestController
public class ApplicantController {
	
	
	

	@Autowired
    private RegisterApplicantService registerApplicantService;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;
	
	
    
	
	 
	
private Map<String, Boolean> otpVerificationMap = new HashMap<>();

    @PostMapping("/applicant/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody JobApplicant  applicantRequest) {
        String userEmail = applicantRequest.getEmail();
        JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
        if (jobApplicant == null) {     
            String otp = otpService.generateOtp(userEmail);
         	            emailService.sendOtpEmail(userEmail, otp);
 	            otpVerificationMap.put(userEmail, true);
 	            return ResponseEntity.ok("OTP sent to your email.");
        }

        else {
        	 return ResponseEntity.badRequest().body("Email is already  registered.");
        }
    }
      
    @PostMapping("/applicant/verify-otp")
  public ResponseEntity<String> verifyOtp( @RequestBody  OtpVerification verificationRequest) {
        String otp=verificationRequest.getOtp();
        String email=verificationRequest.getEmail();
      //  System.out.println(otp+email);

        if (otpService.validateOtp(email, otp)) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Incorrect OTP.");
        }

    }
    
  
    @PostMapping("/applicant/register-jobApplicant")
    public ResponseEntity<String> register(@RequestBody JobApplicant jobApplicant) {
       return registerApplicantService.saveApplicant(jobApplicant);
    }

 
  

    
   
    
}



    

    

