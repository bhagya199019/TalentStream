package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.ApplyJob;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long>{

	List<ApplyJob>findByJobApplicantId(long JobApplicantId);
	boolean existsByJobApplicantAndJob(JobApplicant applicant, Job job);
	

}
