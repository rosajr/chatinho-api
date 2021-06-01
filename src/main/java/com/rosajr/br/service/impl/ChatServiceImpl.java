package com.rosajr.br.service.impl;

import com.rosajr.br.dto.ChatInputDTO;
import com.rosajr.br.entity.Chat;
import com.rosajr.br.entity.Message;
import com.rosajr.br.entity.MessageData;
import com.rosajr.br.entity.Participants;
import com.rosajr.br.exceptions.ObjectNotFoundException;
import com.rosajr.br.repository.*;
import com.rosajr.br.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDataRepository messageDataRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(ChatInputDTO dto) {

        var chat = Chat.builder().build();
        chatRepository.save(chat);

        var sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new ObjectNotFoundException("Sender not found for id: " + dto.getSenderId()));

        var senderParticipant = Participants.builder()
                .chat(chat)
                .user(sender)
                .build();

        participantsRepository.save(senderParticipant);

        var message = Message.builder()
                .chat(chat)
                .sender(sender)
                .text(dto.getMessage())
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();

        messageRepository.save(message);

        dto.getReceivers().forEach(receiverId -> {

            var receiver = userRepository.findById(receiverId)
                    .orElseThrow(() -> new ObjectNotFoundException("Receiver not found for id: " + receiverId));

            var receiverParticipant = Participants.builder()
                    .chat(chat)
                    .user(receiver)
                    .build();

            participantsRepository.save(receiverParticipant);

            var messageData = MessageData.builder()
                    .message(message)
                    .receiver(receiver)
                    .read(false)
                    .received(false)
                    .build();

            messageDataRepository.save(messageData);

        });


    }
}
