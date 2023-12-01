package com.bitlabs.App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAlertDTO {

	private Long id;
    private String message;
    private boolean isRead;
    private Long scheduleInterviewId;
}
