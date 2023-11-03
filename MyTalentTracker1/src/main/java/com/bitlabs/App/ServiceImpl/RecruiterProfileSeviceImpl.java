package com.bitlabs.App.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.RecruiterProfile;
import com.bitlabs.App.Repository.RecruiterProfileRepository;
import com.bitlabs.App.Service.RecruiterProfileService;

@Service
public class RecruiterProfileSeviceImpl implements RecruiterProfileService {
	
	@Autowired
	private RecruiterProfileRepository recruiterProfileRepository;
	
	public RecruiterProfile saveRecruiterProfile(RecruiterProfile recruiterProfile) {
		return recruiterProfileRepository.save(recruiterProfile);
		
	}
	
	public Optional<RecruiterProfile> getRecruiterProfileById (Long recruiterProfileId) {
		return recruiterProfileRepository.findById(recruiterProfileId);
	}

}
