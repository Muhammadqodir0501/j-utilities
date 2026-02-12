package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.ProviderCreateRequest;
import com.example.jutilities.dto.response.ProviderResponse;

import java.util.List;
import java.util.UUID;

public interface ProviderService {

    ProviderResponse createProvider(ProviderCreateRequest request);

    ProviderResponse switchStatus(UUID providerId);

    List<ProviderResponse> getAllProviders();
}