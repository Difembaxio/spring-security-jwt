package ru.difembaxio.secutityjwt.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {

  private String refreshToken;
}
