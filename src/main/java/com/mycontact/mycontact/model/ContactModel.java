package com.mycontact.mycontact.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
public class ContactModel {
    private String nom;
    private String prenom;
    private String phone;
    private String userId;

}
