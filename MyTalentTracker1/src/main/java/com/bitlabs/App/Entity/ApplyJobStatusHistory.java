package com.bitlabs.App.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class ApplyJobStatusHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "apply_job_id", nullable = false)
    @JsonBackReference
    private ApplyJob applyJob;
    
    @Column(name = "applicant_status", nullable = false)
    private String applicantStatus;
    
    @Column(name = "change_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime changeDate;

    // Constructors, getters, setters, etc.
}
