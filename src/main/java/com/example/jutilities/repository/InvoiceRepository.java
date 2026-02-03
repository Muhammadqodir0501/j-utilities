package com.example.jutilities.repository;

import com.example.jutilities.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    Optional<Invoice> findByAccountNumber(String accountNumber);

    List<Invoice> findAllByUser_Id(UUID userId);

    List<Invoice> findAllByProvider_Id(UUID providerId);
}
