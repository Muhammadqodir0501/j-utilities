package com.example.jutilities.repository;

import com.example.jutilities.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    Optional<Provider> findByName(String name);
}
