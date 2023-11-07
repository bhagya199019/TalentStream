package com.bitlabs.App.ServiceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Repository.ApplicantProfileRepository;
import com.bitlabs.App.Service.ApplicantProfileService;

@Service
public class ApplicantProfileServiceImpl implements ApplicantProfileService {
	
	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;
	
	public ApplicantProfile createOrUpdateProfile(ApplicantProfile applicantProfile) {
		
		return applicantProfileRepository.save(applicantProfile);
	}

	
	public ApplicantProfile viewApplicantById(int profileId) {
		return applicantProfileRepository.findById(profileId).orElse(null);
	}
	
	public void deleteApplicantById(int  profileId) {
	applicantProfileRepository.deleteById(profileId);
	}
}
