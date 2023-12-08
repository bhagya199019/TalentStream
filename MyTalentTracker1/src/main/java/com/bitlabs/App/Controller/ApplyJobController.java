package com.bitlabs.App.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.Alert;
import com.bitlabs.App.Entity.ApplyJobStatusHistory;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.ScheduleInterview;
import com.bitlabs.App.Service.AlertService;
import com.bitlabs.App.Service.ApplyJobservice;
import com.bitlabs.App.Service.ScheduleInterviewService;
import com.bitlabs.App.dto.ApplicantDetailsDTO;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;
import com.bitlabs.App.dto.AppliedApplicantInfo;
import com.bitlabs.App.dto.AppliedApplicantInfoDTO;
import com.bitlabs.App.dto.ApplyJobStatusHistoryDTO;
import com.bitlabs.App.dto.InterviewFeedbackUpdateDTO;

@RestController
public class ApplyJobController {

	@Autowired
	private ApplyJobservice applyJobService;
	
	@Autowired
	private ScheduleInterviewService scheduleInterviewService;
	
	@Autowired
	private AlertService alertService;
	
   @PostMapping("/applicant/applyjob/{applicantId}/{jobId}")
	    public String saveJobForApplicant(
	            @PathVariable long applicantId,
	            @PathVariable long jobId
	    ) {
	       
		 return applyJobService.applicantApplyForJob(applicantId, jobId);
	          
	        
   }
   
   @GetMapping("/recruiter/{jobRecruiterId}/appliedapplicants")
   public ResponseEntity<List<AppliedApplicantInfoDTO>> getAppliedApplicantsForRecruiter(@PathVariable long jobRecruiterId) {
	   List<AppliedApplicantInfoDTO> appliedApplicants = applyJobService. getAppliedApplicants(jobRecruiterId);
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
   
    
     
   @GetMapping("/applicant/checkstatus/{applyJobId}")
   public ResponseEntity<List<ApplyJobStatusHistoryDTO>> getApplicantStatus(@PathVariable Long applyJobId) {
       List<ApplyJobStatusHistoryDTO> historyDTOList = applyJobService.getApplicantStatus(applyJobId);
       return ResponseEntity.ok(historyDTOList);
   }

   @PostMapping("/recruiters/scheduleInterview/{applyJobId}")
   public ResponseEntity<Void> createScheduleInterview(
           @PathVariable Long applyJobId,
           @RequestBody ScheduleInterview scheduleInterview) {
  	 ScheduleInterview isCreated = scheduleInterviewService.createScheduleInterview(applyJobId,scheduleInterview);
       if (isCreated!=null) {
           return new ResponseEntity<>(HttpStatus.CREATED);
       } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }
   
   
   @PostMapping("/recruiter/update-interview-feedback/{interviewId}")
   public ResponseEntity<String> updateInterviewFeedback(@PathVariable Long interviewId,@RequestBody InterviewFeedbackUpdateDTO request) {

       String updateFeedback = scheduleInterviewService.updateInterviewFeedback(interviewId, request.getFeedback());
       return ResponseEntity.ok(updateFeedback);
   }
   
   

   @GetMapping("/applicant/check-interview-feedback/{applicantId}")
   public List<InterviewFeedbackUpdateDTO> getFeedbackByApplicantId(
		   @PathVariable long applicantId) {
       return scheduleInterviewService.getFeedbackByApplicantId(applicantId);
   }


	 @GetMapping("/applicant/applyjob-status-history/{applyJobId}")
	    public ResponseEntity<List<ApplyJobStatusHistory>> getApplicantStatusHistory(@PathVariable Long applyJobId) {
	        List<ApplyJobStatusHistory> historyList = applyJobService.getApplicantStatusHistory(applyJobId);
	        return ResponseEntity.ok(historyList);
	    }
	 
	 
	 
	 @GetMapping("recruiter/applicant-details/{jobId}/{applicantStatus}")
	    public ResponseEntity<List<ApplicantDetailsDTO>> getApplicantDetails(
	            @PathVariable Long jobId,
	            @PathVariable String applicantStatus) {
	        List<ApplicantDetailsDTO> applicantDetails = applyJobService.getJobApplicantsDetailsByStatus(jobId, applicantStatus);
	        return ResponseEntity.ok(applicantDetails);
	    }
	 
	 @GetMapping("applicant/{applicantId}/getjob-alerts")
	    public ResponseEntity<List<Alert>> getJobAlertsByApplicantId(@PathVariable Long applicantId) {
	        List<Alert> alerts = alertService.getAlertsByApplicantId(applicantId);
	        return ResponseEntity.ok(alerts);
	    }
	 
}
