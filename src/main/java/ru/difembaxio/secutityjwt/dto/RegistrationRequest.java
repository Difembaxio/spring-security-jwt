package ru.difembaxio.secutityjwt.dto;

import lombok.Data;

@Data

public class RegistrationRequest {

  private String login;
  private String password;
}
