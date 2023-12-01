package com.bitlabs.App.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "job_applicant_id")
    private JobApplicant jobApplicant;

    private boolean isRead; // Indicates whether the alert has been read by the applicant

    @ManyToOne
    @JoinColumn(name = "schedule_interview_id")
    private ScheduleInterview scheduleInterview;
    
    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private JobRecruiter jobRecruiter;
    
}
