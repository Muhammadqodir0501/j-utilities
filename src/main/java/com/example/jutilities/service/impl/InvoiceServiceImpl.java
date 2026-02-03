package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.InvoiceDto;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    @Override
    public Invoice createInvoice(InvoiceDto invoiceDto) {
        Optional<User> user = userRepository.findById(invoiceDto.getUserId());
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        Optional<Provider> provider = providerRepository.findByName(invoiceDto.getProviderName());
        if(provider.isEmpty()) {
            throw new NotFoundException("Provider not found");
        }

        Invoice invoice = Invoice.builder()
                .accountNumber(invoiceDto.getAccountNumber())
                .user(user.get())
                .amount(invoiceDto.getAmount())
                .isPayed(false)
                .build();
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice payedInvoice(InvoiceDto invoiceDto) {
        Optional<User> user = userRepository.findById(invoiceDto.getUserId());
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        Optional<Invoice> invoice = invoiceRepository.findByAccountNumber(invoiceDto.getAccountNumber());
        if(invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        long remainder = invoice.get().getAmount() - invoiceDto.getAmount();

        if(remainder < 0){
            throw new BadRequestException("Payed more than requested amount");
        } else if (remainder > 0) {
            invoice.get().setAmount(remainder);
            invoice.get().setPayed(false);
            invoiceRepository.save(invoice.get());
        }else{
            invoice.get().setPayed(true);
            invoiceRepository.save(invoice.get());
        }
        return invoice.get();
    }

    @Override
    public Invoice getInvoice(String accountNumber) {
        return invoiceRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));
    }

    @Override
    public List<Invoice> getUserInvoices(String passportNumber) {
        Optional<User> user = userRepository.findByPassportNumber(passportNumber);
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getUser().getPassportNumber()
                        .equals(passportNumber))
                .toList();
    }
}
