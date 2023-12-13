package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitlabs.App.Entity.CompanyLogo;

public interface CompanyLogoRepository extends JpaRepository<CompanyLogo,Integer> {
	
	CompanyLogo findByJobRecruiterRecruiterId(long jobRecruiterId);

}
