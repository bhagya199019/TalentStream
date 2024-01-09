package com.bitlabs.App.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bitlabs.App.filter.JwtRequestFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	// @Autowired
//	  private AuthEntryPointJwt unauthorizedHandler;

	
	@Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
	    return authConfiguration.getAuthenticationManager();
	  }
	
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder()); 
	}
	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) {
//	    auth.authenticationProvider(authenticationProvider());
//	}
// 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
    }
	
	
//	protected void configure (HttpSecurity httpSecurity) throws Exception {
//
//		httpSecurity.csrf(csrf -> csrf.disable())
//		
//     
//	    .authorizeHttpRequests(auth -> 
//        {
//			try {
//				auth.requestMatchers("/applicant/send-otp").permitAll().
//				requestMatchers("/applicant/applyjob/{applicantId}/{jobId}", "/applicant/findRecommendedJobsBySkills/{applicantProfileId}", "/applicant/createProfile/{applicantId}", "/applicant/getapplicantProfileDetails/{profileid}", "/applicant/saveJob/{applicantId}/{jobId}", "/applicant/viewJob/{jobId}", "/applicant/applyJob/getAppliedJobs/{applicantId}", "/applicant/searchjobbyskillname/{applicantId}/jobs/{skillName}").hasAnyRole("JOBAPPLICANT").
//				        //    requestMatchers("/recruiters", "/signOut", "/applicant/forgotpassword/sendotp", "/recruiterLogin", "/recruiters/send-otp", "/verify-otp", "/saveApplicant", "/registration-send-otp", "/applicant/login", "/applicant/send-otp", "/applicant/verify-otp", "/applicants/reset-password/{email}", "/recruiters/reset-password/set-new-password/{email}").permitAll()
//				            // Add other antMatchers and roles as needed
//				         anyRequest().authenticated()
//				        .and()
//				        .exceptionHandling()
//				        .and()
//				        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//
//		//   httpSecurity.authenticationProvider(authenticationProvider());
//	    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	    
//	 //   return httpSecurity.build();
//	}
//	
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests(auth -> auth
//                        .requestMatchers(request -> 
//                            ((Object) request)
//                                    .antMatchers("/applicant/applyjob/{applicantId}/{jobId}", "/applicant/findRecommendedJobsBySkills/{applicantProfileId}", "/applicant/createProfile/{applicantId}", "/applicant/getapplicantProfileDetails/{profileid}", "/applicant/saveJob/{applicantId}/{jobId}", "/applicant/viewJob/{jobId}", "/applicant/applyJob/getAppliedJobs/{applicantId}", "/applicant/searchjobbyskillname/{applicantId}/jobs/{skillName}")
//                                    .hasAnyRole("JOBAPPLICANT"),
//                            request -> 
//                            request
//                                    .antMatchers("/recruiters", "/signOut", "/applicant/forgotpassword/sendotp", "/recruiterLogin", "/recruiters/send-otp", "/verify-otp", "/saveApplicant", "/registration-send-otp", "/applicant/login", "/applicant/send-otp", "/applicant/verify-otp", "/applicants/reset-password/{email}", "/recruiters/reset-password/set-new-password/{email}")
//                                    .permitAll()
//                            // Add other antMatchers and roles as needed
//                        )
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
	
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    return http.csrf(csrf -> csrf.disable())
//	               .authorizeRequests(authorize -> 
//	                   authorizes(.requestMatcher
//	                           request -> request
//	                                   .antMatchers("/applicant/applyjob/{applicantId}/{jobId}", "/applicant/findRecommendedJobsBySkills/{applicantProfileId}", "/applicant/createProfile/{applicantId}", "/applicant/getapplicantProfileDetails/{profileid}", "/applicant/saveJob/{applicantId}/{jobId}", "/applicant/viewJob/{jobId}", "/applicant/applyJob/getAppliedJobs/{applicantId}", "/applicant/searchjobbyskillname/{applicantId}/jobs/{skillName}")
//	                                   .hasAnyRole("JOBAPPLICANT"),
//	                           request -> request
//	                                   .antMatchers("/recruiters", "/signOut", "/applicant/forgotpassword/sendotp", "/recruiterLogin", "/recruiters/send-otp", "/verify-otp", "/saveApplicant", "/registration-send-otp", "/applicant/login", "/applicant/send-otp", "/applicant/verify-otp", "/applicants/reset-password/{email}", "/recruiters/reset-password/set-new-password/{email}")
//	                                   .permitAll()
//	                           // Add other antMatchers and roles as needed
//	                   )
//	                   .anyRequest().authenticated()
//	               )
//	               .exceptionHandling()
//	               .and()
//	               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	               .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//	               .build();
//	}
	
	
//	public void configure(HttpSecurity httpSecurity) throws Exception {
//	    httpSecurity.csrf().disable()
//	            .authorizeRequests()
//	                .antMatchers("/applicant/applyjob/{applicantId}/{jobId}", "/applicant/findRecommendedJobsBySkills/{applicantProfileId}", "/applicant/createProfile/{applicantId}", "/applicant/getapplicantProfileDetails/{profileid}", "/applicant/saveJob/{applicantId}/{jobId}", "/applicant/viewJob/{jobId}", "/applicant/applyJob/getAppliedJobs/{applicantId}", "/applicant/searchjobbyskillname/{applicantId}/jobs/{skillName}")
//	                    .hasAnyRole("JOBAPPLICANT")
//	                .antMatchers("/applicant/send-otp").permitAll()
//	                .anyRequest().authenticated()
//	                .and()
//	            .exceptionHandling()
//	                .and()
//	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	 
//	    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}

	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/applicant/send-otp", "/applicant/verify-otp", "/applicant/register-jobApplicant", "/applicant/forgotPassword/send-Otp", "/applicant/reset-password","/applicantLogOut","/applicants/login").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/applicants/**")
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();

    }
	
}

	


