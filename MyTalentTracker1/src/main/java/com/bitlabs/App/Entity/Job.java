package com.bitlabs.App.Entity;

import java.util.HashSet;
import java.util.Set;
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
import jakarta.persistence.JoinColumn;

 @Entity
// @JsonIgnoreProperties({"jobRecruiters"})
public class Job {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(mappedBy = "companyProfile", cascade = CascadeType.ALL)
   // @JoinColumn(name = "jobRecruiter_id")
    private JobRecruiter jobRecruiter;

    @Column(nullable = false)
    private String jobTitle;

 
	public JobRecruiter getJobRecruiter() {
		return jobRecruiter;
	}

 

	public void setJobRecruiter(JobRecruiter jobRecruiter) {
		this.jobRecruiter = jobRecruiter;
	}

	@Column(nullable = false)

    private int minimumExperience;

   public int getMinimumExperience() {
		return minimumExperience;
	}



	public void setMinimumExperience(int minimumExperience) {
		this.minimumExperience = minimumExperience;
	}

@Column(nullable = false)
    private int maximumExperience;

 
    @Column(nullable = false)

    private double maxSalary;
    @Column(nullable = false)

    private double minSalary;
 
    public double getMinSalary() {
		return minSalary;
	}


	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

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

 

    public void addSkillWithMinimumExperience(String skillName, int minimumExperience) {

        RecruiterSkills skill = new  RecruiterSkills();

        skill.setSkillName(skillName);

        skill.setMinimumExperience(minimumExperience);

        skillsRequired.add(skill);

    }

 

    private String jobHighlights;

    @Column(nullable = false, length = 2000)

    private String description;

 

    @Lob

    @Column

    private byte[] uploadDocument; // Use byte[] to store the file content

 

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public String getJobTitle() {

		return jobTitle;

	}

	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;

	}

 

public int getMaximumExperience() {

	return maximumExperience;

	}

 

	public void setMaximumExperience(int maximumExperience) {

		this.maximumExperience = maximumExperience;

	}

 
	public double getMaxSalary() {

		return maxSalary;

	}

 

	public void setMaxSalary(double maxSalary) {

		this.maxSalary = maxSalary;

	}

 

	public String getLocation() {

		return location;

	}

 

	public void setLocation(String location) {

		this.location = location;

	}

 

	public String getEmployeeType() {

		return employeeType;

	}

 

	public void setEmployeeType(String employeeType) {

		this.employeeType = employeeType;

	}

 

	public String getIndustryType() {

		return industryType;

	}

 

	public void setIndustryType(String industryType) {

		this.industryType = industryType;

	}

 

	public String getMinimumQualification() {

		return minimumQualification;

	}

 

	public void setMinimumQualification(String minimumQualification) {

		this.minimumQualification = minimumQualification;

	}

 

	public String getSpecialization() {

		return specialization;

	}

 

	public void setSpecialization(String specialization) {

		this.specialization = specialization;

	}

 

	public String getJobHighlights() {

		return jobHighlights;

	}

 

	public void setJobHighlights(String jobHighlights) {

		this.jobHighlights = jobHighlights;

	}

	public Set<RecruiterSkills> getSkillsRequired() {
		return skillsRequired;
	}



	public void setSkillsRequired(Set<RecruiterSkills> skillsRequired) {
		this.skillsRequired = skillsRequired;
	}



	public String getDescription() {

		return description;

	}

 	public void setDescription(String description) {

		this.description = description;

	}

 	public byte[] getUploadDocument() {
		return uploadDocument;

	}

 	public void setUploadDocument(byte[] uploadDocument) {
		this.uploadDocument = uploadDocument;

	}



}

 	