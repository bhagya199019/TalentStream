package com.bitlabs.App.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.Alert;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;

import com.bitlabs.App.Entity.RecruiterProfile;

import com.bitlabs.App.Repository.AlertRepository;
import com.bitlabs.App.Service.AlertService;

	@Service
public class AlertServiceImpl implements AlertService {

		@Autowired
	    private AlertRepository alertRepository;

	    @Autowired
	    private JavaMailSender javaMailSender; // Autowire JavaMailSender bean

	    public void sendAlert(RecruiterProfile recruiter, ApplyJob applyJob, String alertMessage) {
	        // Assuming each ApplyJob is associated with one JobApplicant
	        JobApplicant applicant = applyJob.getJobApplicant();

	        Alert alert = new Alert();
	        alert.setRecruiter(recruiter);
	        alert.setCompanyName(recruiter.getCompanyName());
	        alert.setAlertMessage(alertMessage);
	        alert.setAlertDate(LocalDateTime.now());

	        alertRepository.save(alert);

	        // Send email to the applicant
	        sendEmailToApplicant(applicant.getEmail(), alertMessage);
	      //  System.out.println("email is"+applicant.getEmail());
	    }


	    private void sendEmailToApplicant(String to, String alertMessage) {
	    	 String emailMessage = "Dear Applicant,\n\n" + alertMessage;
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Job Application Status Update");
	        message.setText(emailMessage);

	        javaMailSender.send(message);
	    }
	}
	



