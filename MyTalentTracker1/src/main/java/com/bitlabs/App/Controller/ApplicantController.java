package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.dto.NewPasswordRequestDTO;
import com.bitlabs.App.dto.OtpVerificationDTO;
import com.bitlabs.App.dto.ResendOtpDTO;
import com.bitlabs.App.dto.SendOtpDTO;

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
	
	
    @PostMapping("/applicant/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtpDTO  request) {
        String userEmail = request.getEmail();
        
        JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
        if (jobApplicant == null) {     
            String otp = otpService.generateOtp(userEmail);
         	            emailService.sendOtpEmail(userEmail, otp);
 	          //  otpVerificationMap.put(userEmail, true);
 	            return ResponseEntity.ok("OTP sent to your email.");
        }

        else {
        	 return ResponseEntity.badRequest().body("Email is already registered.");
        }
    }
      
    @PostMapping("/applicant/verify-otp")
  public ResponseEntity<String> verifyOtp( @RequestBody  OtpVerificationDTO verificationRequest) {
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
  public ResponseEntity<String> ForgotPasswordSendOtp(@RequestBody SendOtpDTO  request){
	  String userEmail=request.getEmail();
	  JobApplicant jobApplicant=registerApplicantService.findByEmailAddress(userEmail);
	
	      if(jobApplicant!=null) {
		
		     String otp=otpService.generateOtp(userEmail);
		     emailService.sendOtpEmail(userEmail, otp);
         //    otpVerificationMap.put(userEmail,true);
		
		return ResponseEntity.ok("OTP sent to your Email");
		}
	
	else {
		
		return ResponseEntity.badRequest().body(" Applicant email not registered");
	}
	
	      
	     
}
   
     @PostMapping("/applicant/reset-password/{email}")
	    public ResponseEntity<String> setNewPassword(@RequestBody NewPasswordRequestDTO request, @PathVariable String email) {
	        String newPassword = request.getNewPassword();
	        String confirmedPassword = request.getConfirmPassword();
	        
	        JobApplicant applicant = registerApplicantService.findByEmailAddress(email);
	        if (applicant == null) {
	            return ResponseEntity.badRequest().body("User not found.");
	        }
	        if(!(newPassword.equals(confirmedPassword)))
	        {
	        	return ResponseEntity.badRequest().body("password not match");
	        }
	         // Encode the new password before saving
	         String encodedPassword = passwordEncoder.encode(newPassword);
	         applicant.setPassword(encodedPassword);
	
	         // Save the updated password
	         jobApplicantRepository.save(applicant);
	        return ResponseEntity.ok("Password reset was done successfully");
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

   

   
   @PostMapping("/applicant/resend-otp")
   public ResponseEntity<String> resendOtp(@RequestBody ResendOtpDTO request) {
       String userEmail = request.getEmail();
       
       JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
       if (jobApplicant == null) {
           if (otpService.isOtpExpired(userEmail) && otpService.canResendOtp(userEmail)) {
               String otp = otpService.generateOtp(userEmail);
               emailService.sendOtpEmail(userEmail, otp);
               return ResponseEntity.ok("OTP resent to your email.");
           } else {
               return ResponseEntity.badRequest().body("Resend OTP is not allowed at this time.");
           }
       } else {
           return ResponseEntity.badRequest().body("Email is already registered.");
       }
   }


   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /*   @PostMapping("/applicant/signOut")
   public ResponseEntity<Void> signOut(HttpServletRequest request) {
	   
       System.out.println("Checking");

       // Get the current session and invalidate it
       HttpSession session = request.getSession(false);
       if (session != null) {
           session.invalidate();
       }

       return ResponseEntity.noContent().build();
   }*/
}
