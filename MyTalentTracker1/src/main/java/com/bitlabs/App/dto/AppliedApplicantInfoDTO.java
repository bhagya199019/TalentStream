package com.bitlabs.App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppliedApplicantInfoDTO {
	
	private Long applyjobid;
    private String name;
    private String email;
    private String mobilenumber;
    private String jobTitle;
    private String applicantStatus;
    private int minimumExperience;
    private String skillName;
    private String minimumQualification;
    private String location;
	
    
    

}
