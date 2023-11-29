package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.TeamMember;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Repository.TeamMemberRepository;
import com.bitlabs.App.Service.TeamMemberService;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

	 @Autowired
	    private TeamMemberRepository teamMemberRepository; // Assuming you have a TeamMemberRepository

	    @Autowired
	    private JobRecruiterRepository recruiterRepository; // Assuming you have a RecruiterRepository

	    public TeamMember addTeamMemberToRecruiter(Long recruiterId, TeamMember teamMember) {
	        JobRecruiter recruiter = recruiterRepository.findById(recruiterId).orElse(null);

	        if (recruiter == null) {
	            // Handle the case where the recruiter doesn't exist
	            return null; // You can return an appropriate response or throw an exception
	        }

	        teamMember.setRecruiter(recruiter); // Set the recruiter for the team member
	        TeamMember savedTeamMember = teamMemberRepository.save(teamMember);

	       
	        return savedTeamMember;
	    }
}
