package com.bitlabs.App.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "applicantId")
    private JobApplicant jobApplicant;
 

	@ManyToOne
    @JoinColumn(name ="job_id")
    private Job job;
    
    @Column(nullable = false)
    private String applicantStatus = "New";
    
    @OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScheduleInterview> scheduleInterviews;


	 @Override
		public String toString() {
			return "ApplyJob [applyjobid=" + applyjobid + ", jobApplicant=" + jobApplicant + ", job=" + job
					+ ", applicantStatus=" + applicantStatus + ", scheduleInterviews=" + scheduleInterviews + "]";
		}
}