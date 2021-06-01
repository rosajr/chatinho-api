package com.rosajr.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ChatInputDTO {
    @NotNull
    private Long senderId;
    @NotNull
    private List<Long> receivers;
    @NotBlank
    private String message;
}
