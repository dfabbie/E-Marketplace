package com.marketplace.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Users {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    @NonNull
    private String password;
    private Instant date;
    @NonNull
    private String username;
    private boolean isActive;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<Authority> authorities;



}
