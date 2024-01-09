package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.dto.AppliedApplicantInfo;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long>{

	List<ApplyJob>findByJobApplicantId(long JobApplicantId);
	boolean existsByJobApplicantAndJob(JobApplicant applicant, Job job);
	
	@Query("SELECT NEW com.bitlabs.App.dto.AppliedApplicantInfo(" +
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

	    @Query("SELECT NEW com.bitlabs.App.dto.AppliedApplicantInfo(" +
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
	    
	    
	    @Query("SELECT NEW com.bitlabs.App.dto.AppliedApplicantInfo(" +
	            " aj.applyjobid, a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicantStatus, " +
	            " j.minimumExperience, s.skillName, " +
	            " j.minimumQualification, j.location) " +
	            "FROM ApplyJob aj " +
	            "INNER JOIN aj.jobApplicant a " +
	            "INNER JOIN aj.job j " +
	            "INNER JOIN j.skillsRequired s " +
	            "WHERE aj.id = :applyJobId")
	    List<AppliedApplicantInfo> findApplicantInfoByApplyJobId(@Param("applyJobId") long applyJobId);
	    
	    
	    

	    @Query("SELECT a FROM ApplyJob a WHERE a.job.id = :jobId AND a.applicantStatus = :applicantStatus")
	    List<ApplyJob> findByJobIdAndApplicantStatus(@Param("jobId") Long jobId, @Param("applicantStatus") String applicantStatus);
	    
	    
	    @Query("SELECT DISTINCT aj " +
			       "FROM ApplyJob aj " +
			       "LEFT JOIN FETCH aj.job j " +
			       "LEFT JOIN FETCH j.skillsRequired s " +
			       "WHERE (:jobTitle IS NULL OR LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%'))) " +
			       "AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
			       "AND (:skillName IS NULL OR LOWER(s.skillName) = LOWER(:skillName))")
			List<ApplyJob> findByJobTitleAndLocationAndSkillName(
			        @Param("jobTitle") String jobTitle,
			        @Param("location") String location,
			        @Param("skillName") String skillName);
	    
	    
	    @Query(value = "SELECT COUNT(*) FROM apply_job aj " +
	            "JOIN Job j ON aj.job_id = j.id " +
	            "WHERE j.job_recruiter_recruiter_id = :recruiterId " +
	            "AND aj.applicant_status IN :statusList", nativeQuery = true)
	long countShortlistedAndInterviewedApplicants(@Param("recruiterId") Long recruiterId,
	                                           @Param("statusList") List<String> statusList);

	

}


