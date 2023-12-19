package com.bitlabs.App.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bitlabs.App.Entity.ApplicantImage;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Exception.CustomException;
import com.bitlabs.App.Repository.ApplicantImageRepository;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.ApplicantImageService;

@Service
public class ApplicantImageServiceImpl implements ApplicantImageService {
	
	
	private final Path root = Paths.get("applicantprofileimages");
	@Autowired
	private ApplicantImageRepository applicantImageRepository;
	
	@Autowired
	private  JobApplicantRepository applicantRepository;
	
	public String uploadImage(long applicantId, MultipartFile imageFile) {
		 
        JobApplicant applicant = applicantRepository.getJobApplicantById(applicantId);
        if (applicant == null)
            throw new CustomException("Applicant not found for ID: " + applicantId, HttpStatus.NOT_FOUND);
        else {
            if (applicantImageRepository.existsByApplicant(applicant)) {
                throw new CustomException("An image already exists for the applicant.", HttpStatus.BAD_REQUEST);
            }
 
            if (imageFile.getSize() > 1 * 1024 * 1024) {
                throw new CustomException("File size should be less than 1MB.", HttpStatus.BAD_REQUEST);
            }
            String contentType = imageFile.getContentType();
            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
                throw new CustomException("Only JPG and PNG file types are allowed.", HttpStatus.BAD_REQUEST);
            }
 
            String name = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String fileName = applicantId + "_" + name;
 
            String folderPath = "src/main/resources/applicant/photos";
            String filePath = Paths.get(folderPath, fileName).toString();
 
            try {
                Files.createDirectories(Paths.get(folderPath)); // Create directories if they don't exist
                Files.copy(imageFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            ApplicantImage applicantImage = new ApplicantImage();
            applicantImage.setImagename(fileName);
            applicantImage.setApplicant(applicant);
            applicantImageRepository.save(applicantImage);
 
            return name;
        }
    }
 

}
