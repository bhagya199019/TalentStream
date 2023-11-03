package com.bitlabs.App.Service;

import java.util.Optional;

import com.bitlabs.App.Entity.RecruiterProfile;

public interface RecruiterProfileService {
	public RecruiterProfile saveRecruiterProfile(RecruiterProfile recruiterProfile);
	public Optional<RecruiterProfile> getRecruiterProfileById (Long recruiterProfileId);

}
