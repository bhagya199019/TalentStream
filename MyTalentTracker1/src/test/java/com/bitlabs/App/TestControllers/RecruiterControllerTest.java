package com.bitlabs.App.TestControllers;



/* import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitlabs.App.Controller.ApplicantController;
import com.bitlabs.App.Controller.RecruiterController;
import com.bitlabs.App.Entity.JobApplicant;
import com.bitlabs.App.Entity.JobRecruiter;
import com.bitlabs.App.Entity.Login;
import com.bitlabs.App.Repository.JobApplicantRepository;
import com.bitlabs.App.Repository.JobRecruiterRepository;
import com.bitlabs.App.Service.EmailService;
import com.bitlabs.App.Service.OtpService;
import com.bitlabs.App.Service.RecruiterService;
import com.bitlabs.App.ServiceImpl.OtpServiceImpl.OtpData;
import com.bitlabs.App.dto.NewPasswordRequestDTO;
import com.bitlabs.App.dto.OtpVerificationDTO;
import com.bitlabs.App.dto.SendOtpDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class RecruiterControllerTest {
	
	private MockMvc mockMvc;
	 
    @Mock
    private RecruiterService recruiterService;
 
    @Mock
    private OtpService otpService;
 
    @Mock
    private EmailService emailService;
 
    @Mock
    private AuthenticationManager authenticationManager;
 
 
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private JobRecruiterRepository jobRecruiterRepository;
 
    @InjectMocks
    private RecruiterController  recruiterController;
 
     private Map<String, OtpData> otpMap;
     
     private Map<String, Boolean> otpVerificationMap;
     
     
     @BeforeEach
     public void setUp() {
         mockMvc = MockMvcBuilders.standaloneSetup(recruiterController).build();
         MockitoAnnotations.openMocks(this);
         otpMap = new HashMap<>();
         otpVerificationMap = new HashMap<>();
     }
     
    
     @Test
     void testSendOtpSuccess() {
         // Mock data
         SendOtpDTO request = new SendOtpDTO();
         request.setEmail("newrecruiter@example.com");

         // Mock behavior for recruiter not found
         when(recruiterService.findByEmailAddress(anyString())).thenReturn(null);

         // Mock behavior for OTP generation
         when(otpService.generateOtp(anyString())).thenReturn("123456");

         // Perform the request
         ResponseEntity<String> response = recruiterController.sendOtp(request);

         // Verify the result
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("OTP sent to your email.", response.getBody());

         // Verify that the service methods were called with the correct arguments
         verify(recruiterService, times(1)).findByEmailAddress("newrecruiter@example.com");
         verify(otpService, times(1)).generateOtp("newrecruiter@example.com");
         verify(emailService, times(1)).sendOtpEmail("newrecruiter@example.com", "123456");
     }
    
    
     
     @Test
     public void testSendOtpFailureEmailAlreadyRegistered() {
         // Arrange
         SendOtpDTO request = new SendOtpDTO();
         request.setEmail("existinguser@example.com");

         JobRecruiter jobRecruiter = new JobRecruiter(); // Assuming the email is already registered
         when(recruiterService.findByEmailAddress(anyString())).thenReturn(jobRecruiter);

         // Act
         ResponseEntity<String> response = recruiterController.sendOtp(request);

         // Assert
         assertThat(response.getBody(),equalToIgnoringWhiteSpace("Email is already registered."));
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

         // Verify that the service methods were not called
         verify(otpService, never()).generateOtp(anyString());
         verify(emailService, never()).sendOtpEmail(anyString(), anyString());
     }

     @Test
     void testVerifyOtpSuccess() {
         // Arrange
         OtpVerificationDTO request = new OtpVerificationDTO();
         request.setEmail("user@example.com");
         request.setOtp("123456");

         // Mock the behavior of the OtpService to return true for a valid OTP
         when(otpService.validateOtp(eq("user@example.com"), eq("123456"))).thenReturn(true);

         // Act
         ResponseEntity<String> response = recruiterController.verifyOtp(request);

         // Assert
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("OTP verified successfully", response.getBody());

         // Verify that the validateOtp method was called with the correct arguments
         verify(otpService, times(1)).validateOtp(eq("user@example.com"), eq("123456"));
     }
     
     
     @Test
     void testVerifyOtpFailureIncorrectOtp() {
         // Arrange
         OtpVerificationDTO request = new OtpVerificationDTO();
         request.setEmail("user@example.com");
         request.setOtp("654321");

         // Mock the behavior of the OtpService to return false for an incorrect OTP
         when(otpService.validateOtp(eq("user@example.com"), eq("654321"))).thenReturn(false);

         // Act
         ResponseEntity<String> response = recruiterController.verifyOtp(request);

         // Assert
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertEquals("Incorrect OTP.", response.getBody());

         // Verify that the validateOtp method was called with the correct arguments
         verify(otpService, times(1)).validateOtp(eq("user@example.com"), eq("654321"));
     }
     
     
     @Test
     void testVerifyOtpFailureInvalidEmail() {
         // Arrange
         OtpVerificationDTO request = new OtpVerificationDTO();
         request.setEmail("invalid@example.com");
         request.setOtp("123456");

         // Mock the behavior of the OtpService to return false for an invalid email
         when(otpService.validateOtp(eq("invalid@example.com"), eq("123456"))).thenReturn(false);

         // Act
         ResponseEntity<String> response = recruiterController.verifyOtp(request);

         // Assert
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertEquals("Incorrect OTP.", response.getBody());

         // Verify that the validateOtp method was called with the correct arguments
         verify(otpService, times(1)).validateOtp(eq("invalid@example.com"), eq("123456"));
     }
     
     
  // Test method to verify the successful registration of a JobRecruiter
     @Test
     public void testSaveRecruiterSuccess() {
         // Create a new instance of JobRecruiter
         JobRecruiter jobRecruiter = new JobRecruiter();

         // Mock the behavior of the recruiterService's saveRecruiter method
         // to return a ResponseEntity with "Registration successful" message
         when(recruiterService.saveRecruiter(any(JobRecruiter.class)))
                 .thenReturn(ResponseEntity.ok("Registration successful"));

         // Call the register method of the recruiterController with the mock JobRecruiter
         ResponseEntity<String> response = recruiterController.registerjobRecruiter(jobRecruiter);

         // Assertions to check the response status and body
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("Registration successful", response.getBody());

         // Verify that the saveRecruiter method of recruiterService was called exactly once
         // with any instance of JobRecruiter as an argument
         verify(recruiterService, times(1)).saveRecruiter(any(JobRecruiter.class));
     }

     
     @Test
     public void testSaveRecruiterFailureResponseStatusException() {
         // Arrange
         JobRecruiter jobRecruiter = new JobRecruiter();

         // Mock the behavior of recruiterService.saveRecruiter to throw a RuntimeException
         when(recruiterService.saveRecruiter(any(JobRecruiter.class)))
                 .thenThrow(new RuntimeException("Some error message"));

         try {
             // Act
             ResponseEntity<String> response = recruiterController.registerjobRecruiter(jobRecruiter);

             // If you reach this point, the exception wasn't thrown as expected
             fail("Expected RuntimeException, but it wasn't thrown");
         } catch (RuntimeException e) {
             // Assert the exception message or any other details as needed
             assertEquals("Some error message", e.getMessage());

             // Verify that the service method was called
             verify(recruiterService, times(1)).saveRecruiter(any(JobRecruiter.class));
         }
     }

     @Test
   void testLoginRecruiterSuccess() throws Exception {
         // Arrange
         Login loginRequest = new Login();
         loginRequest.setEmail("test@example.com");
         loginRequest.setPassword("password");  // Set the password

         JobRecruiter mockJobRecruiter = new JobRecruiter();
         mockJobRecruiter.setEmail("test@example.com");
         mockJobRecruiter.setPassword(passwordEncoder.encode("password"));

         // Mock the repository and encoder behavior
         when(jobRecruiterRepository.findByEmail(any()))
                 .thenReturn(mockJobRecruiter);
         when(passwordEncoder.matches(any(), any()))
                 .thenReturn(true);

         // Act
         ResponseEntity<String> response = recruiterController.loginRecruiter(loginRequest);

         // Assert
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("Login successfully", response.getBody());

         // Verify that findByEmail and passwordEncoder.matches were called with the correct arguments
         verify(jobRecruiterRepository, times(1)).findByEmail("test@example.com");
         verify(passwordEncoder, times(1)).matches(eq("password"), any());
     }
     
     
     @Test
   void testLoginRecruiterFailureIncorrectpassword() throws Exception {
         // Arrange
         Login loginRequest = new Login();
         loginRequest.setEmail("test@example.com");
         loginRequest.setPassword("wrong_password");  // Set an incorrect password

         JobRecruiter mockJobRecruiter = new JobRecruiter();
         mockJobRecruiter.setEmail("test@example.com");
         mockJobRecruiter.setPassword(passwordEncoder.encode("correct_password"));  // Set the correct password

         // Mock the repository and encoder behavior
         when(jobRecruiterRepository.findByEmail(any()))
                 .thenReturn(mockJobRecruiter);
         when(passwordEncoder.matches(any(), any()))
                 .thenReturn(false);  // Simulate incorrect password

         // Act
         ResponseEntity<String> response = recruiterController.loginRecruiter(loginRequest);

         // Assert
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertEquals("Please enter the correct password", response.getBody());

         // Verify that findByEmail and passwordEncoder.matches were called with the correct arguments
         verify(jobRecruiterRepository, times(1)).findByEmail("test@example.com");
         verify(passwordEncoder, times(1)).matches(eq("wrong_password"), any());
     }


     @Test
     void testLoginRecruiterUserNotFoundFailure() throws Exception {
         // Arrange
         Login loginRequest = new Login();
         loginRequest.setEmail("nonexistent@example.com");
         loginRequest.setPassword("some_password");

         // Mock the repository to return null, simulating a user not found scenario
         when(jobRecruiterRepository.findByEmail(any()))
                 .thenReturn(null);

         // Act
         ResponseEntity<String> response = recruiterController.loginRecruiter(loginRequest);

         // Assert
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertEquals("User not found", response.getBody());

         // Verify that findByEmail was called with the correct arguments
         verify(jobRecruiterRepository, times(1)).findByEmail("nonexistent@example.com");

         // Verify that passwordEncoder.matches was not invoked in this case
         verify(passwordEncoder, never()).matches(any(), any());
     }


     @Test
     void testForgotPasswordSendOtpSuccess() {
         // Arrange
         SendOtpDTO request = new SendOtpDTO();
         request.setEmail("test@example.com");

         JobRecruiter mockJobRecruiter = new JobRecruiter();
         mockJobRecruiter.setEmail("test@example.com");

         // Mock the service behavior
         when(recruiterService.findByEmailAddress(any()))
                 .thenReturn(mockJobRecruiter);

         when(otpService.generateOtp(any()))
                 .thenReturn("123456"); // Assuming a fixed OTP for testing

         // Act
         ResponseEntity<String> response = recruiterController.ForgotPasswordSendOtp(request);

         // Assert
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("OTP sent to your Email", response.getBody());

         // Verify that findByEmailAddress and other relevant methods were called with the correct arguments
         verify(recruiterService, times(1)).findByEmailAddress("test@example.com");
         verify(otpService, times(1)).generateOtp("test@example.com");
         verify(emailService, times(1)).sendOtpEmail("test@example.com", "123456");
     }
     
     
     @Test
     void testForgotPasswordSendOtpFailure() {
         // Arrange
         SendOtpDTO request = new SendOtpDTO();
         request.setEmail("nonexistent@example.com");

         // Mock the service behavior to return null, simulating a user not found scenario
         when(recruiterService.findByEmailAddress(any()))
                 .thenReturn(null);

         // Act
         ResponseEntity<String> response = recruiterController.ForgotPasswordSendOtp(request);

         // Assert
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         assertEquals("Recruiter not registered", response.getBody().trim());

         // Verify that findByEmailAddress and other relevant methods were called with the correct arguments
         verify(recruiterService, times(1)).findByEmailAddress("nonexistent@example.com");
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

         JobRecruiter mockRecruiter = new JobRecruiter();
         mockRecruiter.setEmail(email);

         // Mock behaviors
         when(recruiterService.findByEmailAddress(email)).thenReturn(mockRecruiter);
         when(passwordEncoder.encode(newPassword)).thenReturn("encodedPassword");
         when(jobRecruiterRepository.save(any(JobRecruiter.class))).thenReturn(mockRecruiter);

         // Perform the request
         ResponseEntity<String> response = recruiterController.resetPassword(request, email);

         assertEquals("Password reset done successfully", response.getBody());
     }
     
     
     @Test
     public void testSetNewPasswordUserNotFound() {
         // Mock data
         String email = "nonexistent@example.com";
         NewPasswordRequestDTO request = new NewPasswordRequestDTO();

         // Mock behavior for user not found
         when(recruiterService.findByEmailAddress(email)).thenReturn(null);

         // Perform the request
         ResponseEntity<String> response = recruiterController.resetPassword(request, email);

         // Verify the result
         assertEquals("User not found", response.getBody());
     }
     
     
     @Test
     public void testSetNewPasswordPasswordNotMatch() {
         // Mock data
         String email = "test@example.com";
         NewPasswordRequestDTO request = new NewPasswordRequestDTO();
         request.setNewPassword("password"); // Set a non-null password for testing
         request.setConfirmPassword("differentPassword"); // Set a different confirmation password

         JobRecruiter mockRecruiter = new  JobRecruiter();
         mockRecruiter.setEmail(email);

         // Mock behaviors
         when(recruiterService.findByEmailAddress(email)).thenReturn(mockRecruiter);

         // Perform the request
         ResponseEntity<String> response = recruiterController.resetPassword(request, email);

         // Verify the result
         assertEquals("Passwords do not match", response.getBody());
     }

}
*/