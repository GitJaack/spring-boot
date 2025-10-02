package com.mycontact.mycontact.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycontact.mycontact.model.ContactModel;
import com.mycontact.mycontact.model.UserModel;
import com.mycontact.mycontact.model.DTO.ContactDTO;
import com.mycontact.mycontact.repository.ContactRepository;

import jakarta.validation.Valid;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactModel createContact(ContactDTO request, @Valid UserModel user) {
        ContactModel contact = new ContactModel();
        contact.setNom(request.getNom());
        contact.setPrenom(request.getPrenom());
        contact.setPhone(request.getPhone());
        contact.setOwner(user);
        return contactRepository.save(contact);
    }

    public List<ContactModel> getContactsByUser(UserModel user) {
        return contactRepository.findByOwner(user);
    }

    public void deleteContact(Long contactId, UserModel user) {
        ContactModel contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact introuvable avec l'id : " + contactId));

        if (!contact.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("Vous n'êtes pas autorisé à supprimer ce contact");
        }

        contactRepository.delete(contact);
    }
}
