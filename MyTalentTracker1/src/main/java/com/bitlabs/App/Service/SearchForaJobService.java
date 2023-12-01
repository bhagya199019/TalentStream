package com.bitlabs.App.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bitlabs.App.Entity.Job;

public interface SearchForaJobService {

	 public Page<Job> searchJobsBySkillAndApplicant(int applicantId, String skillName, Pageable pageable);
}
