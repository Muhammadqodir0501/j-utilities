package com.example.jutilities.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class InvoiceCreateRequest {
    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Provider ID is required")
    private UUID providerId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @Min(value = 100, message = "Amount must be at least 100")
    private long amount;
}