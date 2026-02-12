package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.UserRegisterRequest;
import com.example.jutilities.dto.response.UserResponse;
import com.example.jutilities.entity.User;
import com.example.jutilities.exception.ConflictException;
import com.example.jutilities.exception.NotFoundException;
import com.example.jutilities.repository.UserRepository;
import com.example.jutilities.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRegisterRequest request) {
        if (userRepository.findByPassportNumber(request.getPassportNumber()).isPresent()) {
            throw new ConflictException("User already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .passportNumber(request.getPassportNumber())
                .build();

        userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public UserResponse getUserByPassportNumber(String passportNumber) {
        User user = userRepository.findByPassportNumber(passportNumber)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .passportNumber(user.getPassportNumber())
                .build();
    }
}