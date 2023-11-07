package com.bitlabs.App.Service;

import java.util.Optional;

import com.bitlabs.App.Entity.ApplicantProfile;

public interface ApplicantProfileService {
	
	public ApplicantProfile createOrUpdateProfile(ApplicantProfile applicantProfile);
	public Optional<ApplicantProfile>viewApplicantById(int profileId);
	public void deleteApplicantById(int applicantId);

}
