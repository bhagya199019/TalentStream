package com.bitlabs.App.Entity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ApplicantProfile {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileid;

    @Embedded
    private BasicDetails basicDetails;

    @Embedded
    private XclassDetails xClassDetails;

    @Embedded
    private IntermediateDetails intermediateDetails;

    @Embedded
    private GraduationDetails graduationDetails;
    
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })

    @JoinTable(
            name = "applicant_profile_skills_required", 
            joinColumns = @JoinColumn(name = "profileid"), 
            inverseJoinColumns = @JoinColumn(name = "applicantskill_id") // This is the column in the other table
        )
    
    private Set<ApplicantSkills> skillsRequired=new HashSet<>();
    
    
    @ElementCollection
    private List<ExperienceDetails> experienceDetails;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="applicantid", referencedColumnName = "id")
    private JobApplicant applicant;
    
   public void setApplicant(JobApplicant applicant) {
		this.applicant = applicant;
	}

@Column(nullable = false)
   private String roles="ROLE_JOBAPPLICANT";


}



 

