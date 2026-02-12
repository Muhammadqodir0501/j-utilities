package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.InvoiceCreateRequest;
import com.example.jutilities.dto.request.InvoicePayRequest;
import com.example.jutilities.dto.response.InvoiceResponse;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(InvoiceCreateRequest request);

    InvoiceResponse payInvoice(InvoicePayRequest request);

    List<InvoiceResponse> getUserInvoices(String passportNumber);
}