package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.RecruiterProfile;
import com.bitlabs.App.dto.JobAlertDTO;

public interface AlertService {
	
	public void sendAlert(JobRecruiter recruiter, ApplyJob applyJob, String alertMessage);
}