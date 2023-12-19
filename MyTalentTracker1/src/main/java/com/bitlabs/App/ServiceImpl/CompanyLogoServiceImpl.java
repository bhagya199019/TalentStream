package com.bitlabs.App.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.bitlabs.App.Entity.CompanyLogo;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Exception.CustomException;
import com.bitlabs.App.Repository.CompanyLogoRepository;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.CompanyLogoService;

import jakarta.transaction.Transactional;

@Service
public class CompanyLogoServiceImpl implements CompanyLogoService {
	
	private static final long MAX_FILE_SIZE_BYTES = 1024 * 1024; // 1 MB = 1024 * 1024 bytes
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "gif"};

    	@Autowired
		private CompanyLogoRepository companyLogoRepository;
		
    	@Autowired
		private  JobRecruiterRepository jobRecruiterRepository;
	
		  @Transactional
		    public String saveCompanyLogo(long jobRecruiterId, MultipartFile logoFile) throws IOException {
		        if (!isValidFormat(logoFile.getOriginalFilename())) {
		            throw new CustomException("Image format not accepted. ", HttpStatus.BAD_REQUEST);
		        }

		        if (logoFile.getSize() > MAX_FILE_SIZE_BYTES) {
		            throw new CustomException("File size should be less than or equal to " + (MAX_FILE_SIZE_BYTES / 1024) + " KB. ", HttpStatus.BAD_REQUEST);
		        }

		        JobRecruiter recruiter = jobRecruiterRepository.findByRecruiterId(jobRecruiterId);
		        if (recruiter == null) {
		            throw new CustomException("Recruiter not found for ID: " + jobRecruiterId, HttpStatus.NOT_FOUND);
		        } else {
		            String name = StringUtils.cleanPath(logoFile.getOriginalFilename());
		            String fileName = jobRecruiterId + ".jpg"; 
		            Files.createDirectories(Paths.get("src/main/resources/public/images/company"));
		            String filePath = new File("src/main/resources/public/images/company").getAbsolutePath() + File.separator + fileName;
		            Files.copy(logoFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		            CompanyLogo existingLogo = companyLogoRepository.findByJobRecruiterRecruiterId(jobRecruiterId);
	                if (existingLogo != null) {
	                    // Image record exists, update the existing record
	                    updateCompanyLogo(existingLogo, recruiter, fileName);
	                } else {
	                    // No existing image record, insert a new record
	                    insertCompanyLogo(recruiter, fileName);
	                }
	                return logoFile.getOriginalFilename();
		    }
		  }

		    public String getFileExtension(String filename) {
		        int dotIndex = filename.lastIndexOf('.');
		        return (dotIndex == -1) ? "" : filename.substring(dotIndex);
		    }

		    private boolean isValidFormat(String filename) {
		        int dotIndex = filename.lastIndexOf('.');
		        String ext = (dotIndex == -1) ? "" : filename.substring(dotIndex + 1).toLowerCase();
		        return Arrays.asList(ALLOWED_EXTENSIONS).contains(ext);
		    }

		    private void updateCompanyLogo(CompanyLogo existingLogo, JobRecruiter recruiter, String fileName) {
		        // Update the existing record
		     
		        existingLogo.setLogoName(fileName);
		        existingLogo.setJobRecruiter(recruiter);
		        companyLogoRepository.save(existingLogo);
		    }

		    private void insertCompanyLogo(JobRecruiter recruiter, String fileName) {
		        // Insert a new record
		    
               CompanyLogo companyLogo = new CompanyLogo();
		        companyLogo.setLogoName(fileName);
		        companyLogo.setJobRecruiter(recruiter);
		        companyLogoRepository.save(companyLogo);
		    }

}
