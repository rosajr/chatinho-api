package com.rosajr.br.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_PARTICIPANTS")
public class Participants {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "PARTICIPANTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PARTICIPANTS_CHAT_ID", referencedColumnName = "CHAT_ID")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "PARTICIPANTS_USER_ID", referencedColumnName = "USER_ID")
    private User user;
}