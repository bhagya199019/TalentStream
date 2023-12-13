package com.bitlabs.App.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String mobilenumber;
	private String password;
	
	   @OneToMany(mappedBy="jobApplicant",cascade = CascadeType.ALL)
	    @JsonIgnore
	    private Set<ApplyJob> appliedJobs = new HashSet<>();
	    
	   @OneToMany(mappedBy="jobApplicant")
	    @JsonIgnore
	    private Set<SavedJob> savedJobs = new HashSet<>();
	   
	   
	
	@Column(nullable = false)
    private String roles="ROLE_JOBAPPLICANT";
	
	
    private int alertCount = 0;

	
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobApplicant",cascade = CascadeType.ALL)
	private List<Alert> alerts = new ArrayList<>();
	
}
