package ru.sleed.mvc.service;

import ru.sleed.mvc.entity.User;
import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.PatchUserDto;
import ru.sleed.mvc.model.dto.UpdateUserDto;
import ru.sleed.mvc.model.dto.UserDto;
import org.springframework.stereotype.Service;
import ru.sleed.mvc.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return UserDto.UserListToDto(userRepository.findAll());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        User user = userById.get();
        UserDto userDto = UserDto.userToDto(user);
        return userDto;
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        UserDto userDto;


        if (createUserDto.getUsername().equals("test")) {
            userDto = UserDto.builder()
                    .id(1L)
                    .username(createUserDto.getUsername().concat("user"))
                    .dateOfBirth(createUserDto.getDateOfBirth())
                    .build();
        } else {
            userDto = UserDto.builder()
                    .id(1L)
                    .username(createUserDto.getUsername())
                    .dateOfBirth(createUserDto.getDateOfBirth())
                    .build();
        }

        return userDto;
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        UserDto userDto;
        if (userRepository.existsById(id)) {
            Optional<User> userById = userRepository.findUserById(id);
            User user = userById.get();
            user.setUsername(updateUserDto.getUsername());
            user.setDateOfBirth(updateUserDto.getDateOfBirth());
            User updatedUser = userRepository.save(user);
            userDto = UserDto.userToDto(updatedUser);
        } else {
            throw new RuntimeException("user not found");
        }
        return userDto;
    }

    @Override
    public UserDto patchUser(Long id, PatchUserDto patchUserDto) {
        UserDto userDto;
        if (userRepository.existsById(id)) {
            Optional<User> userById = userRepository.findUserById(id);
            User user = userById.get();
            if(patchUserDto.getUsername()==null && patchUserDto.getDateOfBirth()!=null){
                user.setDateOfBirth(patchUserDto.getDateOfBirth());
            } else if (patchUserDto.getDateOfBirth() == null && patchUserDto.getUsername()!=null ) {
                user.setUsername(patchUserDto.getUsername());
            }
            User updatedUser = userRepository.save(user);
            userDto = UserDto.userToDto(updatedUser);
        } else {
            throw new RuntimeException("user not found");
        }
        return userDto;
    }

    @Override
    public UserDto deleteUser(Long id) {

        Optional<User> deleteUser = userRepository.findUserById(id);
        User user = deleteUser.get();
        userRepository.delete(user);
        return null;
    }

    @Override
    public List<UserDto> findBySurname(String surname) {
        List<User> ListByUsername = userRepository.findByUsername(surname);
        List<UserDto> userDtoList = UserDto.UserListToDto(ListByUsername);

        return userDtoList;
    }
}
