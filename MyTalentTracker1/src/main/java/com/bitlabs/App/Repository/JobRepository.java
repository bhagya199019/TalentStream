package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.App.Entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>  {

}
