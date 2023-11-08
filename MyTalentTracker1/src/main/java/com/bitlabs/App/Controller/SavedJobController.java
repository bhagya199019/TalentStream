package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Service.SavedJobService;

@RestController
public class SavedJobController {

  @Autowired
 private SavedJobService savedJobService; 

	 @PostMapping("/applicant/saveJob/{applicantId}/{jobId}")
  public ResponseEntity<String>saveJobByApplicant(@PathVariable long applicantId,@PathVariable long jobId){
		 
		 try {
			 	 savedJobService.saveJobForApplicant(applicantId,jobId);
			 	 return ResponseEntity.ok("job saved successfully");
	 }
		 catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			 
		 }
}
}