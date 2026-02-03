package com.example.jutilities.service.abstraction;

import com.example.jutilities.dto.request.UserDto;
import com.example.jutilities.entity.User;

public interface UserService {
    User registerUser(UserDto userDto);

    User getUserByPassportNumber(String passportNumber);
}
