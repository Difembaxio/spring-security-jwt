package ru.difembaxio.secutityjwt.dto;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {

  private String accessToken;
  private String refreshToken;
}
