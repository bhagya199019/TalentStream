package com.bitlabs.App.Service;

import java.util.List;
import java.util.Optional;

import com.bitlabs.App.Entity.Job;

public interface FindRecommendedJobService {
	public List<Job>findJobByMatchingSkills(int applicantProfileId);

}
