package com.bitlabs.App.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Service.ApplyJobservice;
import com.bitlabs.App.dto.AppliedApplicantInfoDto;

@RestController
public class ApplyJobController {

	@Autowired
	private ApplyJobservice applyJobService;
	
   @PostMapping("/applicant/applyjob/{applicantId}/{jobId}")
	    public String saveJobForApplicant(
	            @PathVariable long applicantId,
	            @PathVariable long jobId
	    ) {
	       
		 return applyJobService.applicantApplyForJob(applicantId, jobId);
	          
	        
   }
   
   @GetMapping("/recruiter/{jobRecruiterId}/appliedapplicants")
   public ResponseEntity<List<AppliedApplicantInfoDto>> getAppliedApplicantsForRecruiter(@PathVariable long jobRecruiterId) {
	   List<AppliedApplicantInfoDto> appliedApplicants = applyJobService. getAppliedApplicants(jobRecruiterId);
       return ResponseEntity.ok(appliedApplicants);
    
   }
   
 
   @GetMapping("/applicant/applyJob/getAppliedJobs/{applicantId}")
   public ResponseEntity<List<Job>> getAppliedJobsForApplicant(@PathVariable long applicantId)
            {
       try {
    List<Job> AppliedJobs = applyJobService.getAppliedJobsForApplicant(applicantId);
           return ResponseEntity.ok(AppliedJobs);
} catch (Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
       }
   }
   
   
   @PostMapping("/recruiter/applyjob-update-status/{applyJobId}/{newStatus}")
   public ResponseEntity<String> updateApplicantStatus(@PathVariable Long applyJobId, @PathVariable String newStatus) {
       String updateMessage = applyJobService.updateApplicantStatus(applyJobId, newStatus);
       return ResponseEntity.ok(updateMessage);
   }
}
