package com.bitlabs.App.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewFeedbackUpdateDTO {
	 private Long interviewId;
	    private String feedback;
	    private String status;

}
