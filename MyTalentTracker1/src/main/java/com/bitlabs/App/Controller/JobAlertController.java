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
import com.bitlabs.App.Service.AlertService;
import com.bitlabs.App.dto.JobAlertDTO;
import jakarta.persistence.EntityNotFoundException;


@RestController
public class JobAlertController {
       @Autowired
	    private AlertService jobAlertService;

	   
	    
/*	    @GetMapping("/applicant/{applicantId}/alerts")
	    public ResponseEntity<List<JobAlertDTO>> getJobAlertsByApplicant(@PathVariable Long applicantId) {
	        List<JobAlertDTO> jobAlerts = jobAlertService.viewJobAlertsByApplicant(applicantId);
	        return new ResponseEntity<>(jobAlerts, HttpStatus.OK);
	    }
	    */

}
