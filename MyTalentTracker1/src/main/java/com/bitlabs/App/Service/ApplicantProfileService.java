package com.bitlabs.App.Service;

import com.bitlabs.App.Entity.ApplicantProfile;

public interface ApplicantProfileService {
	
	public ApplicantProfile createOrUpdateProfile(ApplicantProfile applicantProfile);
	public ApplicantProfile viewApplicantById(long applicantId);
	public void deleteApplicantById(long applicantId);

}
