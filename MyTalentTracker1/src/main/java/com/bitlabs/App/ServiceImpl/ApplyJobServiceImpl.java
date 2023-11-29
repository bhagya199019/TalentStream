package com.bitlabs.App.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.ApplyJobRepository;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Repository.ScheduleInterviewRepository;
import com.bitlabs.App.Service.ApplyJobservice;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;
import com.bitlabs.App.dto.AppliedApplicantInfoDTO;

@Service
public class ApplyJobServiceImpl implements ApplyJobservice {
	
	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobApplicantRepository jobApplicantRepository;
	
	@Autowired
	private ScheduleInterviewRepository scheduleInterviewRepository;
	
public String applicantApplyForJob(long applicantId, long jobId) {
		JobApplicant jobApplicant=jobApplicantRepository.findById(applicantId);
		
		System.out.println("jobApplicant is"+jobApplicant);
		Job job=jobRepository.findById(jobId).orElse(null);
		System.out.println("job is"+job);
		
		if(jobApplicant==null && job==null) {
			return "Applicant Id or job Id not null";
		}
		
		else {
			if(!applyJobRepository.existsByJobApplicantAndJob(jobApplicant, job)) {
				ApplyJob applyJob = new  ApplyJob();
		           applyJob.setJobApplicant(jobApplicant);
		           applyJob.setJob(job);
		           applyJobRepository.save(applyJob);
		           return "Job Applied Successfully";
			}
			else {
	    		   return "Job has already been applied by the applicant";
	            }
		}
		
	}
	
	
	public List<AppliedApplicantInfoDTO> getAppliedApplicants(long jobRecruiterId) {
		List<AppliedApplicantInfo> appliedApplicants = applyJobRepository.findAppliedApplicantsInfo(jobRecruiterId);
		 
		List<AppliedApplicantInfoDTO> dtoList = new ArrayList<>();
		for (AppliedApplicantInfo appliedApplicantInfo : appliedApplicants) {
		    AppliedApplicantInfoDTO dto = mapToDTO(appliedApplicantInfo);
		    dtoList.add(dto);
		}
		 
		return dtoList;
		}
		 
		 
		 
		private AppliedApplicantInfoDTO mapToDTO(AppliedApplicantInfo appliedApplicantInfo) {
		    AppliedApplicantInfoDTO dto = new AppliedApplicantInfoDTO();
		    dto.setApplyjobid(appliedApplicantInfo.getApplyjobid());
		    dto.setName(appliedApplicantInfo.getName());
		    dto.setEmail(appliedApplicantInfo.getEmail());
		    dto.setMobilenumber(appliedApplicantInfo.getMobilenumber());
		    dto.setJobTitle(appliedApplicantInfo.getJobTitle());
		    dto.setApplicantStatus(appliedApplicantInfo.getApplicantStatus());
		    dto.setMinimumExperience(appliedApplicantInfo.getMinimumExperience());
		    dto.setSkillName(appliedApplicantInfo.getSkillName());
		    dto.setLocation(appliedApplicantInfo.getLocation());
		    dto.setLocation(appliedApplicantInfo.getLocation());
		    return dto;
		}
		 
	
	public List<Job> getAppliedJobsForApplicant(long applicantId) {
			List<Job> result = new ArrayList<>();      
		     
		      try {
		          List<ApplyJob> appliedJobs = applyJobRepository.findByJobApplicantId(applicantId);
	 
		          for (ApplyJob appliedJobs1 : appliedJobs) {
		              result.add(appliedJobs1.getJob());
		          }
	 
		      } catch(Exception e) {
		    	  e.printStackTrace();
		      }
	 
		      return result;
		  }

	
public String updateApplicantStatus(Long applyJobId, String newStatus) {
	    ApplyJob applyJob=applyJobRepository.findById(applyJobId)
	            .orElseThrow();
	 
	    applyJob.setApplicantStatus(newStatus);
	    applyJobRepository.save(applyJob);
	 
	    return "Applicant status updated to: " + newStatus;
	}


/* public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(
        long recruiterId, String applicantStatus) {
    return scheduleInterviewRepository.getApplicantJobInterviewInfoByRecruiterAndStatus(recruiterId, applicantStatus);
}
 

public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForApplicantAndStatus(long applicantId, String applicantStatus) {
    System.out.println("ApplicantId: " + applicantId);
    System.out.println("ApplicantStatus: " + applicantStatus);

    List<ApplicantJobInterviewDTO> result = scheduleInterviewRepository.getApplicantJobInterviewInfoByApplicantAndStatus(applicantId, applicantStatus);
    System.out.println("Query Result: " + result);

    return result;
}*/

public List<AppliedApplicantInfo>getApplicantInfoByStatus(long applicantId,String applicantStatus) {
    return applyJobRepository.findApplicantInfoByStatus(applicantId, applicantStatus);
}

public List<AppliedApplicantInfo> getApplicantInfoByApplyJobId(long applyJobId) {
    return applyJobRepository.findApplicantInfoByApplyJobId(applyJobId);
}


}


