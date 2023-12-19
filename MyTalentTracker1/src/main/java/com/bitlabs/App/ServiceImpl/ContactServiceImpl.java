package com.bitlabs.App.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Entity.Contact;
import com.bitlabs.App.Repository.ContactRepository;
import com.bitlabs.App.Service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public Contact saveContact(Contact contact) {
		
		return contactRepository.save(contact);
	}

}
