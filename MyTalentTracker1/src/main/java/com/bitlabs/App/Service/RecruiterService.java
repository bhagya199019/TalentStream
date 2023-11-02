package com.bitlabs.App.Service;

import org.springframework.http.ResponseEntity;

import com.bitlabs.App.Entity.JobRecruiter;


public interface RecruiterService {
	public JobRecruiter findByEmailAddress(String recruiterEmail);
	public ResponseEntity<String> saveRecruiter(JobRecruiter jobRecruiter);
}
