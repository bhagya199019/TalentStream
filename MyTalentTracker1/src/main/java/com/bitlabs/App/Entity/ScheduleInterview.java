package com.bitlabs.App.Entity;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	    @JoinColumn(name = "applyjobid")
	    private ApplyJob applyJob;
        
	 // Interview feedback fields
	    private String interviewFeedback;  // Add a field to store feedback
	  //  private String interviewStatus;    // Add a field to store the status of the interview
	    
	    @OneToMany(mappedBy = "scheduleInterview", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private List<JobAlert> jobAlerts = new ArrayList<>();
		
}
