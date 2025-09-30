package com.mycontact.mycontact.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycontact.mycontact.model.ContactModel;
import com.mycontact.mycontact.model.UserModel;
import com.mycontact.mycontact.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactModel createContact(ContactModel contact) {
        return contactRepository.save(contact);
    }

    public List<ContactModel> getContactsByUser(UserModel user) {
        return contactRepository.findByOwner(user);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
