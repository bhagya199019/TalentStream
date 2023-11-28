package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.ScheduleInterview;
import com.bitlabs.App.dto.ApplicantJobInterviewDTO;

@Repository
public interface ScheduleInterviewRepository extends JpaRepository<ScheduleInterview, Long>{
	
	/*
	// Define custom query methods if needed
		@Query("SELECT NEW com.bitlabs.App.dto.ApplicantJobInterviewDTO(" +
		        "a.name, a.email, a.mobilenumber, j.jobTitle, si.timeAndDate, si.location, si.modeOfInterview, si.round, si.interviewLink, si.interviewPerson) " +
		        "FROM ScheduleInterview si " +
		        "INNER JOIN si.applyJob aj " +
		        "INNER JOIN aj.jobApplicant a " +
		        "INNER JOIN aj.job j " +
		        "INNER JOIN j.jobRecruiter r " +
		        "WHERE r.recruiterId = :recruiterId " +
		        "AND aj.applicantStatus = :applicantStatus")
		List<ApplicantJobInterviewDTO>getApplicantJobInterviewInfoByRecruiterAndStatus(@Param("recruiterId") long recruiterId,
		        @Param("applicantStatus") String applicantStatus);
		
		
		@Query("SELECT NEW com.bitlabs.App.dto.ApplicantJobInterviewDTO(" +
		        "a.name, a.email, a.mobilenumber, j.jobTitle, si.timeAndDate, si.location, si.modeOfInterview, si.round, si.interviewLink, si.interviewPerson) " +
		        "FROM ScheduleInterview si " +
		        "INNER JOIN si.applyJob aj " +
		        "INNER JOIN aj.jobApplicant a " +
		        "INNER JOIN aj.job j " +
		        "INNER JOIN j.jobRecruiter r " +
		        "WHERE a.id = :applicantId " +
		        "AND aj.applicantStatus = :applicantStatus")
		List<ApplicantJobInterviewDTO>getApplicantJobInterviewInfoByApplicantAndStatus(
		        @Param("applicantId") long applicantId,
		        @Param("applicantStatus") String applicantStatus);
		        */

}
