package com.example.isc.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@ToString
public class Admin extends User {
    private String adminCode;
}
