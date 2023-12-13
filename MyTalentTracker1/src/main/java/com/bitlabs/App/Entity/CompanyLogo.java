package com.bitlabs.App.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompanyLogo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String logoName;
	
	@OneToOne
	@JoinColumn(name="recruiter_id")
	private JobRecruiter jobRecruiter;
	
	
}
