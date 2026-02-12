package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.InvoiceCreateRequest;
import com.example.jutilities.dto.request.InvoicePayRequest;
import com.example.jutilities.dto.response.InvoiceResponse;
import com.example.jutilities.entity.Invoice;
import com.example.jutilities.entity.Provider;
import com.example.jutilities.entity.User;
import com.example.jutilities.exception.BadRequestException;
import com.example.jutilities.exception.NotFoundException;
import com.example.jutilities.repository.InvoiceRepository;
import com.example.jutilities.repository.ProviderRepository;
import com.example.jutilities.repository.UserRepository;
import com.example.jutilities.service.abstraction.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    @Override
    @Transactional
    public InvoiceResponse createInvoice(InvoiceCreateRequest request) {
        Optional<Invoice> existInvoice = invoiceRepository.findByAccountNumber(request.getAccountNumber());
        if(existInvoice.isPresent()) {
            throw new BadRequestException("Invoice with this account number already exists");
        }


        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Provider provider = providerRepository.findById(request.getProviderId())
                .orElseThrow(() -> new NotFoundException("Provider not found"));

        Invoice invoice = Invoice.builder()
                .accountNumber(request.getAccountNumber())
                .user(user)
                .provider(provider)
                .amount(request.getAmount())
                .isPayed(false)
                .build();

        invoiceRepository.save(invoice);
        return mapToResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceResponse payInvoice(InvoicePayRequest request) {
        Invoice invoice = invoiceRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Invoice not found"));

        if (invoice.isPayed()) {
            throw new BadRequestException("Invoice is already payed");
        }

        long newAmount = invoice.getAmount() - request.getAmount();

        if (newAmount < 0) {
            throw new BadRequestException("Payment amount exceeds debt. Current debt: " + invoice.getAmount());
        }

        invoice.setAmount(newAmount);
        if (newAmount == 0) {
            invoice.setPayed(true);
        }

        invoiceRepository.save(invoice);
        return mapToResponse(invoice);
    }

    @Override
    public List<InvoiceResponse> getUserInvoices(String passportNumber) {
        if (userRepository.findByPassportNumber(passportNumber).isEmpty()) {
            throw new NotFoundException("User with passport " + passportNumber + " not found");
        }

        return invoiceRepository.findAllByUserPassport(passportNumber).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private InvoiceResponse mapToResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .accountNumber(invoice.getAccountNumber())
                .amount(invoice.getAmount())
                .isPayed(invoice.isPayed())
                .userFullName(invoice.getUser().getFullName())
                .providerName(invoice.getProvider().getName())
                .build();
    }
}