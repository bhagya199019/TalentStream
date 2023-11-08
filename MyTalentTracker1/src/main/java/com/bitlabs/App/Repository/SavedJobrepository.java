package com.bitlabs.App.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.SavedJob;

@Repository
public interface SavedJobrepository extends JpaRepository<SavedJob,Long>  {
	
 List<SavedJob>findByApplicantId(Long applicantId);

boolean existsByApplicantAndJob(JobApplicant jobApplicant, Job job);
	

}
