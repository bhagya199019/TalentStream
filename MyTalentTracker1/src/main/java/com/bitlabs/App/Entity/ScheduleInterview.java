package com.bitlabs.App.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ScheduleInterview {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String interviewTitle;
	    private String interviewPerson;
	    private String typeOfInterview;
	    private int round;
	    private String modeOfInterview;
	    private String location;
	    private String interviewLink;
	    
	    @ManyToOne
	    @JsonBackReference
	    @JoinColumn(name = "apply_job_id")
	    private ApplyJob applyJob;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getInterviewTitle() {
			return interviewTitle;
		}

		public void setInterviewTitle(String interviewTitle) {
			this.interviewTitle = interviewTitle;
		}

		public String getInterviewPerson() {
			return interviewPerson;
		}

		public void setInterviewPerson(String interviewPerson) {
			this.interviewPerson = interviewPerson;
		}

		public String getTypeOfInterview() {
			return typeOfInterview;
		}

		public void setTypeOfInterview(String typeOfInterview) {
			this.typeOfInterview = typeOfInterview;
		}

		public int getRound() {
			return round;
		}

		public void setRound(int round) {
			this.round = round;
		}

		

		public String getModeOfInterview() {
			return modeOfInterview;
		}

		public void setModeOfInterview(String modeOfInterview) {
			this.modeOfInterview = modeOfInterview;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getInterviewLink() {
			return interviewLink;
		}

		public void setInterviewLink(String interviewLink) {
			this.interviewLink = interviewLink;
		}

		public ApplyJob getApplyJob() {
			return applyJob;
		}
        
		public Long getApplyJobId() {
	        return (applyJob != null) ? applyJob.getApplyjobid() : null;
	    }
		public void setApplyJob(ApplyJob applyJob) {
			this.applyJob = applyJob;
		}

		public ScheduleInterview(Long id, String interviewTitle, String interviewPerson, String typeOfInterview,
				int round, String modeOfInterview, String location, String interviewLink,
				ApplyJob applyJob) {
			super();
			this.id = id;
			this.interviewTitle = interviewTitle;
			this.interviewPerson = interviewPerson;
			this.typeOfInterview = typeOfInterview;
			this.round = round;
			this.modeOfInterview = modeOfInterview;
			this.location = location;
			this.interviewLink = interviewLink;
			this.applyJob = applyJob;
		}

		public ScheduleInterview() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
