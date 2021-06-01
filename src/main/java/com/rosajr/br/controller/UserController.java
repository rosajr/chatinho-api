package com.rosajr.br.controller;

import com.rosajr.br.dto.UserInputDTO;
import com.rosajr.br.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserInputDTO dto) {
        userService.create(dto);
    }
}
