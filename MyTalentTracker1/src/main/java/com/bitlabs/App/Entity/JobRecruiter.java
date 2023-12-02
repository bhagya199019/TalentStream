package com.bitlabs.App.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JobRecruiter {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterId;

    @Column(nullable = false)
    private String companyname;

    @Column(nullable = false, unique = true)
    private String mobilenumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs;
    

    @Column(nullable = false)
    private String roles="ROLE_JOBRECRUITER";
    
    
    @OneToMany(mappedBy = "jobRecruiter")
    private List<JobAlert> jobAlerts;
	
}
