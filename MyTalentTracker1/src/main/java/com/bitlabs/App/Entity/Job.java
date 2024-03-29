package com.bitlabs.App.Entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"jobRecruiter"})
@JsonIgnoreType
public class Job {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @ManyToOne
  //  @JoinColumn(name = "recruiter_id")
    private JobRecruiter jobRecruiter;
    
    @OneToMany(mappedBy="job")
    @JsonIgnore
    private Set<ApplyJob> appliedJobs = new HashSet<>();

    @Column(nullable = false)
    private String jobTitle;

   @Column(nullable = false)
   private int minimumExperience;

   @Column(nullable = false)
    private int maximumExperience;

 
    @Column(nullable = false)
    private double maxSalary;
   
    @Column(nullable = false)
    private double minSalary;
 
  

	@Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String employeeType;

 

    @Column(nullable = false)
    private String industryType;
 

    @Column(nullable = false)
    private String minimumQualification;

 
    @Column(nullable = false)
    private String specialization;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })

    @JoinTable(
        name = "job_skills",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
       private Set<RecruiterSkills> skillsRequired=new HashSet<>();

 

    // Constructors, getters, setters, and other properties...
    

  private String jobHighlights;

    @Column(nullable = false, length = 2000)

    private String description;

 

    @Lob

    @Column

    private byte[] uploadDocument; // Use byte[] to store the file content
    
    
    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;




}

 	