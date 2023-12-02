package com.bitlabs.App.ServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Entity.ApplicantSkills;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Repository.ApplicantProfileRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Service.SearchForaJobService;

@Service
public class SearchForaJobServiceImpl implements SearchForaJobService{
	
	@Autowired
    private ApplicantProfileRepository applicantProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    
    public Page<Job> searchJobsBySkillAndApplicant(int applicantId, String skillName, Pageable pageable) {
    	    Optional<ApplicantProfile> applicantOptional = applicantProfileRepository.findById(applicantId);
            if (applicantOptional.isPresent()) {
               return jobRepository.findJobsBySkillName(skillName, pageable);
            } else {
              List<Job> emptyJobList = Collections.emptyList();
                return new PageImpl<>(emptyJobList, pageable, 0);
            }
        }
}
