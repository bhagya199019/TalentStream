package com.bitlabs.App.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="ApplicantSavedJob")
public class SavedJob {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "applicantregistration_id")
    private JobApplicant jobApplicant;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
    
    

}
