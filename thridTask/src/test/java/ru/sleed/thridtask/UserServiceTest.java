package ru.sleed.thridtask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sleed.thridtask.model.User;
import ru.sleed.thridtask.repository.UserRepository;
import ru.sleed.thridtask.service.impl.UserServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl service;
    private User user;

    @Test
    public void getAllUsers(){
        List<User> users = service.get();
        System.out.println(users);
    }
}
