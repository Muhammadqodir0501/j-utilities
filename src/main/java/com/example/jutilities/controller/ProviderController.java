package com.example.jutilities.controller;

import com.example.jutilities.dto.request.ProviderCreateRequest;
import com.example.jutilities.dto.response.ProviderResponse;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProviderResponse>> createProvider(@RequestBody @Valid ProviderCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(providerService.createProvider(request)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ProviderResponse>> switchStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(new ApiResponse<>(providerService.switchStatus(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProviderResponse>>> getAllProviders() {
        return ResponseEntity.ok(new ApiResponse<>(providerService.getAllProviders()));
    }
}