package com.bitlabs.App.Controller;

import java.time.LocalDate;
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
import com.bitlabs.App.Service.ApplyJobservice;
import com.bitlabs.App.Service.JobService;
import com.bitlabs.App.dto.AppliedApplicantInfo;

import jakarta.validation.Valid;

@RestController
public class JobController {
      
	@Autowired
	 private JobService jobService;
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	@Autowired
	private ApplyJobservice applyJobService;
	
	
	
/*	@PostMapping("/recruiter/post-job/{jobRecruiterid}")
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
		
	 }*/
	
	@PostMapping("/recruiter/post-job/{jobRecruiterid}")
	public ResponseEntity<String> postJob(@RequestBody @Valid Job job, 
	                                      @PathVariable Long jobRecruiterid,
	                                      @RequestParam(defaultValue = "30") int expirationPeriodInDays) {
	    JobRecruiter jobRecruiter = jobRecruiterRepository.findByRecruiterId(jobRecruiterid);

	    if (jobRecruiter != null) {
	        job.setJobRecruiter(jobRecruiter);
	        
	        // Set the posted date to the current date
	        job.setPostedDate(LocalDate.now());

	        // Set the expiration date based on the posted date and expiration period
	        job.setExpirationDate(job.getPostedDate().plusDays(expirationPeriodInDays));

	        jobService.postJob(job);

	        return ResponseEntity.status(HttpStatus.OK).body("Job posted successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job recruiter with id " + jobRecruiterid + " not found");
	    }
	}

	


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

 	    
	 @GetMapping("/recruiter/viewJobs/{jobRecruiterId}")
	    public ResponseEntity<List<Job>> getJobsByRecruiter(@PathVariable Long jobRecruiterId) {
	        // You can add validation here to ensure the jobRecruiterId is valid.

	        List<Job> jobs = jobService.getJobsByRecruiter(jobRecruiterId);
	        if (jobs.isEmpty()) {
	            // If no jobs are found for the specified recruiter, you can return a not found response.
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(jobs);
	        }
	    }

	
	 
	 
	 @GetMapping("/recruiter/searchJobByTitleLocationAndSkillName")
		public ResponseEntity<List<AppliedApplicantInfo>> searchJobByTitleLocationAndSkillName(
		    @RequestParam(value = "jobTitle", required = false) String jobTitle,
		    @RequestParam(value = "location", required = false) String location,
		    @RequestParam(value = "skillName", required = false) String skillName
		    ) {
	 
		    List<AppliedApplicantInfo> applicantInfo = applyJobService.getAppliedApplicantsByFilters(jobTitle,location,skillName);
		//    System.out.println(jobs);
	 
		    return ResponseEntity.ok(applicantInfo);
		}

	 
 	}


 
  


