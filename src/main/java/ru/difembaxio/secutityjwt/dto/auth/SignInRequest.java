package ru.difembaxio.secutityjwt.dto.auth;

import lombok.Data;


@Data
public class SignInRequest {

    private String login;
    private String password;

}
