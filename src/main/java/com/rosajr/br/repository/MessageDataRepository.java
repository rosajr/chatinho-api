package com.rosajr.br.repository;

import com.rosajr.br.entity.MessageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDataRepository extends JpaRepository<MessageData, Long> {
    MessageData findByReceiverIdAndMessageId(Long receiverId, Long messageId);
}
