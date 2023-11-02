package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitlabs.App.Entity.JobRecruiter;

public interface JobRecruiterRepository extends JpaRepository<JobRecruiter,Long> {

	JobRecruiter findByEmail(String recruiterEmail);
	 boolean existsByEmail(String recruiterEmail);
	
}
