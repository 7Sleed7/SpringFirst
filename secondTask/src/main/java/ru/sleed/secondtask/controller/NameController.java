package ru.sleed.secondtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sleed.secondtask.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("name/")
public class NameController {
    private final ServiceImpl serviceImpl;

    @Autowired
    public NameController(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @GetMapping("getName")
    public String getString(@RequestParam("name") String name) {
        return serviceImpl.nameCreation(name);

    }
}