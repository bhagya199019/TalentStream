package com.bitlabs.App.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInterviewDTO {

	 private Long id;
	    private String interviewTitle;
	    private String interviewPerson;
	    private String typeOfInterview;
	    private int round;
	    private LocalDateTime timeAndDate;
	    private String modeOfInterview;
	    private String location;
	    private String interviewLink;
	    private String interviewFeedback;
}
