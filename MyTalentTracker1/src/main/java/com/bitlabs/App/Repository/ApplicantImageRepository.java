package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitlabs.App.Entity.ApplicantImage;
import com.bitlabs.App.Entity.JobApplicant;

public interface ApplicantImageRepository extends JpaRepository<ApplicantImage, Long> {

	ApplicantImage findByApplicantId(long applicantId);

	ApplicantImage findById(JobApplicant applicant);

	boolean existsByApplicant(JobApplicant applicant);
}
