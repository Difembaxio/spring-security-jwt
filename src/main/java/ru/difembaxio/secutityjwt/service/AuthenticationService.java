package ru.difembaxio.secutityjwt.service;


import ru.difembaxio.secutityjwt.dto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.dto.RegistrationRequest;
import ru.difembaxio.secutityjwt.dto.SignInRequest;
import ru.difembaxio.secutityjwt.model.User;


public interface AuthenticationService {

  User registrationUser(RegistrationRequest singUpRequest);

  User registrationAdmin(RegistrationRequest singUpRequest);

  JwtAuthenticationResponse signIn(SignInRequest SignInRequest);

  JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
