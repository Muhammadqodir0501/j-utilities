package com.example.jutilities.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active;


}
