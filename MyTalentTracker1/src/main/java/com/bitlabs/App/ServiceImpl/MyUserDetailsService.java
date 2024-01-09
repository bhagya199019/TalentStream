package com.bitlabs.App.ServiceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRecruiterRepository;


@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private JobRecruiterRepository recruiterRepository;

    @Autowired
    private JobApplicantRepository applicantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First, try to find a JobRecruiter by email
        JobRecruiter jobRecruiter = recruiterRepository.findByEmail(username);
        
        if (jobRecruiter != null) {
          	System.out.println("Recruiter");
            return new User(
                jobRecruiter.getEmail(),
                jobRecruiter.getPassword(),
                Arrays.stream(jobRecruiter.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
            );
        }
        
        // If not found, try to find an ApplicantRegistration by email
       JobApplicant applicant = applicantRepository.findByEmail(username);
        
        if (applicant != null) {
        	System.out.println("Applicant");
            return new User(
                applicant.getEmail(),
                applicant.getPassword(),
                Arrays.stream(applicant.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
            );
        }

        // Neither a recruiter nor an applicant with this email was found
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}

