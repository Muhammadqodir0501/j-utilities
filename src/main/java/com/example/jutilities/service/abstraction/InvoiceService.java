package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.InvoiceDto;
import com.example.jutilities.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(InvoiceDto invoiceDto);

    Invoice payedInvoice(InvoiceDto invoiceDto);

    Invoice getInvoice(String accountNumber);

    List<Invoice> getUserInvoices(String passportNumber);
}
