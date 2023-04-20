package ru.sleed.thridtask.service;

import ru.sleed.thridtask.model.User;

import java.util.List;

public interface UserService {

    User get(String username);

    List<User> get();

    User get(Long id) throws Exception;

    void create(User entity);

    void updateAll(Long id, User entity) throws Exception;

    void delete(Long id);
}
