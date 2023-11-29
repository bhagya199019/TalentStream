package com.bitlabs.App.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.ScheduleInterview;
import com.bitlabs.App.Repository.ApplyJobRepository;
import com.bitlabs.App.Repository.ScheduleInterviewRepository;
import com.bitlabs.App.Service.ScheduleInterviewService;
import com.bitlabs.App.dto.InterviewFeedbackUpdateDTO;
import jakarta.persistence.EntityNotFoundException;



@Service
public class ScheduleInterviewServiceImpl implements ScheduleInterviewService {

	  @Autowired
	private ApplyJobRepository applyJobRepository;

	    @Autowired
	    private ScheduleInterviewRepository scheduleInterviewRepository;

	    public ScheduleInterview createScheduleInterview(Long applyJobId, ScheduleInterview scheduleInterview) {
	        // Find the ApplyJob based on the given applyJobId
	        ApplyJob applyJob = applyJobRepository.findById(applyJobId)
	                .orElseThrow(() -> new EntityNotFoundException("ApplyJob not found"));

	        // Set the ApplyJob for the ScheduleInterview
	        scheduleInterview.setApplyJob(applyJob);
              
	        // Save the ScheduleInterview with the ApplyJob association
	        return scheduleInterviewRepository.save(scheduleInterview);
	    }
	    
	    

 public String updateInterviewFeedback(Long interviewId, String newFeedback) {
	          Optional<ScheduleInterview> scheduleInterview = scheduleInterviewRepository.findById(interviewId);

	            if (scheduleInterview.isPresent()) {
	                ScheduleInterview interview = scheduleInterview.get();
	                interview.setInterviewFeedback(newFeedback);
	                scheduleInterviewRepository.save(interview);
	                return "Interview feedback updated to: " + newFeedback;
	            } else {
	                return "Interview not found with ID: " + interviewId;
	            }
	        }
	       
 
 public List<InterviewFeedbackUpdateDTO> getFeedbackByApplicantId(long applicantId) {
	    return scheduleInterviewRepository.findFeedbackByApplicantId(applicantId);
	}

	        
	    }


