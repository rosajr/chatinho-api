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
@Table(name = "TB_MESSAGE_DATA")
public class MessageData {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MESSAGE_DATA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MESSAGE_DATA_MESSAGE_ID", referencedColumnName = "MESSAGE_ID")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "MESSAGE_DATA_USER_RECEIVER_ID", referencedColumnName = "USER_ID")
    private User receiver;

    @NotNull
    @Column(name = "MESSAGE_DATA_RECEIVED")
    private Boolean received;

    @NotNull
    @Column(name = "MESSAGE_DATA_READ")
    private Boolean read;
}