package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.AuthenticationResponse;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Response.ResponseHandler;
import com.bitlabs.App.Service.EmailService;

import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.ServiceImpl.JwtUtilService;
import com.bitlabs.App.ServiceImpl.MyUserDetailsService;
import com.bitlabs.App.dto.NewPasswordRequestDTO;
import com.bitlabs.App.dto.OtpVerificationDTO;
import com.bitlabs.App.dto.ResendOtpDTO;
import com.bitlabs.App.dto.SendOtpDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.bitlabs.App.Service.ApplicantService;




@RestController
// @CrossOrigin(origins = "http://localhost:3000")
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
	
	 @Autowired
		private AuthenticationManager authenticationManager;
	 
	 @Autowired
  MyUserDetailsService myUserDetailsService;
	 
	 @Autowired
	 JwtUtilService jwtTokenUtil;
	
	
    @PostMapping("/applicant/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtpDTO  request) {
        String userEmail = request.getEmail();
        
        JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
        if (jobApplicant == null) {     
            String otp = otpService.generateOtp(userEmail);
         	            emailService.sendOtpEmail(userEmail, otp);
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
 
 /*  @PostMapping("/applicant/login")
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
*/
     
     
     @PostMapping("/applicants/login")
     public ResponseEntity<Object> loginApplicant(@RequestBody Login loginRequest) throws Exception {
    	 JobApplicant applicant = registerApplicantService.login(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println(loginRequest.getEmail());
        System.out.println(applicant.getEmail());
        if (applicant!=null) {
        	return createAuthenticationToken(loginRequest, applicant);
        } else {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Object> createAuthenticationToken(Login loginRequest,  JobApplicant applicant ) throws Exception {
			    	try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
    	 UserDetails userDetails = myUserDetailsService.loadUserByUsername(applicant.getEmail());
    	System.out.println(userDetails.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println(jwt);
		return ResponseHandler.generateResponse("Login successfully"+userDetails.getAuthorities(), HttpStatus.OK, new AuthenticationResponse(jwt),applicant.getEmail(),applicant.getName(),applicant.getId());
	}

   

   
   @PostMapping("/applicant/resend-otp")
   public ResponseEntity<String> resendOtp(@RequestBody ResendOtpDTO request) {
	    String userEmail = request.getEmail();

	    JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
	    if (jobApplicant == null) {
	        if (otpService.canResendOtp(userEmail)) {
	            String otp = otpService.generateOtp(userEmail);
	            try {
	                emailService.sendOtpEmail(userEmail, otp);
	                return ResponseEntity.ok("OTP resent to your email.");
	            } catch (Exception e) {
	                return ResponseEntity.badRequest().body("Failed to send OTP. Please try again.");
	            }
	        } else {
	            return ResponseEntity.badRequest().body("Resend OTP is not allowed at this time.");
	        }
	    } else {
	        return ResponseEntity.badRequest().body("Email is already registered.");
	    }
	}



   @PostMapping("/applicantLogOut")
   public ResponseEntity<Void> signOut(@AuthenticationPrincipal JobApplicant user) {
   	System.out.println("checking");
       SecurityContextHolder.clearContext();
       return ResponseEntity.noContent().build();
   }
}
   
   
 /*  @PostMapping("/applicant/resend-otp")
   public ResponseEntity<String> resendOtp(@RequestBody ResendOtpDTO request) {
       String userEmail = request.getEmail();

       JobApplicant jobApplicant = registerApplicantService.findByEmailAddress(userEmail);
       if (jobApplicant == null) {
           OtpService.OtpData otpData = otpService.getOtpData(userEmail);

           if (otpData == null || !otpData.isExpired()) {
               String newOtp = otpService.generateOtp(userEmail);
               otpService.markAsResent(userEmail); // Mark the new OTP as resent
               emailService.sendOtpEmail(userEmail, newOtp);
               return ResponseEntity.ok("OTP resent to your email.");
           } else {
               return ResponseEntity.badRequest().body("Resend OTP is not allowed at this time.");
           }
       } else {
           return ResponseEntity.badRequest().body("Email is already registered.");
       }
   }
*/
   
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

