package com.bitlabs.App.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Entity.NewPasswordRequest;
import com.bitlabs.App.Entity.OtpVerification;
import com.bitlabs.App.Entity.SendOtp;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.bitlabs.App.Service.ApplicantService;




@RestController
public class ApplicantController {
	
	@Autowired
    private ApplicantService registerApplicantService;
	
	@Autowired
	private JobApplicantRepository jobApplicantRepository;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Map<String, Boolean> otpVerificationMap = new HashMap<>();

    @PostMapping("/applicant/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtp  request) {
        String userEmail = request.getEmail();
        
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

 
  
     @PostMapping("/applicant/forgotPassword/send-Otp")
  public ResponseEntity<String> ForgotPasswordSendOtp(@RequestBody SendOtp  request){
	  String userEmail=request.getEmail();
	  JobApplicant jobApplicant=registerApplicantService.findByEmailAddress(userEmail);
	
	      if(jobApplicant!=null) {
		
		     String otp=otpService.generateOtp(userEmail);
		     emailService.sendOtpEmail(userEmail, otp);
             otpVerificationMap.put(userEmail,true);
		
		return ResponseEntity.ok("OTP sent to your Email");
		}
	
	else {
		
		return ResponseEntity.badRequest().body("Applicant not registered");
	}
	

}
   
     @PostMapping("/applicant/reset-password")
     public ResponseEntity<String> resetPassword(@RequestBody NewPasswordRequest request) {
         String newPassword = request.getNewPassword();
         String confirmPassword = request.getConfirmPassword();
         String email = request.getEmail();

         if (email == null) {
             return ResponseEntity.badRequest().body("Email not found");
         }

         JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(email);

         if (jobApplicant == null) {
             return ResponseEntity.badRequest().body("User not found");
         }

         if (!newPassword.equals(confirmPassword)) {
             return ResponseEntity.badRequest().body("Passwords do not match");
         }

         // Encode the new password before saving
         String encodedPassword = passwordEncoder.encode(newPassword);
         jobApplicant.setPassword(encodedPassword);

         // Save the updated password
         jobApplicantRepository.save(jobApplicant);

         return ResponseEntity.ok("Password reset done successfully");
     }

 
   @PostMapping("/applicant/login")
   public ResponseEntity<String> loginApplicant(@RequestBody Login request) throws Exception {
	    String email = request.getEmail();
	    String password = request.getPassword();
	  JobApplicant jobApplicant=jobApplicantRepository.findByEmail(email);;

	    if (jobApplicant == null) {
	        return ResponseEntity.badRequest().body("User not found");
	    } else if (jobApplicant != null && !passwordEncoder.matches(password, jobApplicant.getPassword())) {
	        return ResponseEntity.badRequest().body("Please enter the correct password");
	    } else {
	        return ResponseEntity.ok("Login successfully");
	    }
	}

   @GetMapping("/applicant/viewAllApplicants")
  public ResponseEntity<List<JobApplicant>> viewApplicants(){
	   
	   List <JobApplicant> jobApplicants=registerApplicantService.getAllApplicants();
	   
	   return ResponseEntity.ok(jobApplicants);
   
   }
   
   @PostMapping("/applicant/signOut")
   public ResponseEntity<Void> signOut(HttpServletRequest request) {
       System.out.println("Checking");

       // Get the current session and invalidate it
       HttpSession session = request.getSession(false);
       if (session != null) {
           session.invalidate();
       }

       return ResponseEntity.noContent().build();
   }

}   

    

