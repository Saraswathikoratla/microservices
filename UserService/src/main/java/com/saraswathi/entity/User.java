package com.saraswathi.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role; // USER / ADMIN

    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    // getters & setters
}