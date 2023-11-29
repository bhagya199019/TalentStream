package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.AppliedApplicantInfo;
import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long>{

	List<ApplyJob>findByJobApplicantId(long JobApplicantId);
	boolean existsByJobApplicantAndJob(JobApplicant applicant, Job job);
	
	@Query("SELECT NEW com.bitlabs.App.Entity.AppliedApplicantInfo(" +
		       " aj.applyjobid,a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicantStatus, " +
		       " j.minimumExperience, s.skillName, " +
		       "j.minimumQualification, j.location) " +
		       "FROM ApplyJob aj " +
		       "INNER JOIN aj.jobApplicant a " +
		       "INNER JOIN aj.job j " +
		       "INNER JOIN j.skillsRequired s " +
		       "INNER JOIN j.jobRecruiter r " +
		       "WHERE r.id = :jobRecruiterId")
	    List<AppliedApplicantInfo> findAppliedApplicantsInfo(@Param("jobRecruiterId") long jobRecruiterId);
	
	
	
	    List<ApplyJob> findByJobApplicantIdAndApplicantStatus(long jobApplicantId, String applicantStatus);

	    @Query("SELECT NEW com.bitlabs.App.Entity.AppliedApplicantInfo(" +
	           " aj.applyjobid, a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicantStatus, " +
	           " j.minimumExperience, s.skillName, " +
	           " j.minimumQualification, j.location) " +
	           "FROM ApplyJob aj " +
	           "INNER JOIN aj.jobApplicant a " +
	           "INNER JOIN aj.job j " +
	           "INNER JOIN j.skillsRequired s " +
	           "WHERE a.id = :applicantId " +
	           "AND aj.applicantStatus = :applicantStatus")
	    List<AppliedApplicantInfo> findApplicantInfoByStatus(
	    		@Param("applicantId") long applicantId,
	            @Param("applicantStatus") String applicantStatus
	    );
	    
	    
	    @Query("SELECT NEW com.bitlabs.App.Entity.AppliedApplicantInfo(" +
	            " aj.applyjobid, a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicantStatus, " +
	            " j.minimumExperience, s.skillName, " +
	            " j.minimumQualification, j.location) " +
	            "FROM ApplyJob aj " +
	            "INNER JOIN aj.jobApplicant a " +
	            "INNER JOIN aj.job j " +
	            "INNER JOIN j.skillsRequired s " +
	            "WHERE aj.id = :applyJobId")
	    List<AppliedApplicantInfo> findApplicantInfoByApplyJobId(@Param("applyJobId") long applyJobId);

	}

	


