package com.example.jutilities.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private boolean isPayed;
}
