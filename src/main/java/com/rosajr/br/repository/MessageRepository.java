package com.rosajr.br.repository;

import com.rosajr.br.entity.Chat;
import com.rosajr.br.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
