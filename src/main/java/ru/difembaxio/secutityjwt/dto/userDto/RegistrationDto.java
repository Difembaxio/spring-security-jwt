package ru.difembaxio.secutityjwt.dto.userDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDto {

    private String login;
}
