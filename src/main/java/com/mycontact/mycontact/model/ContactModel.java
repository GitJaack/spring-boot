package com.mycontact.mycontact.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
public class ContactModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String nom;
    private String prenom;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(hidden = true)
    private UserModel owner;

    public ContactModel() {
    }

    public ContactModel(String nom, String prenom, String phone, UserModel owner) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.owner = owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}