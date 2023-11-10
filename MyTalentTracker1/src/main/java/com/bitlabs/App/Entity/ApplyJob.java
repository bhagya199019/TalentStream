package com.bitlabs.App.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="Applyjob")
public class ApplyJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applyjobid;
 
	@ManyToOne
    @JoinColumn(name = "applicantId")
    private JobApplicant jobApplicant;
 
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    
   
 
    @Column(nullable = false)
    private String applicantStatus = "New";
    
    @OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScheduleInterview> scheduleInterviews;


	public List<ScheduleInterview> getScheduleInterviews() {
		return scheduleInterviews;
	}
	public void setScheduleInterviews(List<ScheduleInterview> scheduleInterviews) {
		this.scheduleInterviews = scheduleInterviews;
	}
 
	public Long getApplyjobid() {
		return applyjobid;
	}
 
	public void setApplyjobid(Long applyjobid) {
		this.applyjobid = applyjobid;
	}
 

 
	public JobApplicant getJobApplicant() {
		return jobApplicant;
	}
 
	public void setJobApplicant(JobApplicant jobapplicant) {
		this.jobApplicant = jobApplicant;
	}
 
	public Job getJob() {
		return job;
	}
 
	public void setJob(Job job) {
		this.job = job;
	}
	public String getApplicantStatus() {
        return applicantStatus;
    }

	public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

 
}