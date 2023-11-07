package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.ApplicantProfile;

@Repository
public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile,Integer>{
	
	ApplicantProfile findByApplicantId(int applicantprofileId);

}
