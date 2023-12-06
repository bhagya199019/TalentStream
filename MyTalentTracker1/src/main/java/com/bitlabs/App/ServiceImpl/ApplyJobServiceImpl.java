package com.bitlabs.App.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.ApplyJobStatusHistory;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.ApplyJobRepository;
import com.bitlabs.App.Repository.ApplyJobStatusHistoryRepository;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Repository.ScheduleInterviewRepository;
import com.bitlabs.App.Service.ApplyJobservice;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;
import com.bitlabs.App.dto.AppliedApplicantInfoDTO;

import jakarta.persistence.EntityNotFoundException;

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
	
	
	 @Autowired
	private ApplyJobStatusHistoryRepository applyJobStatusHistoryRepository;
	
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
        ApplyJob applyJob = applyJobRepository.findById(applyJobId)
                .orElseThrow();

        String oldStatus = applyJob.getApplicantStatus();
        applyJob.setApplicantStatus(newStatus);
        applyJobRepository.save(applyJob);

        saveStatusHistory(applyJob, oldStatus, newStatus);

        return "Applicant status updated to: " + newStatus;
    }


	private void saveStatusHistory(ApplyJob applyJob, String oldStatus, String newStatus) {
        if (applyJob.getStatusHistory() == null) {
            applyJob.setStatusHistory(new ArrayList<>());
        }

        ApplyJobStatusHistory statusChange = new ApplyJobStatusHistory();
        statusChange.setApplyJob(applyJob);
        statusChange.setApplicantStatus(newStatus);
        statusChange.setChangeDate(LocalDateTime.now());

        applyJob.getStatusHistory().add(statusChange);
        applyJobStatusHistoryRepository.save(statusChange);
    }
	
	
	 public List<ApplyJobStatusHistory> getApplicantStatusHistory(Long applyJobId) {
	        ApplyJob applyJob = applyJobRepository.findById(applyJobId)
	                .orElseThrow(() -> new EntityNotFoundException("ApplyJob with id " + applyJobId + " not found."));

	        return applyJob.getStatusHistory();
	    }
	
public List<AppliedApplicantInfo>getApplicantInfoByStatus(long applicantId,String applicantStatus) {
    return applyJobRepository.findApplicantInfoByStatus(applicantId, applicantStatus);
}

public List<AppliedApplicantInfo> getApplicantInfoByApplyJobId(long applyJobId) {
    return applyJobRepository.findApplicantInfoByApplyJobId(applyJobId);
}

public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(
        long recruiterId, String applicantStatus) {
    return scheduleInterviewRepository.getApplicantJobInterviewInfoByRecruiterAndStatus(recruiterId, applicantStatus);
}
 

}


