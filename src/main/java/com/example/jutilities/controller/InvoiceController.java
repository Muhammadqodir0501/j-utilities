package com.example.jutilities.controller;

import com.example.jutilities.dto.request.InvoiceCreateRequest;
import com.example.jutilities.dto.request.InvoicePayRequest;
import com.example.jutilities.dto.response.InvoiceResponse;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<ApiResponse<InvoiceResponse>> createInvoice(@RequestBody @Valid InvoiceCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(invoiceService.createInvoice(request)));
    }

    @PostMapping("/pay")
    public ResponseEntity<ApiResponse<InvoiceResponse>> payInvoice(@RequestBody @Valid InvoicePayRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(invoiceService.payInvoice(request)));
    }

    @GetMapping("/user/{passportNumber}")
    public ResponseEntity<ApiResponse<List<InvoiceResponse>>> getUserInvoices(@PathVariable String passportNumber) {
        return ResponseEntity.ok(new ApiResponse<>(invoiceService.getUserInvoices(passportNumber)));
    }
}