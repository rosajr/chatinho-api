package com.rosajr.br.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_MESSAGE")
public class Message {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MESSAGE_USER_SENDER_ID", referencedColumnName = "USER_ID")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "MESSAGE_CHAT_ID", referencedColumnName = "CHAT_ID")
    private Chat chat;

    @NotNull
    @Column(name = "MESSAGE_TEXT")
    private String text;

    @NotNull
    @Column(name = "MESSAGE_DATE")
    private LocalDateTime dateTime;
}