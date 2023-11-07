package com.bitlabs.App.Service;

import com.bitlabs.App.Entity.ApplicantProfile;

public interface ApplicantProfileService {
	
	public ApplicantProfile createOrUpdateProfile(ApplicantProfile applicantProfile);
	public ApplicantProfile viewApplicantById(int profileId);
	public void deleteApplicantById(int applicantId);

}
