package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender javaMailSender;
    
    public void sendOtpEmail(String toEmail,String otp) {
    	SimpleMailMessage simpleMessage=new SimpleMailMessage();
    	simpleMessage.setTo(toEmail);
    	simpleMessage.setText("Your OTP is"+otp);
    	javaMailSender.send(simpleMessage);
}
}
