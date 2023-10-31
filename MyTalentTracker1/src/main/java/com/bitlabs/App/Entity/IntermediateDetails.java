package com.bitlabs.App.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class IntermediateDetails {
	 private String icollegeName;
	    private String iboard;
	    private String iprogram;
	    private String ipercentage;
	    private String iyearOfPassing;
	    private String iCity;
	    private String iState;

}
