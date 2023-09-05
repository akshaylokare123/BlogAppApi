package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.payloads.request.UserDto;

public interface UserService {

	public UserDto registerNewUser(UserDto userDto);

	public UserDto createUser(UserDto userDto);

	public UserDto updateUser(UserDto userDto, Integer userId);

	public UserDto getUserById(Integer userId);

	public List<UserDto> getAllUsers();

	public void deleteUser(Integer userId);

}
