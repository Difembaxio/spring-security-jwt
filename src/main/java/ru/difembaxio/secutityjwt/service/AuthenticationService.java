package ru.difembaxio.secutityjwt.service;


import ru.difembaxio.secutityjwt.dto.tokenDto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.tokenDto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.dto.auth.SignInRequest;
import ru.difembaxio.secutityjwt.dto.userDto.RegistrationDto;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;


public interface AuthenticationService {

    RegistrationDto registerUser(UserDto userDto);

    RegistrationDto registerAdmin(UserDto userDto);

    JwtAuthenticationResponse signIn(SignInRequest SignInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
