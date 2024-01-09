package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.ApplyJobStatusHistory;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.dto.ApplicantDetailsDTO;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;
import com.bitlabs.App.dto.AppliedApplicantInfo;
import com.bitlabs.App.dto.AppliedApplicantInfoDTO;
import com.bitlabs.App.dto.ApplyJobStatusHistoryDTO;

public interface ApplyJobservice {
	
	public String applicantApplyForJob(long applicantId, long jobId);
	
	public List<AppliedApplicantInfoDTO> getAppliedApplicants(long jobRecruiterId);
	
	public List<Job> getAppliedJobsForApplicant(long applicantId);
	
	public String updateApplicantStatus(Long applyJobId, String newStatus);
	
	
	public List<ApplyJobStatusHistory> getApplicantStatusHistory(Long applyJobId);
	
	public List<ApplyJobStatusHistoryDTO> getApplicantStatus(Long applyJobId);
	
	
/*	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(
	        long recruiterId, String applicantStatus);
	
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForApplicantAndStatus(
	        long applicantId, String applicantStatus);
	        */
	
	public List<AppliedApplicantInfo> getAppliedApplicantsByFilters(String jobTitle, String location, String skillName);
	public List<AppliedApplicantInfo> getApplicantInfoByStatus(long applicantId,String applicantStatus);
	
	public List<AppliedApplicantInfo> getApplicantInfoByApplyJobId(long applyJobId);
	
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(
	        long recruiterId, String applicantStatus);
	
//	public List<ApplicantDetailsDTO> getJobApplicantsDetailsByStatus(Long jobId, String applicantStatus);
	
	public int incrementJobAlertCount(Long applicantId);
	
	public int resetJobAlertCount(Long applicantId);
	
	public List<ApplicantDetailsDTO> getJobApplicantsDetailsByStatus(String applicantStatus);
	
	public long countShortlistedAndInterviewedApplicants(long recruiterId);
}
