package com.bitlabs.App.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ApplicantImageService {
	public String uploadImage(long applicantId, MultipartFile imageFile);
}
