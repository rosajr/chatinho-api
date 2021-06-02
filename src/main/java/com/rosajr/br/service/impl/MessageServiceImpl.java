package com.rosajr.br.service.impl;

import com.rosajr.br.dto.MessageInputDTO;
import com.rosajr.br.entity.Message;
import com.rosajr.br.entity.MessageData;
import com.rosajr.br.exceptions.ObjectNotFoundException;
import com.rosajr.br.repository.ChatRepository;
import com.rosajr.br.repository.MessageDataRepository;
import com.rosajr.br.repository.MessageRepository;
import com.rosajr.br.service.MessageService;
import com.rosajr.br.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDataRepository messageDataRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void create(MessageInputDTO dto) {

        var chat = chatRepository.findById(dto.getChatId())
                .orElseThrow(() -> new ObjectNotFoundException("Chat not found for id: " + dto.getChatId()));

        var sender = userService.getUserById(dto.getSenderId());

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

    @Override
    @Transactional
    public void readMessage(Long messageId, Long receiverId) {
        var messageData = messageDataRepository.findByReceiverIdAndMessageId(receiverId, messageId);
        messageData.setRead(true);
    }

    @Override
    @Transactional
    public void receiveMessage(Long messageId, Long receiverId) {
        var messageData = messageDataRepository.findByReceiverIdAndMessageId(receiverId, messageId);
        messageData.setReceived(true);
    }

    private Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ObjectNotFoundException("Message not found for id: " + messageId));
    }

}