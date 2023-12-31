package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.ApplyJobStatusHistory;

@Repository
public interface ApplyJobStatusHistoryRepository extends JpaRepository<ApplyJobStatusHistory, Long> {

}
