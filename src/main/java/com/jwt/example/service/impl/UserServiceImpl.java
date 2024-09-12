package com.jwt.example.service.impl;

import com.jwt.example.dto.UserDto;
import com.jwt.example.entity.User;
import com.jwt.example.exception.ResourceNotFound;
import com.jwt.example.repository.UserRepository;
import com.jwt.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User toBeCreated = this.modelMapper.map(userDto, User.class);
        toBeCreated.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        User createduser = this.userRepository.save(toBeCreated);
        return this.modelMapper.map(createduser, UserDto.class);
    }

    @Override
    public UserDto getUserById(int userId) {
        User foundUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", String.valueOf(userId)));
        return this.modelMapper.map(foundUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream().map((user) -> this.modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto updateUserById(UserDto userDto, int userId) {
        User foundUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", String.valueOf(userId)));
        foundUser.setPassword(userDto.getPassword());
        User updatedUser = this.userRepository.save(foundUser);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(int userId) {
        User foundUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", String.valueOf(userId)));
        this.userRepository.deleteById(userId);
    }

    @Override
    public String getToken(UserDto userDto) {

        return null;
    }
}
