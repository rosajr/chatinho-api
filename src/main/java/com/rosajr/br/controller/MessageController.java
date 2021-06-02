package com.rosajr.br.controller;

import com.rosajr.br.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PutMapping("/{id}/user/{userId}/read")
    @ResponseStatus(HttpStatus.OK)
    public void readMessage(@PathVariable Long id, @PathVariable Long userId) {
        messageService.readMessage(id, userId);
    }

    @PutMapping("/{id}/user/{userId}/receive")
    @ResponseStatus(HttpStatus.OK)
    public void receiveMessage(@PathVariable Long id, @PathVariable Long userId) {
        messageService.receiveMessage(id, userId);
    }
}
