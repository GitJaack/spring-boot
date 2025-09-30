package com.mycontact.mycontact.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(example = "test1@gmail.com")
    private String email;

    @Column(nullable = false)
    @Schema(example = "1234")
    private String password;

    @Schema(hidden = true)
    private LocalDateTime createdAt = LocalDateTime.now();

    public UserModel() {
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
