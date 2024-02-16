package com.example.securityjwt.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User{

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "text", nullable = false)
    private String password;

    @Setter
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}