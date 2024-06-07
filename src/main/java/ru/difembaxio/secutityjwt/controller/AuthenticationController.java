package ru.difembaxio.secutityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.difembaxio.secutityjwt.dto.auth.SignInRequest;
import ru.difembaxio.secutityjwt.dto.tokenDto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.tokenDto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }


    @PostMapping("/refresh")
    public JwtAuthenticationResponse refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authenticationService.refreshToken(refreshTokenRequest);
    }
}
