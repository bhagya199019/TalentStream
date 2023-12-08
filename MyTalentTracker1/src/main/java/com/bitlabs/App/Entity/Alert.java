package com.bitlabs.App.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Alert {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 /*   @ManyToOne
    @JoinColumn(name = "recruiter_id")
    @JsonIgnore
    private JobRecruiter recruiter;*/

    private String companyName;

    private String alertMessage;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime alertDate;
    
    
    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    @JsonIgnore
    private JobApplicant jobApplicant;

    
    
    
    
}
