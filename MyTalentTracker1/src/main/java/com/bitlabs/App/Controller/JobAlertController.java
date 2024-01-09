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

import com.bitlabs.App.Entity.Alert;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.AlertService;
import com.bitlabs.App.Service.ApplyJobservice;

import jakarta.persistence.EntityNotFoundException;


@RestController
public class JobAlertController {
       @Autowired
	    private AlertService alertService;
       
       @Autowired
       private ApplyJobservice applyJobService;
       
       @Autowired
       private JobApplicantRepository jobApplicantRepository;

	   
       @GetMapping("/applicants/{applicantId}/getjob-alerts")
	    public ResponseEntity<List<Alert>> getJobAlertsByApplicantId(@PathVariable Long applicantId) {
    	   
	        List<Alert> alerts = alertService.getAlertsByApplicantId(applicantId);
	     // Reset the job alert count when the user accesses the job alerts
	    	int resetCount=  applyJobService.resetJobAlertCount(applicantId);

	        return ResponseEntity.ok(alerts);
	    }


       
       @GetMapping("/applicants/{applicantId}/getAlert-count")
	    public ResponseEntity<Integer> getAlertCount(@PathVariable Long applicantId) {
    	   JobApplicant jobApplicant = jobApplicantRepository.findById(applicantId)
                   .orElseThrow(() -> new IllegalArgumentException("JobApplicant not found"));
	        int updatedAlertCount=jobApplicant.getAlertCount();
	        return ResponseEntity.ok(updatedAlertCount);
	    }
       
     
       @GetMapping("/applicants/{applicantId}/resetAlert-count")
	    public ResponseEntity<Integer> resetAlertCount(@PathVariable Long applicantId) {
	        int updatedAlertCount =applyJobService.resetJobAlertCount(applicantId);
	        return ResponseEntity.ok(updatedAlertCount);
	    }
}

