package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.ProviderDto;
import com.example.jutilities.entity.Provider;
import com.example.jutilities.exception.ConflictException;
import com.example.jutilities.exception.NotFoundException;
import com.example.jutilities.repository.ProviderRepository;
import com.example.jutilities.service.abstraction.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    public Provider createProvider(ProviderDto providerDto) {

        Optional<Provider> existProvider = providerRepository.findByName(providerDto.getName());
        if(existProvider.isPresent()) {
            throw new ConflictException("Provider already exists");
        }

        Provider provider = Provider.builder()
                .name(providerDto.getName())
                .active(true)
                .build();

        return providerRepository.save(provider);
    }

    @Override
    public Provider updateActivation(UUID providerId){
        Optional<Provider> existProvider = providerRepository.findById(providerId);
        if(existProvider.isPresent()) {
            if(existProvider.get().isActive()) {
                existProvider.get().setActive(false);
                providerRepository.save(existProvider.get());
            }else {
                existProvider.get().setActive(true);
                providerRepository.save(existProvider.get());
            }
        }
        throw new NotFoundException("Provider not found");
    }

    @Override
    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }
}
