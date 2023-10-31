package com.bitlabs.App.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class GraduationDetails {

	 private String gcollegeName;  
	 private String gboard;        
	 private String gprogram;      
	 private String gpercentage;   
	 private String gyearOfPassing;
	 private String gCity;        
	 private String gState;
}
