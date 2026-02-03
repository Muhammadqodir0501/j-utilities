package com.example.jutilities.service.impl;

import com.example.jutilities.dto.request.UserDto;
import com.example.jutilities.entity.User;
import com.example.jutilities.exception.ConflictException;
import com.example.jutilities.exception.NotFoundException;
import com.example.jutilities.repository.UserRepository;
import com.example.jutilities.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) {

        Optional<User> existUser = userRepository.findByPassportNumber(userDto.getPassportNumber());
        if(existUser.isPresent()) {
            throw new ConflictException("User already exists");
        }

        User user = User.builder()
                .fullName(userDto.getFullName())
                .passportNumber(userDto.getPassportNumber())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getUserByPassportNumber(String passportNumber) {
        Optional<User> user = userRepository.findByPassportNumber(passportNumber);
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }
}
