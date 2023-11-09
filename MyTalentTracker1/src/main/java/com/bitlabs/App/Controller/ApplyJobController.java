package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.bitlabs.App.Service.ApplyJobservice;

public class ApplyJobController {

	@Autowired
	private ApplyJobservice applyJobService;
	
		
	
	 @PostMapping("/applicants/applyjob/{applicantId}/{jobId}")
	    public String saveJobForApplicant(
	            @PathVariable long applicantId,
	            @PathVariable long jobId
	    ) {
	       
		 return applyJobService.applicantApplyForJob(applicantId, jobId);
	          
	        
   }
	    
	
}
