package com.bitlabs.App.Controller;

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
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.JobService;


import jakarta.validation.Valid;

@RestController
public class JobController {
      
	@Autowired
	 private JobService jobService;
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	
	
	@PostMapping("/recruiter/post-job/{jobRecruiterid}")
 public ResponseEntity<String> postJob(@RequestBody  @Valid Job job,@PathVariable Long jobRecruiterid){
		JobRecruiter jobRecruiter=jobRecruiterRepository.findByRecruiterId(jobRecruiterid);
		
		if(jobRecruiter!=null) {
			job.setJobRecruiter(jobRecruiter);
			jobService.postJob(job);
			
		return ResponseEntity.status(HttpStatus.OK).body("job posted successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job recruiter with id"+jobRecruiterid+"not found");
		}
		
	 }
	

/*	
 	@GetMapping("/recruiters/search")
	public ResponseEntity<List<Job>> searchJobsByKeywordAndjobId(
	    @RequestParam("keyword") String keyword,@RequestParam(value = "id", required = false) Long jobId)
	     {
	    List<Job> jobs = jobService.searchJobsByKeywordAndjobId(keyword,jobId);
	    System.out.println(jobs);
	 //   List<JobDTO> jobDTOs = JobMapper.mapToDTOs(jobs);
	    return ResponseEntity.ok(jobs);
	}
  
 	
 	@GetMapping("/recruiter/search")
public ResponseEntity<List<Job>> searchJobsByKeyword(
    @RequestParam(value = "keyword", required = false) String keyword,
    @RequestParam(value = "id", required = false) Long id,
    @RequestParam(value = "minExperience", required = false) Integer minExperience,
    @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
    @RequestParam(value = "maxSalary", required = false) Double maxSalary) {

    List<Job> jobs = jobService.searchJobsByKeyword(keyword, id, minExperience, maxExperience, maxSalary);
    System.out.println(jobs);

    return ResponseEntity.ok(jobs);
} */
	
	@GetMapping("/recruiter/search")
	public ResponseEntity<List<Job>> searchJobsByFilters(
	    @RequestParam(value = "jobTitle", required = false) String jobTitle,
	    @RequestParam(value = "id", required = false) Long id,
	    @RequestParam(value = "minExperience", required = false) Integer minExperience,
	    @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
	    @RequestParam(value = "maxSalary", required = false) Double maxSalary,
	    @RequestParam(value = "location", required = false) String location,
	    @RequestParam(value = "employeeType", required = false) String employeeType,
	    @RequestParam(value = "industryType", required = false) String industryType,
	    @RequestParam(value = "minimumQualification", required = false) String minimumQualification,
	    @RequestParam(value = "specialization", required = false) String specialization) {
 
	    List<Job> jobs = jobService.searchJobsByFilters(
	        jobTitle, id, minExperience, maxExperience, maxSalary, location, employeeType, industryType,
	        minimumQualification, specialization);
	    System.out.println(jobs);
 
	    return ResponseEntity.ok(jobs);
	}

 	    
 	}

  
 
  


