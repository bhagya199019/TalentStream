package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.Alert;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.RecruiterProfile;


public interface AlertService {
	
	public void sendAlert(JobRecruiter recruiter, ApplyJob applyJob, String alertMessage);
	 public List<Alert> getAlertsByApplicantId(Long applicantId);
	
}
