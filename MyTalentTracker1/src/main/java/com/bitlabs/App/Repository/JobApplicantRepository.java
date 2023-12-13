package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitlabs.App.Entity.JobApplicant;

public interface JobApplicantRepository extends JpaRepository<JobApplicant,Long> {
	
	JobApplicant findByEmail(String email);
    boolean existsByEmail(String email);
    
   JobApplicant getJobApplicantById(long applicantId);
   
   JobApplicant findById(long applicantId);
   
   @Query("SELECT ja FROM JobApplicant ja WHERE EXISTS (SELECT aj FROM ja.appliedJobs aj WHERE aj.applicantStatus = :applicantStatus)")
   List<JobApplicant> findByAppliedJobsApplicantStatus(@Param("applicantStatus") String applicantStatus);

	
}
