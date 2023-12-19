package com.bitlabs.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitlabs.App.Entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer>{

}
