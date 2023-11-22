package com.bitlabs.App.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Entity.NewPasswordRequest;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RecruiterService;
import com.bitlabs.App.dto.OtpVerificationDTO;
import com.bitlabs.App.dto.SendOtpDTO;

@RestController
public class RecruiterController {
	
	@Autowired
 private  RecruiterService recruiterService;
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;;
	
	 private Map<String, Boolean> otpVerificationMap = new HashMap<>();
	
	@PostMapping("/recruiter/send-otp")
 public ResponseEntity<String>sendOtp(@RequestBody SendOtpDTO sendOtpRequest){
		
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
	 public ResponseEntity<String>verifyOtp(@RequestBody OtpVerificationDTO otpVerificationRequest){
		
		String otp=otpVerificationRequest.getOtp();
		String email=otpVerificationRequest.getEmail();
		if(otpService.validateOtp(email,otp)){
			 return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Incorrect OTP.");
        }
		
	}
	
	@PostMapping("/recruiter/register-jobRecruiter")
    public ResponseEntity<String> registerjobRecruiter(@RequestBody JobRecruiter jobRecruiter) {
       return recruiterService.saveRecruiter(jobRecruiter);
    }
	
	
	@PostMapping("/recruiter/forgotPassword/send-Otp")
	  public ResponseEntity<String> ForgotPasswordSendOtp(@RequestBody SendOtpDTO  request){
		  String recruiterEmail=request.getEmail();
		  JobRecruiter jobRecruiter=recruiterService.findByEmailAddress(recruiterEmail);
		
		      if(jobRecruiter!=null) {
			
			     String otp=otpService.generateOtp(recruiterEmail);
			     emailService.sendOtpEmail(recruiterEmail, otp);
	             otpVerificationMap.put(recruiterEmail,true);
			
			return ResponseEntity.ok("OTP sent to your Email");
			}
		
		else {
			
			return ResponseEntity.badRequest().body("Recruiter not registered");
		}
		

	}
	   
	  @PostMapping("/recruiter/reset-password")
 public ResponseEntity<String> resetPassword(@RequestBody NewPasswordRequest request) {
	         String newPassword = request.getNewPassword();
	         String confirmPassword = request.getConfirmPassword();
	         String email = request.getEmail();

	         if (email == null) {
	             return ResponseEntity.badRequest().body("Email not found");
	         }

	         JobRecruiter jobRecruiter = recruiterService.findByEmailAddress(email);

	         if (jobRecruiter==null) {
	             return ResponseEntity.badRequest().body("User not found");
	         }

	         if (!newPassword.equals(confirmPassword)) {
	             return ResponseEntity.badRequest().body("Passwords do not match");
	         }

	         // Encode the new password before saving
	         String encodedPassword = passwordEncoder.encode(newPassword);
	         jobRecruiter.setPassword(encodedPassword);

	         // Save the updated password
	         jobRecruiterRepository.save(jobRecruiter);

	         return ResponseEntity.ok("Password reset done successfully");
	     }

	 
	   @PostMapping("/recruiter/login")
 public ResponseEntity<String> loginRecruiter(@RequestBody Login request) throws Exception {
		    String email = request.getEmail();
		    String password = request.getPassword();
		  JobRecruiter jobRecruiter=jobRecruiterRepository.findByEmail(email);;

		    if (jobRecruiter == null) {
		        return ResponseEntity.badRequest().body("User not found");
		    } else if (jobRecruiter!= null && !passwordEncoder.matches(password, jobRecruiter.getPassword())) {
		        return ResponseEntity.badRequest().body("Please enter the correct password");
		    } else {
		        return ResponseEntity.ok("Login successfully");
		    }
		}
	

}
