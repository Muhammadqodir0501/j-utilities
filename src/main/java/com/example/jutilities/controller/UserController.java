package com.example.jutilities.controller;

import com.example.jutilities.dto.request.UserRegisterRequest;
import com.example.jutilities.dto.response.UserResponse;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(userService.registerUser(request)));
    }

    @GetMapping("/{passportNumber}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable String passportNumber) {
        return ResponseEntity.ok(new ApiResponse<>(userService.getUserByPassportNumber(passportNumber)));
    }
}