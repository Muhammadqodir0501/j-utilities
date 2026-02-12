package com.example.jutilities.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 30, message = "Full name must be between 2 and 30 characters")
    private String fullName;

    @NotBlank(message = "Passport number is required")
    @Size(min = 7, max = 9)
    private String passportNumber;

}