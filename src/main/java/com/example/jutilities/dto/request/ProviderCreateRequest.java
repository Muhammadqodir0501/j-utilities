package com.example.jutilities.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProviderCreateRequest {
    @NotBlank(message = "Provider name is required")
    private String name;
}