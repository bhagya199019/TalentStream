package com.bitlabs.App.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CompanyLogoService {

	public String saveCompanyLogo(long jobRecruiterId, MultipartFile logoFile) throws IOException;
}
