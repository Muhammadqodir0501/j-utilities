package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.ProviderDto;
import com.example.jutilities.entity.Provider;

import java.util.List;
import java.util.UUID;

public interface ProviderService {
    Provider createProvider(ProviderDto providerDto);

    Provider updateActivation(UUID providerId);

    List<Provider> getAllProviders();
}
