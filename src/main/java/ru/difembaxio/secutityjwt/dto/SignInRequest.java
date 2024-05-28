package ru.difembaxio.secutityjwt.dto;

import lombok.Data;


@Data
public class SignInRequest {

  private String login;
  private String password;

}
