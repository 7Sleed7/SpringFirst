package ru.sleed.mvc.service;

import ru.sleed.mvc.entity.User;
import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.UpdateUserDto;
import ru.sleed.mvc.model.dto.UserDto;
import org.springframework.stereotype.Service;
import ru.sleed.mvc.repository.UserRepository;

import java.util.List;

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
        return UserDto.userToDto(userRepository.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("ID NOT FOUND")));
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
            userDto = UserDto.builder().build();
            if (!userDto.getUsername().startsWith("test")) {
                userRepository.save(UserDto.dtoToUser(userDto));
            } else {
                throw new RuntimeException("User is test");
            }
        } else {
            throw new RuntimeException("user not found");
        }
        return userDto;
    }
}
