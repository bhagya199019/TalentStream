package com.bitlabs.App.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantJobInterviewDTO {
	
	    private String name;
	    private String email;
	    private String mobilenumber;
	    private String jobTitle;
	    private LocalDateTime timeAndDate;
	    private String location;
	    private String modeOfInterview;
	    private int round;
	    private String interviewLink;
	    private String interviewPerson;

}
