package com.bitlabs.App.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class JobDTO {

	private Long id;
    private String jobTitle;
    private int minimumExperience;
    private int maximumExperience;
    
    @Min(0)
    private double maxSalary;
    
    @Min(0)
    private double minSalary;
   
    private  String location;
    private  String employeeType;
    private  String industryType;
    private  String minimumQualification;
    private  String specialization;
    private  String jobHighlights;
    private  String description;
	public JobDTO(Long id, String jobTitle, int minimumExperience, int maximumExperience, @Min(0) double maxSalary,
			@Min(0) double minSalary, String location, String employeeType, String industryType,
			String minimumQualification, String specialization, String jobHighlights, String description) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.minimumExperience = minimumExperience;
		this.maximumExperience = maximumExperience;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.location = location;
		this.employeeType = employeeType;
		this.industryType = industryType;
		this.minimumQualification = minimumQualification;
		this.specialization = specialization;
		this.jobHighlights = jobHighlights;
		this.description = description;
	}
	public JobDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
}
