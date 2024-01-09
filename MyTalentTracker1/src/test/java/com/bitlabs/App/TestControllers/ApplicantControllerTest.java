package com.bitlabs.App.TestControllers;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitlabs.App.Controller.ApplicantController;
import com.bitlabs.App.Entity.AuthenticationResponse;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Service.ApplicantService;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RecruiterService;
import com.bitlabs.App.ServiceImpl.JwtUtilService;
import com.bitlabs.App.ServiceImpl.MyUserDetailsService;
import com.bitlabs.App.ServiceImpl.OtpServiceImpl;
import com.bitlabs.App.ServiceImpl.OtpServiceImpl.OtpData;
import com.bitlabs.App.dto.NewPasswordRequestDTO;
import com.bitlabs.App.dto.OtpVerificationDTO;
import com.bitlabs.App.dto.SendOtpDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
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
    private MyUserDetailsService myUserDetailsService;

    @Mock
     JwtUtilService jwtTokenUtil;

    @Mock
    private RecruiterService recruiterService;
 
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private JobApplicantRepository jobApplicantRepository;
 
    @InjectMocks
    private ApplicantController  applicantController;
 
    
    
    private Map<String, OtpData> otpMap;
    
 
 
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicantController).build();
        MockitoAnnotations.openMocks(this);
        otpMap = new HashMap<>();
    }
    

 
 // Test method to verify the successful registration of a JobApplicant
    @Test
    public void testSaveApplicantSuccess() {
        // Create a new instance of JobApplicant
        JobApplicant jobApplicant = new JobApplicant();

        // Mock the behavior of the applicantService's saveApplicant method
        // to return a ResponseEntity with "Registration successful" message
        when(applicantService.saveApplicant(any(JobApplicant.class)))
                .thenReturn(ResponseEntity.ok("Registration successful"));

        // Call the register method of the applicantController with the mock JobApplicant
        ResponseEntity<String> response = applicantController.register(jobApplicant);

        // Assertions to check the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration successful", response.getBody());

        // Verify that the saveApplicant method of applicantService was called exactly once
        // with any instance of JobApplicant as an argument
        verify(applicantService, times(1)).saveApplicant(any(JobApplicant.class));
    }
    
    @Test
    public void testSaveApplicantFailureResponseStatusException() {
        // Arrange
        JobApplicant jobApplicant = new JobApplicant();

        // Mock the behavior of applicantService.saveApplicant to throw a RuntimeException
        when(applicantService.saveApplicant(any(JobApplicant.class)))
                .thenThrow(new RuntimeException("Some error message"));

        try {
            // Act
            ResponseEntity<String> response = applicantController.register(jobApplicant);

            // If you reach this point, the exception wasn't thrown as expected
            fail("Expected RuntimeException, but it wasn't thrown");
        } catch (RuntimeException e) {
            // Assert the exception message or any other details as needed
            assertEquals("Some error message", e.getMessage());

            // Verify that the service method was called
            verify(applicantService, times(1)).saveApplicant(any(JobApplicant.class));
        }
    }

    @Test
    public void testSendOtpSuccess() {
        // Arrange
        SendOtpDTO request = new SendOtpDTO();
        request.setEmail("newuser@example.com");

        JobApplicant jobApplicant = null; // Assuming the email is not registered
        when(applicantService.findByEmailAddress(anyString())).thenReturn(jobApplicant);

        // Mock the behavior of otpService.generateOtp to return a non-null value
        when(otpService.generateOtp(anyString())).thenReturn("123456");

        // Act
        ResponseEntity<String> response = applicantController.sendOtp(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OTP sent to your email.", response.getBody());

        // Verify that the service method was called
        verify(otpService, times(1)).generateOtp(anyString());
        // Verify that the emailService method was called with a non-null OTP
        verify(emailService, times(1)).sendOtpEmail(eq("newuser@example.com"), eq("123456"));
    }

    
    @Test
    public void testSendOtpFailureEmailAlreadyRegistered() {
        // Arrange
        SendOtpDTO request = new SendOtpDTO();
        request.setEmail("existinguser@example.com");

        JobApplicant jobApplicant = new JobApplicant(); // Assuming the email is already registered
        when(applicantService.findByEmailAddress(anyString())).thenReturn(jobApplicant);

        // Act
        ResponseEntity<String> response = applicantController.sendOtp(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is already registered.", response.getBody());

        // Verify that the service methods were not called
        verify(otpService, never()).generateOtp(anyString());
        verify(emailService, never()).sendOtpEmail(anyString(), anyString());
    }

    
    @Test
    void testVerifyOtpSuccess() {
        // Arrange
        OtpVerificationDTO verificationRequest = new OtpVerificationDTO();
        verificationRequest.setEmail("test@example.com");
        verificationRequest.setOtp("123456");

        when(otpService.validateOtp(eq("test@example.com"), eq("123456"))).thenReturn(true);

        // Act
        ResponseEntity<String> response = applicantController.verifyOtp(verificationRequest);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.ok("OTP verified successfully"), response);
        // Add more assertions if needed
    }
    
    
    @Test
    void testVerifyOtpFailure() {
        // Arrange
        OtpVerificationDTO verificationRequest = new OtpVerificationDTO();
        verificationRequest.setEmail("test@example.com");
        verificationRequest.setOtp("654321");

        when(otpService.validateOtp(eq("test@example.com"), eq("654321"))).thenReturn(false);

        // Act
        ResponseEntity<String> response = applicantController.verifyOtp(verificationRequest);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.badRequest().body("Incorrect OTP."), response);
       
    }
    
    
    @Test
    void testValidateOtpSuccess() {
        // Arrange
        OtpServiceImpl otpService = new OtpServiceImpl();
        String userEmail = "test@example.com";
        String generatedOtp = otpService.generateOtp(userEmail);

        // Act
        boolean validationResult = otpService.validateOtp(userEmail, generatedOtp);

        // Assert
        assertTrue(validationResult, "Validation of OTP should succeed");
    }
   
    
    @Test
    void testValidateOtpFailure() {
        // Arrange
        OtpServiceImpl otpService = new OtpServiceImpl();
        String userEmail = "test@example.com";
        String correctOtp = "123456";
        String enteredOtp = "654321";

        // Generate a correct OTP and add it to the otpMap
        String generatedOtp = otpService.generateOtp(userEmail);
        assertTrue(otpService.validateOtp(userEmail, generatedOtp), "Precondition: Correct OTP should be valid");

        // Act
        boolean result = otpService.validateOtp(userEmail, enteredOtp);

        // Assert
        assertFalse(result, "Validation of incorrect OTP should fail");
        assertTrue(otpService.isOtpExpired(userEmail), "OTP should be marked as expired");
    }
    
    
        
 /*   @Test
    void testLoginApplicantSuccess() throws Exception {
        // Arrange
        Login loginRequest = new Login();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");  // Set the password

        JobApplicant mockJobApplicant = new JobApplicant();
        mockJobApplicant.setEmail("test@example.com");
        mockJobApplicant.setPassword(passwordEncoder.encode("password"));

        // Mock the repository and encoder behavior
        when(jobApplicantRepository.findByEmail(any()))
                .thenReturn(mockJobApplicant);
        when(passwordEncoder.matches(any(), any()))
                .thenReturn(true);

        // Act
        ResponseEntity<String> response = applicantController.loginApplicant(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successfully", response.getBody());

        // Verify that findByEmail and passwordEncoder.matches were called with the correct arguments
        verify(jobApplicantRepository, times(1)).findByEmail("test@example.com");
        verify(passwordEncoder, times(1)).matches(eq("password"), any());
    }
    
    
    @Test
    void testLoginApplicantSuccess() throws Exception {
        // Arrange
        Login loginRequest = new Login();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        JobApplicant mockJobApplicant = new JobApplicant();
        mockJobApplicant.setEmail("test@example.com");
        mockJobApplicant.setPassword(passwordEncoder.encode("password"));

        when(jobApplicantRepository.findByEmail(any()))
                .thenReturn(mockJobApplicant);
        when(passwordEncoder.matches(any(), any()))
                .thenReturn(true);

        // Mock authenticationManager behavior
        doNothing().when(authenticationManager).authenticate(any());

        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        when(myUserDetailsService.loadUserByUsername(any()))
                .thenReturn(userDetails);

        when(jwtTokenUtil.generateToken(any(UserDetails.class)))
                .thenReturn("mockJwtToken");

        // Act
        ResponseEntity<Object> responseEntity = applicantController.loginApplicant(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        
     // Modify the assertions based on your actual response structure
        assertEquals("Login successfully", "Login successfully");
        
     // Assert the JWT field
        assertEquals("Expected JWT token", "mockJwtToken", ((AuthenticationResponse) responseEntity.getBody()).getJwt());

        // Verify that findByEmail, passwordEncoder.matches, authenticationManager.authenticate, and loadUserByUsername were called
        verify(jobApplicantRepository, times(1)).findByEmail("test@example.com");
        verify(passwordEncoder, times(1)).matches(eq("password"), any());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(myUserDetailsService, times(1)).loadUserByUsername("test@example.com");
        verify(jwtTokenUtil, times(1)).generateToken(userDetails);
    }


    
    @Test
    void testLoginApplicantFailureIncorrectpassword() throws Exception {
        // Arrange
        Login loginRequest = new Login();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("wrong_password");  // Set an incorrect password

        JobApplicant mockJobApplicant = new JobApplicant();
        mockJobApplicant.setEmail("test@example.com");
        mockJobApplicant.setPassword(passwordEncoder.encode("correct_password"));  // Set the correct password

        // Mock the repository and encoder behavior
        when(jobApplicantRepository.findByEmail(any()))
                .thenReturn(mockJobApplicant);
        when(passwordEncoder.matches(any(), any()))
                .thenReturn(false);  // Simulate incorrect password

        // Act
        ResponseEntity<String> response = applicantController.loginApplicant(loginRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please enter the correct password", response.getBody());

        // Verify that findByEmail and passwordEncoder.matches were called with the correct arguments
        verify(jobApplicantRepository, times(1)).findByEmail("test@example.com");
        verify(passwordEncoder, times(1)).matches(eq("wrong_password"), any());
    }

    
    @Test
    void testLoginApplicantUserNotFoundFailure() throws Exception {
        // Arrange
        Login loginRequest = new Login();
        loginRequest.setEmail("nonexistent@example.com");
        loginRequest.setPassword("some_password");

        // Mock the repository to return null, simulating a user not found scenario
        when(jobApplicantRepository.findByEmail(any()))
                .thenReturn(null);

        // Act
        ResponseEntity<String> response = applicantController.loginApplicant(loginRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // Verify that findByEmail was called with the correct arguments
        verify(jobApplicantRepository, times(1)).findByEmail("nonexistent@example.com");

        // Verify that passwordEncoder.matches was not invoked in this case
        verify(passwordEncoder, never()).matches(any(), any());
    }


    @Test
    void testForgotPasswordSendOtpSuccess() {
        // Arrange
        SendOtpDTO request = new SendOtpDTO();
        request.setEmail("test@example.com");

        JobApplicant mockJobApplicant = new JobApplicant();
        mockJobApplicant.setEmail("test@example.com");

        // Mock the service behavior
        when(applicantService.findByEmailAddress(any()))
                .thenReturn(mockJobApplicant);

        when(otpService.generateOtp(any()))
                .thenReturn("123456"); // Assuming a fixed OTP for testing

        // Act
        ResponseEntity<String> response = applicantController.ForgotPasswordSendOtp(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OTP sent to your Email", response.getBody());

        // Verify that findByEmailAddress and other relevant methods were called with the correct arguments
        verify(applicantService, times(1)).findByEmailAddress("test@example.com");
        verify(otpService, times(1)).generateOtp("test@example.com");
        verify(emailService, times(1)).sendOtpEmail("test@example.com", "123456");
    }
    
    
    @Test
    void testForgotPasswordSendOtpFailure() {
        // Arrange
        SendOtpDTO request = new SendOtpDTO();
        request.setEmail("nonexistent@example.com");

        // Mock the service behavior to return null, simulating a user not found scenario
        when(applicantService.findByEmailAddress(any()))
                .thenReturn(null);

        // Act
        ResponseEntity<String> response = applicantController.ForgotPasswordSendOtp(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Applicant email not registered", response.getBody().trim());

        // Verify that findByEmailAddress and other relevant methods were called with the correct arguments
        verify(applicantService, times(1)).findByEmailAddress("nonexistent@example.com");
        verify(otpService, never()).generateOtp(any());
        verify(emailService, never()).sendOtpEmail(any(), any());
    }
    
    @Test
    public void testSetNewPasswordSuccess() {
        // Mock data
        String email = "test@example.com";
        String newPassword = "newPassword";
        String confirmedPassword = "newPassword";
        
        // Create NewPasswordRequestDTO and set newPassword and confirmedPassword
        NewPasswordRequestDTO request = new NewPasswordRequestDTO();
        request.setNewPassword(newPassword);  // Assuming Lombok generates setNewPassword method
        request.setConfirmPassword(confirmedPassword);  // Assuming Lombok generates setConfirmPassword method

        JobApplicant mockApplicant = new JobApplicant();
        mockApplicant.setEmail(email);

        // Mock behaviors
        when(applicantService.findByEmailAddress(email)).thenReturn(mockApplicant);
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedPassword");
        when(jobApplicantRepository.save(any(JobApplicant.class))).thenReturn(mockApplicant);

        // Perform the request
        ResponseEntity<String> response = applicantController.setNewPassword(request, email);

        // Verify the result
        if (newPassword != null) {
            assertEquals("Password reset was done successfully", response.getBody());
        } else {
            // Handle the case where newPassword is null
            assertEquals("Bad request for null password", response.getBody());
        }
    }
    
    @Test
    public void testSetNewPasswordUserNotFound() {
        // Mock data
        String email = "nonexistent@example.com";
        NewPasswordRequestDTO request = new NewPasswordRequestDTO();

        // Mock behavior for user not found
        when(applicantService.findByEmailAddress(email)).thenReturn(null);

        // Perform the request
        ResponseEntity<String> response = applicantController.setNewPassword(request, email);

        // Verify the result
        assertEquals("User not found.", response.getBody());
    }

    @Test
    public void testSetNewPasswordPasswordNotMatch() {
        // Mock data
        String email = "test@example.com";
        NewPasswordRequestDTO request = new NewPasswordRequestDTO();
        request.setNewPassword("password"); // Set a non-null password for testing
        request.setConfirmPassword("differentPassword"); // Set a different confirmation password

        JobApplicant mockApplicant = new JobApplicant();
        mockApplicant.setEmail(email);

        // Mock behaviors
        when(applicantService.findByEmailAddress(email)).thenReturn(mockApplicant);

        // Perform the request
        ResponseEntity<String> response = applicantController.setNewPassword(request, email);

        // Verify the result
        assertEquals("password not match", response.getBody());
    }

}
*/
