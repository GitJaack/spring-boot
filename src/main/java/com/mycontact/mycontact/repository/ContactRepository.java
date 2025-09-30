package com.mycontact.mycontact.repository;

import java.util.List;
import com.mycontact.mycontact.model.ContactModel;
import com.mycontact.mycontact.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {
    List<ContactModel> findByOwner(UserModel owner);
}