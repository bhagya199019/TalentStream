package com.bitlabs.App.TestControllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitlabs.App.Controller.ApplicantController;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Service.ApplicantService;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RecruiterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicantControllerTest {
	private MockMvc mockMvc;
	 
    @Mock
    private ApplicantService applicantService;
 
    @Mock
    private OtpService otpService;
 
    @Mock
    private EmailService emailService;
 
    @Mock
    private AuthenticationManager authenticationManager;
 
  
 
    @Mock
    private RecruiterService recruiterService;
 
    @Mock
    private PasswordEncoder passwordEncoder;
 
    @InjectMocks
    private ApplicantController  applicantController;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicantController).build();
    }

 
    @Test
    public void testRegister() throws Exception {
        JobApplicant jobApplicant = new JobApplicant(); // Create a sample applicant object
        when(applicantService.saveApplicant(jobApplicant)).thenReturn(ResponseEntity.ok("Registration successful"));
 
        mockMvc.perform(MockMvcRequestBuilders.post("/applicant/register-jobApplicant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jobApplicant)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
