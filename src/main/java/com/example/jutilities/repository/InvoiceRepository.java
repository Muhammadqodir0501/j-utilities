package com.example.jutilities.repository;

import com.example.jutilities.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    Optional<Invoice> findByAccountNumber(String accountNumber);

    @Query("SELECT i FROM Invoice i JOIN FETCH i.user JOIN FETCH i.provider WHERE i.user.passportNumber = :passport")
    List<Invoice> findAllByUserPassport(@Param("passport") String passportNumber);
}