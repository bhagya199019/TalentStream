package com.bitlabs.App.Entity;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
public class ScheduleInterview {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String interviewTitle;
	    private String interviewPerson;
	    private String typeOfInterview;
	    private int round;
	    
	    @Column(columnDefinition = "DATETIME")
	    private LocalDateTime timeAndDate;
	    private String modeOfInterview;
	    private String location;
	    private String interviewLink;
	    
	    @ManyToOne
	    @JsonBackReference
	    @JoinColumn(name = "apply_job_id")
	    private ApplyJob applyJob;

		
}
