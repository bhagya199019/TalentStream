package com.bitlabs.App.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
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
	
	   @OneToMany(mappedBy="jobApplicant")
	    @JsonIgnore
	    private Set<ApplyJob> appliedJobs = new HashSet<>();
	    
	   @OneToMany(mappedBy="jobApplicant")
	    @JsonIgnore
	    private Set<SavedJob> savedJobs = new HashSet<>();
	
	@Column(nullable = false)
    private String roles="ROLE_JOBAPPLICANT";

	@Override
	public String toString() {
		return "JobApplicant [id=" + id + ", name=" + name + ", email=" + email + ", mobilenumber=" + mobilenumber
				+ ", password=" + password + ", roles=" + roles + "]";
	} 
	
	
	
}
