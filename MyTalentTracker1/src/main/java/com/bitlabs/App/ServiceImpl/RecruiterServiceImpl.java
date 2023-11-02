package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.RecruiterService;

@Service
public class RecruiterServiceImpl implements RecruiterService{
	
	@Autowired
	private JobRecruiterRepository jobRecruiterRepository;
	
	public JobRecruiter findByEmailAddress(String recruiterEmail) {
		return jobRecruiterRepository.findByEmail(recruiterEmail);
	}

}
