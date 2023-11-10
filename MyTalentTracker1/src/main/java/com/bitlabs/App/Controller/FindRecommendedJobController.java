package com.bitlabs.App.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Service.FindRecommendedJobService;

@RestController
public class FindRecommendedJobController {
	
	@Autowired
	private FindRecommendedJobService findRecommendedJobservice;
	
	
	@GetMapping("/applicant/findRecommendedJobsBySkills/{applicantProfileId}")
 public List<Job>recommendedJobToApplicant(@PathVariable String applicantProfileId){
		
		int applicantProfileid = Integer.parseInt(applicantProfileId);
		return findRecommendedJobservice.findJobByMatchingSkills(applicantProfileid);
		
	}

}
