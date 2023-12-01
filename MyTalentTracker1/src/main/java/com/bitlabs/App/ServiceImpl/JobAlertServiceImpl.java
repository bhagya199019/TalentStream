package com.bitlabs.App.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.JobAlert;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.ScheduleInterview;
import com.bitlabs.App.Repository.JobAlertRepository;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Repository.ScheduleInterviewRepository;
import com.bitlabs.App.Service.JobAlertService;
import com.bitlabs.App.dto.JobAlertDTO;

import jakarta.persistence.EntityNotFoundException;


	@Service
	public class JobAlertServiceImpl implements JobAlertService {

	    @Autowired
	    private JobAlertRepository jobAlertRepository;

	    @Autowired
	    private ScheduleInterviewRepository scheduleInterviewRepository;

	    @Autowired
	    private JobRecruiterRepository jobRecruiterRepository;

	    public void sendAlert(Long recruiterId, Long scheduleInterviewId, String message) {
	        // Retrieve Recruiter
	        JobRecruiter jobRecruiter = jobRecruiterRepository.findById(recruiterId)
	                .orElseThrow(() -> new EntityNotFoundException("Recruiter not found"));

	        // Retrieve ScheduleInterview
	        ScheduleInterview scheduleInterview = scheduleInterviewRepository.findById(scheduleInterviewId)
	                .orElseThrow(() -> new EntityNotFoundException("ScheduleInterview not found"));

	        // Retrieve JobApplicant from the associated Job
	        JobApplicant jobApplicant = scheduleInterview.getApplyJob().getJobApplicant();

	        // Create a new JobAlert
	        JobAlert jobAlert = new JobAlert();
	        jobAlert.setMessage(message);
	        jobAlert.setJobRecruiter(jobRecruiter);
	        jobAlert.setJobApplicant(jobApplicant);
	        jobAlert.setScheduleInterview(scheduleInterview);

	        // Save the JobAlert
	        jobAlertRepository.save(jobAlert);
	    }
	    
	    
	    public List<JobAlertDTO> getJobAlertsByApplicant(Long applicantId) {
	        // Retrieve JobAlerts for the given JobApplicant ID
	        List<JobAlert> jobAlerts = jobAlertRepository.findByJobApplicantId(applicantId);

	        // Convert JobAlert entities to DTOs if needed
	        List<JobAlertDTO> jobAlertDTOs = jobAlerts.stream()
	        		.filter(jobAlert -> jobAlert.getScheduleInterview() != null)  // Filter out null ScheduleInterviews
	                .map(jobAlert -> new JobAlertDTO(
	                        jobAlert.getId(),
	                        jobAlert.getMessage(),
	                        jobAlert.isRead(),
	                        jobAlert.getScheduleInterview().getId()))
	                .collect(Collectors.toList());

	        return jobAlertDTOs;
	    }
	}
	



