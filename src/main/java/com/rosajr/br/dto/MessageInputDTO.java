package com.rosajr.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
public class MessageInputDTO {
    @NotNull
    private Long senderId;
    @NotNull
    private Long chatId;
    @NotBlank
    private String message;
}
