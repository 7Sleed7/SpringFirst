package ru.sleed.thridtask.service.impl;

import org.springframework.stereotype.Service;
import ru.sleed.thridtask.exeption.ApplicationException;
import ru.sleed.thridtask.exeption.ExceptionMessage;
import ru.sleed.thridtask.model.User;
import ru.sleed.thridtask.repository.UserRepository;
import ru.sleed.thridtask.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User get(String name) {
        return repository.getUsers().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst().orElseThrow(() -> new ApplicationException(ExceptionMessage.NAME_NOT_FOUND));
    }

    @Override
    public List<User> get() {
        return repository.getUsers();
    }

    @Override
    public User get(Long id) {
        return repository.getUsers().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElseThrow(() -> new ApplicationException(ExceptionMessage.ID_NOT_FOUND));
    }

    @Override
    public void create(User entity) {
        repository.getUsers().add(entity);
    }

    @Override
    public void updateAll(Long id, User entity) {
        User entityUpdate = get(id);
        delete(id);
        if (entityUpdate.getName() != null && !entityUpdate.getName().isEmpty()) {
            entityUpdate.setName(entity.getName());
        }
        if (entityUpdate.getAge() != null && !entityUpdate.getAge().toString().isEmpty()) {
            entityUpdate.setAge(entity.getAge());
        }
        repository.getUsers().add(entityUpdate);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new ApplicationException(ExceptionMessage.ID_NOT_FOUND);
        } else {
            repository.getUsers().removeIf(s -> s.getId().equals(id));
        }

    }
}
