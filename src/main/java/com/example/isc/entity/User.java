package com.example.isc.entity;

import com.example.isc.entity.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity<Integer> implements UserDetails {

    private String firstname;

    private String lastname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEnabled = false;

    private boolean isBlocked;

    private boolean isExpired;

    private String username;

    private String email;

    private String nationalId;

    private LocalDate creationDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name().toUpperCase()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
