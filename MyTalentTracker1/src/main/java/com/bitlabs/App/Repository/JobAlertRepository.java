package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitlabs.App.Entity.JobAlert;

public interface JobAlertRepository extends JpaRepository<JobAlert, Long> {
    // You can add custom query methods here if needed
	
	 @Query("SELECT ja FROM JobAlert ja WHERE ja.jobApplicant.id = :applicantId")
	    List<JobAlert> findByJobApplicantId(@Param("applicantId") Long applicantId);
}