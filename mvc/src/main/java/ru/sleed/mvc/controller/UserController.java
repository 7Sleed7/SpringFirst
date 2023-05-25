package ru.sleed.mvc.controller;

import jakarta.validation.Valid;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.sleed.mvc.model.dto.CreateUserDto;
import ru.sleed.mvc.model.dto.PatchUserDto;
import ru.sleed.mvc.model.dto.UpdateUserDto;
import ru.sleed.mvc.model.dto.UserDto;
import ru.sleed.mvc.service.IUserService;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return service.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        UserDto userDto = service.findById(id);
        return userDto;
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        log.info("Get request for create user: {}", createUserDto);

        UserDto userDto = service.createUser(createUserDto);

        log.info("Return response for create user: {}", userDto);

        return userDto;
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @Valid @RequestBody UpdateUserDto updateUserDto) {
        log.info("Get request for create user: {}", updateUserDto);

        UserDto userDto = service.updateUser(id, updateUserDto);

        log.info("Return response for create user: {}", userDto);

        return userDto;
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody PatchUserDto patchUserDto) {
        Optional<UserDto> optionalUser = Optional.of(service.findById(id));
        if (optionalUser.isPresent()) {
            UserDto userDto = optionalUser.get();
            if (patchUserDto.getUsername() != null) {
                userDto.setUsername(patchUserDto.getUsername());
            }
            if (patchUserDto.getDateOfBirth() != null) {
                userDto.setDateOfBirth(patchUserDto.getDateOfBirth());
            }
            return userDto;
        } else {
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id) {
        return null;
    }
}
