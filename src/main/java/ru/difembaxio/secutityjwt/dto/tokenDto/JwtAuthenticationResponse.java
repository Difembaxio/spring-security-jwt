package ru.difembaxio.secutityjwt.dto.tokenDto;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String accessToken;
    private String refreshToken;
}
