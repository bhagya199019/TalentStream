package com.bitlabs.App.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.ApplicantProfile;
import com.bitlabs.App.Entity.ApplicantSkills;
import com.bitlabs.App.Entity.Job;
import com.bitlabs.App.Repository.ApplicantProfileRepository;
import com.bitlabs.App.Repository.JobRepository;
import com.bitlabs.App.Service.FindRecommendedJobService;


@Service
public class FindRecommendedJobServiceimpl implements FindRecommendedJobService {
	
	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	public List<Job>findJobByMatchingSkills(int applicantProfileId){
		
		ApplicantProfile applicantProfile= applicantProfileRepository.findByApplicantId(applicantProfileId);
		
		Set<ApplicantSkills>applicantSkills=applicantProfile.getSkillsRequired();
		
		Set<String>lowerCaseApplicantSkills=applicantSkills.stream().map(skills->skills.getSkillName().toLowerCase())
				.collect(Collectors.toSet());
		
		List<Job>matchingJob=jobRepository.findBySkillsRequiredIgnoreCaseAndSkillNameIn(lowerCaseApplicantSkills);
		
		return matchingJob;
		
	}
	
	
	
	
	
	

}
