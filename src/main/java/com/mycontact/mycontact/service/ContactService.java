package com.mycontact.mycontact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.mycontact.mycontact.model.ContactModel;
import com.mycontact.mycontact.model.UserModel;
import com.mycontact.mycontact.repository.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public void deleteContact(Long contactId, UserModel user) {

        Optional<ContactModel> contactModel = contactRepository.findById(contactId);

        if (contactModel.isEmpty()) {
            throw new EntityNotFoundException("Contact introuvable avec l'id : " + contactId);
        }

        ContactModel contact = contactModel.get();

        if (!contact.getOwner().getId().equals(user.getId())) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer ce contact");
        }

        contactRepository.deleteById(contactId);

    }
}
