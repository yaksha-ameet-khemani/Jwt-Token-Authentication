package com.jwt.example.service;

import com.jwt.example.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto getUserById(int userId);

    public List<UserDto> getAllUsers();

    public UserDto updateUserById(UserDto userDto, int userId);

    public void deleteUser(int userId);

    String getToken(UserDto userDto);
}
