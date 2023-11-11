package com.bitlabs.App.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>  {
	
	@Query("SELECT DISTINCT j FROM Job j " +
	           "JOIN j.skillsRequired r " +
	           "WHERE LOWER(r.skillName) IN :skillNames")
	List<Job> findBySkillsRequiredIgnoreCaseAndSkillNameIn(Set<String> skillNames);
	
	
	
/* @Query("SELECT DISTINCT j FROM Job j " +
	           "JOIN j.skillsRequired s " +
	           "WHERE LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	           "   OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    List<Job> searchJobsByKeyword(@Param("keyword") String keyword);
	
	
/* @Query("SELECT DISTINCT j FROM Job j " +
		       "JOIN j.skillsRequired s " +
		       "WHERE (:jobId IS NULL OR j.id = :jobId) " +  // Check if jobId is provided and matches
		       "   AND LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%')) " 
		      )  // Check maxExperience condition
		List<Job> searchJobsByKeywordAndjobId(@Param("keyword") String keyword,
		                                          @Param("jobId") Long jobId);
		                         
/*	
	@Query("SELECT DISTINCT j FROM Job j " +
		       "JOIN j.skillsRequired s " +
		       "WHERE (:jobId IS NULL OR j.id = :jobId) " +
		       "   AND LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   AND (j.maxSalary <= :maxSalary OR :maxSalary IS NULL) " +
		       "   AND (j.minimumExperience >= :minExperience OR :minExperience IS NULL) " +
		       "   AND (j.maximumExperience <= :maxExperience OR :maxExperience IS NULL)")
		List<Job> searchJobsByKeywordAndMaxSalary(@Param("keyword") String keyword,
		                                          @Param("jobId") Long jobId,
		                                          @Param("maxSalary") Double maxSalary,
		                                          @Param("minExperience") Integer minExperience,
		                                          @Param("maxExperience") Integer maxExperience);
   
   
	@Query("SELECT DISTINCT j FROM Job j " +
	        "WHERE (:jobId IS NULL OR j.id = :jobId) " +
	        "   AND LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	        "   AND LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	        "   AND LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	        "   AND LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	        "   AND LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
	        "   AND LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
	List<Job> searchJobsByKeywordAndjobId(@Param("keyword") String keyword,
	                                      @Param("jobId") Long jobId);
	

	@Query("SELECT DISTINCT j FROM Job j " +
		       "JOIN j.skillsRequired s " +
		       "WHERE (:keyword IS NULL OR LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
		       "   AND (:id IS NULL OR j.id = :id) " +
		       "   AND (:minExperience IS NULL OR j.minimumExperience >= :minExperience) " +
		       "   AND (:maxExperience IS NULL OR j.maximumExperience <= :maxExperience) " +
		       "   AND (:maxSalary IS NULL OR j.maxSalary <= :maxSalary)")
		List<Job> searchJobsByKeyword(
		    @Param("keyword") String keyword,
		    @Param("id") Long id,
		    @Param("minExperience") Integer minExperience,
		    @Param("maxExperience") Integer maxExperience,
		    @Param("maxSalary") Double maxSalary);
		    
	
	
	@Query("SELECT DISTINCT j FROM Job j " +
		       "JOIN j.skillsRequired s " +
		       "WHERE (:keyword IS NULL OR " +
		       "   LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.industryType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "   OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
		       "   AND (:id IS NULL OR j.id = :id) " +
		       "   AND (:minExperience IS NULL OR j.minimumExperience >= :minExperience) " +
		       "   AND (:maxExperience IS NULL OR j.maximumExperience <= :maxExperience) " +
		       "   AND (:maxSalary IS NULL OR j.maxSalary <= :maxSalary)")
		List<Job> searchJobsByKeyword(
		    @Param("keyword") String keyword,
		    @Param("id") Long id,
		    @Param("minExperience") Integer minExperience,
		    @Param("maxExperience") Integer maxExperience,
		    @Param("maxSalary") Double maxSalary);
*/
	@Query("SELECT j FROM Job j " +
		       "WHERE (:jobTitle IS NULL OR LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%'))) " +
		       "AND (:id IS NULL OR j.id = :id) " +
		       "AND (:minExperience IS NULL OR j.minimumExperience >= :minExperience) " +
		       "AND (:maxExperience IS NULL OR j.maximumExperience <= :maxExperience) " +
		       "AND (:maxSalary IS NULL OR j.maxSalary <= :maxSalary) " +
		       "AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
		       "AND (:employeeType IS NULL OR LOWER(j.employeeType) LIKE LOWER(CONCAT('%', :employeeType, '%'))) " +
		       "AND (:industryType IS NULL OR LOWER(j.industryType) LIKE LOWER(CONCAT('%', :industryType, '%'))) " +
		       "AND (:minimumQualification IS NULL OR LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :minimumQualification, '%'))) " +
		       "AND (:specialization IS NULL OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :specialization, '%')))" +
	           " AND (:skillName IS NULL OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skillName, '%')))")
	List<Job> searchJobsByFilters(
	    @Param("jobTitle") String jobTitle,
	    @Param("id") Long id,
	    @Param("minExperience") Integer minExperience,
	    @Param("maxExperience") Integer maxExperience,
	    @Param("maxSalary") Double maxSalary,
	    @Param("location") String location,
	    @Param("employeeType") String employeeType,
	    @Param("industryType") String industryType,
	    @Param("minimumQualification") String minimumQualification,
	    @Param("specialization") String specialization,
	    @Param("skillName") String skillName);

		
	
}
