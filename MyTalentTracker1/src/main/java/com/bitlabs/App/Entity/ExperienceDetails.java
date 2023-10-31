package com.bitlabs.App.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ExperienceDetails {

	   private String company;
	    private String position;
	    private String startDate;
	    private String endDate;
}
