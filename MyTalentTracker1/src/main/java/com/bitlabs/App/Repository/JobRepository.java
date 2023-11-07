package com.bitlabs.App.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>  {
	
	@Query("SELECT DISTINCT j FROM Job j " +
	           "JOIN j.skillsRequired r " +
	           "WHERE LOWER(r.skillName) IN :skillNames")
	List<Job> findBySkillsRequiredIgnoreCaseAndSkillNameIn(Set<String> skillNames);
}
