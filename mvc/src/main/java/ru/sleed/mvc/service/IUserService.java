package ru.sleed.mvc.service;

import ru.sleed.mvc.entity.User;
import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.UpdateUserDto;
import ru.sleed.mvc.model.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAllUsers();
    UserDto createUser(CreateUserDto createUserDto);

    UserDto findById(Long id);

    UserDto updateUser(Long id, UpdateUserDto updateUserDto);
}
