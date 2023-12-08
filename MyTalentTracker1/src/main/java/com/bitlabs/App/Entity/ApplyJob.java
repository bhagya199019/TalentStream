package com.bitlabs.App.Entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @JsonIgnore
    private List<ScheduleInterview>scheduleInterviews;
    
    @Column(columnDefinition = "TIMESTAMP",nullable=false)
    private LocalDateTime applicationDate;
    
    
    
 
    @OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
private List<ApplyJobStatusHistory> statusHistory;
    	

	
	 
}