package com.bitlabs.App.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name="ApplicantSavedJob")
@Data
public class SavedJob {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "applicant_id")
    private JobApplicant jobApplicant;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

	
    

}
