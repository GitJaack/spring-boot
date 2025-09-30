package com.mycontact.mycontact.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import com.mycontact.mycontact.model.ContactModel;
import com.mycontact.mycontact.model.UserModel;
import com.mycontact.mycontact.service.ContactService;
import com.mycontact.mycontact.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "Contacts")
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Ajoute un contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact ajouté avec succès", content = @Content()),
    })
    public ResponseEntity<ContactModel> addContact(ContactModel contact, Principal principal) {
        // Récupérer l’utilisateur connecté
        String email = principal.getName();
        UserModel user = userService.findByEmail(email);
        contact.setOwner(user);
        return ResponseEntity.ok(contactService.createContact(contact));
    }

    @Operation(summary = "Récupère tous mes contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Récupération des contacts avec succès", content = @Content()),
    })
    @GetMapping
    public ResponseEntity<List<ContactModel>> getMyContacts(Principal principal) {
        String email = principal.getName();
        UserModel user = userService.findByEmail(email);
        return ResponseEntity.ok(contactService.getContactsByUser(user));
    }

    @Operation(summary = "Supprime un contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact supprimé avec succès", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas l'autorisation de supprimer ce contact", content = @Content()),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id") Long id, Principal principal) {
        try {
            UserModel user = userService.findByEmail(principal.getName());
            contactService.deleteContact(id, user);
            return ResponseEntity.ok("Contact supprimé avec succès");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}