package ru.difembaxio.secutityjwt.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private String error;
    private String description;
}
