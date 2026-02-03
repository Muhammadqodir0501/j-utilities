package com.example.jutilities.controller;

import com.example.jutilities.dto.request.UserDto;
import com.example.jutilities.entity.User;
import com.example.jutilities.exception.ApiResponse;
import com.example.jutilities.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(user));
    }

    @GetMapping("/{passportNumber}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable String passportNumber) {
        User user = userService.getUserByPassportNumber(passportNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(user));
    }
}
