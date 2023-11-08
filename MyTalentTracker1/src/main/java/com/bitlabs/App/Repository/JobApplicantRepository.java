package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitlabs.App.Entity.JobApplicant;

public interface JobApplicantRepository extends JpaRepository<JobApplicant,Long> {
	
	JobApplicant findByEmail(String email);
    boolean existsByEmail(String email);
    
   JobApplicant getJobApplicantById(long applicantId);
   
   JobApplicant findById(long applicantId);
	
}
