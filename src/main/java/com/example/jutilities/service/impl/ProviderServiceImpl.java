package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.ProviderCreateRequest;
import com.example.jutilities.dto.response.ProviderResponse;
import com.example.jutilities.entity.Provider;
import com.example.jutilities.exception.ConflictException;
import com.example.jutilities.exception.NotFoundException;
import com.example.jutilities.repository.ProviderRepository;
import com.example.jutilities.service.abstraction.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    public ProviderResponse createProvider(ProviderCreateRequest request) {
        if (providerRepository.findByName(request.getName()).isPresent()) {
            throw new ConflictException("Provider already exists");
        }

        Provider provider = Provider.builder()
                .name(request.getName())
                .active(true)
                .build();

        providerRepository.save(provider);
        return mapToResponse(provider);
    }

    @Override
    public ProviderResponse switchStatus(UUID providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException("Provider not found"));

        provider.setActive(!provider.isActive());

        providerRepository.save(provider);
        return mapToResponse(provider);
    }

    @Override
    public List<ProviderResponse> getAllProviders() {
        return providerRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProviderResponse mapToResponse(Provider provider) {
        return ProviderResponse.builder()
                .id(provider.getId())
                .name(provider.getName())
                .active(provider.isActive())
                .build();
    }
}