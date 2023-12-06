package com.bitlabs.App.Entity;


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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applyjobid;
 
	@ManyToOne
    @JoinColumn(name = "applicant_id")
    private JobApplicant jobApplicant;

	@ManyToOne
    @JoinColumn(name ="job_id")
    private Job job;
    
    @Column(nullable = false)
    private String applicantStatus = "New";
    
    @OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScheduleInterview>scheduleInterviews;
    
    @Column(nullable = false)
    private String applicationDate;
    
    
    @OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ApplyJobStatusHistory> statusHistory;

	@Override
	public String toString() {
		return "ApplyJob [applyjobid=" + applyjobid + ", jobApplicant=" + jobApplicant + ", job=" + job
				+ ", applicantStatus=" + applicantStatus + ", scheduleInterviews=" + scheduleInterviews
				+ ", applicationDate=" + applicationDate + "]";
	}
	


	 
}