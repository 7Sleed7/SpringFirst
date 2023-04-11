package ru.sleed.secondtask.service.impl;

import org.springframework.stereotype.Service;
import ru.sleed.secondtask.service.TestService;

@Service
public class ServiceImpl implements TestService {
    @Override
    public String nameCreation(String name) {
        return "name is: " + name;
    }
}