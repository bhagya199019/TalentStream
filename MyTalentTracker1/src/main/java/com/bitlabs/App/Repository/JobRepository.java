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
		       "AND (:specialization IS NULL OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :specialization, '%')))")
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
		    @Param("specialization") String specialization);
	
}
