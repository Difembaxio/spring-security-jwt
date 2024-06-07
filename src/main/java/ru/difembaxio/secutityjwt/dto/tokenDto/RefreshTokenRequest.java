package ru.difembaxio.secutityjwt.dto.tokenDto;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;
}
