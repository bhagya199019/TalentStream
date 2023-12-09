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
import com.bitlabs.App.Service.AlertService;

import jakarta.persistence.EntityNotFoundException;


@RestController
public class JobAlertController {
       @Autowired
	    private AlertService alertService;

	   
       @GetMapping("applicant/{applicantId}/getjob-alerts")
	    public ResponseEntity<List<Alert>> getJobAlertsByApplicantId(@PathVariable Long applicantId) {
	        List<Alert> alerts = alertService.getAlertsByApplicantId(applicantId);
	        return ResponseEntity.ok(alerts);
	    }

    
}
