package com.example.jutilities.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InvoicePayRequest {
    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @Min(value = 1, message = "Amount must be positive")
    private long amount;
}