package com.rosajr.br.controller;

import com.rosajr.br.dto.ChatInputDTO;
import com.rosajr.br.dto.MessageInputDTO;
import com.rosajr.br.service.ChatService;
import com.rosajr.br.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody ChatInputDTO dto) {
        chatService.create(dto);
    }

    @PostMapping("/newMessage")
    @ResponseStatus(HttpStatus.OK)
    public void newMessage(@RequestBody MessageInputDTO dto) {
        messageService.create(dto);
    }
}
