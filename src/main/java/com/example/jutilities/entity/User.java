package com.example.jutilities.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String passportNumber;

}
