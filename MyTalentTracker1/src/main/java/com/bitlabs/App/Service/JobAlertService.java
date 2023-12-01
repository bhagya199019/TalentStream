package com.bitlabs.App.Service;

import java.util.List;


import com.bitlabs.App.dto.JobAlertDTO;

public interface JobAlertService {
	 public void sendAlert(Long recruiterId, Long scheduleInterviewId, String message);
	 public List<JobAlertDTO> getJobAlertsByApplicant(Long applicantId);
}
