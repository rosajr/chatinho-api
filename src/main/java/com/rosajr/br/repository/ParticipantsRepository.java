package com.rosajr.br.repository;

import com.rosajr.br.entity.Chat;
import com.rosajr.br.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
}
