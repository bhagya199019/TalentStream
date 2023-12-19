package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.bitlabs.App.Exception.CustomException;
import com.bitlabs.App.Exception.UnsupportedFileTypeException;
import com.bitlabs.App.Service.ApplicantImageService;

@RestController
public class ApplicantImageController {
	
	@Autowired
    private ApplicantImageService applicantImageService;
	
	
	private String path;
	
    @PostMapping("/{applicantId}/upload")
    public String fileUpload(@PathVariable Long applicantId,@RequestParam("photo")MultipartFile photo) 
    {
    	 try {
    	        String filename = applicantImageService.uploadImage(applicantId, photo);
    	        return "Image uploaded successfully. Filename: " + filename;
    	    } catch (CustomException ce) {
    	        return ce.getMessage();
    	    } catch (UnsupportedFileTypeException e) {
    	        return "Only JPG and PNG file types are allowed.";
    	    } catch (MaxUploadSizeExceededException e) {
    	        return "File size should be less than 1MB.";
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return "Image not uploaded successfully";
    	    }
    }
    

}
