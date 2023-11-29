package com.bitlabs.App.Service;

import java.util.List;

import com.bitlabs.App.Entity.ScheduleInterview;
import com.bitlabs.App.dto.InterviewFeedbackUpdateDTO;

public interface ScheduleInterviewService {
	
	public ScheduleInterview createScheduleInterview(Long applyJobId, ScheduleInterview scheduleInterview);
	 public String updateInterviewFeedback(Long interviewId, String newFeedback);
	 
	 public List<InterviewFeedbackUpdateDTO> getFeedbackByApplicantId(long applicantId);

}
