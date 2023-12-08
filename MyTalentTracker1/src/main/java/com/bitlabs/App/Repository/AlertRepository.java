package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitlabs.App.Entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {
   
	@Query("SELECT a FROM Alert a LEFT JOIN FETCH a.jobApplicant WHERE a.jobApplicant.id = :applicantId")
	List<Alert> findByJobApplicantId(@Param("applicantId") Long applicantId);

	
}