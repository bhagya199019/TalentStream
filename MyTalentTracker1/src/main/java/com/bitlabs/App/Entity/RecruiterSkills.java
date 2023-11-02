package com.bitlabs.App.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class RecruiterSkills {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		              CascadeType.PERSIST,
		              CascadeType.MERGE
	          })
	 @JsonIgnore
    private Set<Job> jobs=new HashSet<>();

    
	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@Column(nullable = false)
    private String skillName;

	@Column(nullable = false, columnDefinition = "int default 0")
	private int minimumExperience;

   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getMinimumExperience() {
		return minimumExperience;
	}

	public void setMinimumExperience(int minimumExperience) {
		this.minimumExperience = minimumExperience;
	}
    

}
