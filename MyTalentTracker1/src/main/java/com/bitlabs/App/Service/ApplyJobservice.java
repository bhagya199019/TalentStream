package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;
import com.bitlabs.App.dto.AppliedApplicantInfoDTO;

public interface ApplyJobservice {
	
	public String applicantApplyForJob(long applicantId, long jobId);
	
	public List<AppliedApplicantInfoDTO> getAppliedApplicants(long jobRecruiterId);
	
	public List<Job> getAppliedJobsForApplicant(long applicantId);
	
	public String updateApplicantStatus(Long applyJobId, String newStatus);
	
/*	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(
	        long recruiterId, String applicantStatus);
	
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForApplicantAndStatus(
	        long applicantId, String applicantStatus);
	        */
	
	
	public List<AppliedApplicantInfo> getApplicantsInfoByStatus(long applicantId,String applicantStatus);
}
