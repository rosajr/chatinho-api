package com.rosajr.br.service.impl;

import com.rosajr.br.dto.MessageInputDTO;
import com.rosajr.br.entity.Message;
import com.rosajr.br.entity.MessageData;
import com.rosajr.br.exceptions.ObjectNotFoundException;
import com.rosajr.br.repository.ChatRepository;
import com.rosajr.br.repository.MessageDataRepository;
import com.rosajr.br.repository.MessageRepository;
import com.rosajr.br.repository.UserRepository;
import com.rosajr.br.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDataRepository messageDataRepository;

    @Override
    public void create(MessageInputDTO dto) {

        var chat = chatRepository.findById(dto.getChatId())
                .orElseThrow(() -> new ObjectNotFoundException("Chat not found for id: " + dto.getChatId()));

        var sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new ObjectNotFoundException("Sender not found for id: " + dto.getSenderId()));

        var message = Message.builder()
                .chat(chat)
                .sender(sender)
                .text(dto.getMessage())
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();

        messageRepository.save(message);

        chat.getParticipants()
                .stream()
                .filter(participants -> !participants.getUser().getId().equals(sender.getId()))
                .forEach(participant -> {

                    var messageData = MessageData.builder()
                            .message(message)
                            .receiver(participant.getUser())
                            .read(false)
                            .received(false)
                            .build();

                    messageDataRepository.save(messageData);
                });

    }
}
