package com.example.jutilities.controller;

import com.example.jutilities.dto.request.ProviderDto;
import com.example.jutilities.entity.Provider;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Provider>> createProvider(@RequestBody ProviderDto providerDto) {
        Provider provider = providerService.createProvider(providerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(provider));
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ApiResponse<Provider>> updateProvider(@PathVariable UUID providerId) {
        Provider provider = providerService.updateActivation(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(provider));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Provider>>> getAllProviders() {
        List<Provider> providers = providerService.getAllProviders();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(providers));
    }

}
