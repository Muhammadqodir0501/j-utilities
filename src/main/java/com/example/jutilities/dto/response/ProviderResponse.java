package com.example.jutilities.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class ProviderResponse {
    private UUID id;
    private String name;
    private boolean active;
}