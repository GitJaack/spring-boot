package com.mycontact.mycontact.repository;

import org.springframework.stereotype.Repository;

import com.mycontact.mycontact.model.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
}