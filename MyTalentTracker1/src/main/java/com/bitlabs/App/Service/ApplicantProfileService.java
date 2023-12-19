package com.bitlabs.App.Service;

import java.util.Optional;

import com.bitlabs.App.Entity.ApplicantProfile;

public interface ApplicantProfileService {
	
	public String createOrUpdateApplicantProfile(long applicantId, ApplicantProfile applicantProfile);
	public Optional<ApplicantProfile>viewApplicantById(int profileId);
	

}
