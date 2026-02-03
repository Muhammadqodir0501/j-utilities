package com.example.jutilities.controller;

import com.example.jutilities.dto.request.InvoiceDto;
import com.example.jutilities.entity.Invoice;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<ApiResponse<Invoice>> createInvoice(@RequestBody InvoiceDto invoiceDto){
        Invoice invoice = invoiceService.createInvoice(invoiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(invoice));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Invoice>> payedInvoice(@RequestBody InvoiceDto invoiceDto){
        Invoice invoice = invoiceService.payedInvoice(invoiceDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(invoice));
    }

    @GetMapping("/{passportNumber}")
    public ResponseEntity<ApiResponse<List<Invoice>>> getUserInvoices(@PathVariable String passportNumber){
        List<Invoice> invoices = invoiceService.getUserInvoices(passportNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(invoices));
    }

    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<ApiResponse<Invoice>> getInvoice(@PathVariable String accountNumber){
        Invoice invoice = invoiceService.getInvoice(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(invoice));
    }
}
