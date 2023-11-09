package com.bitlabs.App.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Repository.ApplicantProfileRepository;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.ApplicantProfileService;

@Service
public class ApplicantProfileServiceImpl implements ApplicantProfileService {
	
	@Autowired
	private JobApplicantRepository jobApplicantRepository;
	
	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;
	
	 public String createOrUpdateApplicantProfile(long applicantId, ApplicantProfile applicantProfile) {
	    	JobApplicant jobApplicant = jobApplicantRepository.getJobApplicantById(applicantId);
	    	
	    	ApplicantProfile existingProfile = applicantProfileRepository.findByApplicantId(applicantId);

	        if (existingProfile == null) {
	        	applicantProfile.setApplicant(jobApplicant);
	        	applicantProfileRepository.save(applicantProfile);
	        	System.out.println("Profile saved successfully");
	        	return "Profile saved successfully";
	              
	              
	        } else {
	        	System.out.println("your Profile was updated already");
	            return "your Profile was updated already"; // Profile for this applicant already exists
	        }
	    }
	
	public Optional<ApplicantProfile>viewApplicantById(int profileId) {
		return applicantProfileRepository.findById(profileId);
	}
	
	public void deleteApplicantById(int  profileId) {
	applicantProfileRepository.deleteById(profileId);
	}
}
