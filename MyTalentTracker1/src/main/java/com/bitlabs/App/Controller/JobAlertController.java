package com.bitlabs.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlabs.App.Service.JobAlertService;
import com.bitlabs.App.dto.JobAlertDTO;
import jakarta.persistence.EntityNotFoundException;


@RestController
public class JobAlertController {
       @Autowired
	    private JobAlertService jobAlertService;

	    @PostMapping("/recruiter/{recruiterId}/send-alert/{scheduleInterviewId}")
	    public ResponseEntity<Void> sendAlert(
	            @PathVariable Long recruiterId,
	            @PathVariable Long scheduleInterviewId,
	            @RequestBody String message) {
	    	 try {
	    	        jobAlertService.sendAlert(recruiterId, scheduleInterviewId, message);
	    	        return new ResponseEntity<>(HttpStatus.CREATED);
	    	    } catch (EntityNotFoundException e) {
	    	        // Handle the exception, e.g., return a specific HTTP status code
	    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	    }
	    }
	    
	    @GetMapping("/applicant/{applicantId}/alerts")
	    public ResponseEntity<List<JobAlertDTO>> getJobAlertsByApplicant(@PathVariable Long applicantId) {
	        List<JobAlertDTO> jobAlerts = jobAlertService.getJobAlertsByApplicant(applicantId);
	        return new ResponseEntity<>(jobAlerts, HttpStatus.OK);
	    }

}
