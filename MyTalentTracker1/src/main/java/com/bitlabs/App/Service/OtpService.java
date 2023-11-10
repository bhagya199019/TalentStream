package com.bitlabs.App.Service;



public interface OtpService {
   
	
	    String generateOtp(String userEmail);
	    
	    boolean validateOtp(String userEmail, String enteredOtp);
	    
	 //   boolean isEmailAssociatedWithOtp(String userEmail);
	    
	    boolean isOtpExpired(String userEmail);
	}

