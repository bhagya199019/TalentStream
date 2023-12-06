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
public class ApplyJobStatusHistoryDTO {
	
	private String applicantStatus;

    private LocalDateTime changeDate;

}
