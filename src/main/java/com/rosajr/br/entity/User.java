package com.rosajr.br.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_USER")
public class User {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USER_NAME")
    private String name;

    @NotNull
    @Column(name = "USER_NICK_NAME")
    private String nickName;

    @NotNull
    @Column(name = "USER_PASSWORD")
    private String password;
}