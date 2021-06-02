package com.rosajr.br.service;

import com.rosajr.br.dto.MessageInputDTO;

public interface MessageService {
    void create(MessageInputDTO dto);

    void readMessage(Long messageId, Long receiverId);

    void receiveMessage(Long messageId, Long receiverId);
}
