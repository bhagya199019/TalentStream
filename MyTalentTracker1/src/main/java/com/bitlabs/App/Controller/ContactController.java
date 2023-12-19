package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.Contact;
import com.bitlabs.App.Service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService; 
	
	@PostMapping("/saveContact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

}
