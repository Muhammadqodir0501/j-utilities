package com.example.jutilities.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class InvoiceResponse {
    private UUID id;
    private String accountNumber;
    private String providerName;
    private String userFullName;
    private long amount;
    private boolean isPayed;
}