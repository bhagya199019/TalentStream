package com.bitlabs.App.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class XclassDetails {

	private String xschoolName;
    private String xboard;
    private String xpercentage;
    private String xyearOfPassing;
    private String xcity;
    private String xstate;
    private String xpincode;
    
}
