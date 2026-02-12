package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.UserRegisterRequest;
import com.example.jutilities.dto.response.UserResponse;
import com.example.jutilities.entity.User;

public interface UserService {
    UserResponse registerUser(UserRegisterRequest userDto);

    UserResponse getUserByPassportNumber(String passportNumber);
}
