package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.dto.AppliedApplicantInfoDto;

public interface ApplyJobservice {
	
	public String applicantApplyForJob(long applicantId, long jobId);
	
	public List<AppliedApplicantInfoDto> getAppliedApplicants(long jobRecruiterId);
	
	public List<Job> getAppliedJobsForApplicant(long applicantId);
	
	public String updateApplicantStatus(Long applyJobId, String newStatus);

}
