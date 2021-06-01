package com.rosajr.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Data
public class UserInputDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String nickName;
    @NotBlank
    private String password;
}
