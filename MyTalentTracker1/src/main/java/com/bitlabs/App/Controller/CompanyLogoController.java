package com.bitlabs.App.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.bitlabs.App.Exception.CustomException;
import com.bitlabs.App.Exception.UnsupportedFileTypeException;
import com.bitlabs.App.Service.CompanyLogoService;

@RestController
public class CompanyLogoController {

	 @Autowired
	    private CompanyLogoService companyLogoService;
		
	
		
	    @PostMapping("/companylogo/upload/{jobRecruiterId}")
	    public String fileUpload(@PathVariable Long jobRecruiterId,@RequestParam  MultipartFile logoFile)
	    {
	    	try {
	            String filename = companyLogoService.saveCompanyLogo(jobRecruiterId,logoFile);
	            return filename+ " Image uploaded successfully";
	        } 
	    	catch (CustomException ce) {
	            return  ce.getMessage();
	    	}
	    	catch (UnsupportedFileTypeException e) {
 	        return "Only JPG and PNG file types are allowed.";
 	    } 
	    	catch (MaxUploadSizeExceededException e) {
 	        return "File size should be less than 1MB.";
 	    }
	    	catch (IOException e) {
	            e.printStackTrace();
	            return "Image not uploaded successfully";
	        }
	    }
}
