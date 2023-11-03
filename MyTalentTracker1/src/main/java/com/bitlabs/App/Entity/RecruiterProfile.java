package com.bitlabs.App.Entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class RecruiterProfile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String companyName;

    private String website;

    private String phoneNumber;

    private String email;

    private String headOffice;

 

   @OneToOne//(mappedBy = "companyProfile", cascade = CascadeType.ALL)
    @JoinColumn(name = "jobRecruiter_id")
    private JobRecruiter jobRecruiter;

 

    // Constructors, getters, and setters

   
  @ElementCollection
  @CollectionTable(
      name = "social_profiles",
      joinColumns = @JoinColumn(name = "company_profile_id")
  )
  private List<String> socialProfiles;



}
