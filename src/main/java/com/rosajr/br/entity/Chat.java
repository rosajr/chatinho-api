package com.rosajr.br.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_CHAT")
public class Chat {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "CHAT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chat")
    @JsonBackReference
    private List<Message> messages;

    @OneToMany(mappedBy = "chat")
    @JsonBackReference
    private List<Participants> participants;

}
