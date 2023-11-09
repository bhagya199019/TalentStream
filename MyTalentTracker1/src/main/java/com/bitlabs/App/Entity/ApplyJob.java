package com.bitlabs.App.Entity;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class ApplyJob {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long applyJobId;
	
	@ManyToOne
	@JoinColumn(name="applicantId")
	private JobApplicant jobApplicant;
	
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDate;
	
	@Column(nullable=false)
	private String applicantStatus="New";
	
	@OneToMany(mappedBy="applyJob",cascade=CascadeType.ALL)
	@JsonManagedReference
   private List<ScheduleInterview>scheduleInterview;

	public Long getApplyJobId() {
		return applyJobId;
	}

	public void setApplyJobId(Long applyJobId) {
		this.applyJobId = applyJobId;
	}

	public JobApplicant getJobApplicant() {
		return jobApplicant;
	}

	public void setJobApplicant(JobApplicant jobApplicant) {
		this.jobApplicant = jobApplicant;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	@PrePersist
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApplicantStatus() {
		return applicantStatus;
	}

	public void setApplicantStatus(String applicantStatus) {
		this.applicantStatus = applicantStatus;
	}

	public List<ScheduleInterview> getScheduleInterview() {
		return scheduleInterview;
	}

	public void setScheduleInterview(List<ScheduleInterview> scheduleInterview) {
		this.scheduleInterview = scheduleInterview;
	}
	
}
