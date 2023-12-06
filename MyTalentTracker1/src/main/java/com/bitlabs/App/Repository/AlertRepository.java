package com.bitlabs.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitlabs.App.Entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    // You can add custom query methods here if needed
	
}