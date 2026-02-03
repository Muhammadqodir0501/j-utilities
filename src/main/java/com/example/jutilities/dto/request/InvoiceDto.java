package com.example.jutilities.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private String accountNumber;
    private String providerName;
    private UUID userId;
    private long amount;

}
