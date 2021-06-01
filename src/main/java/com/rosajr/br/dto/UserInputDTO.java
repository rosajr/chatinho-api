package com.rosajr.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
public class UserInputDTO {

    @NotNull
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    private String password;
}
