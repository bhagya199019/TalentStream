package com.bitlabs.App.Service;

import com.bitlabs.App.Entity.JobRecruiter;

public interface RecruiterService {
	public JobRecruiter findByEmailAddress(String recruiterEmail);
}
