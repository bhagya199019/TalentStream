package com.bitlabs.App.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDetailsDTO {

	 private String candidateName;
	    private String email;
	    private String mobileNumber;
	    private String jobTitle;
	    private String applicantStatus;
	    private List<ScheduleInterviewDTO> scheduleInterviews;
}
