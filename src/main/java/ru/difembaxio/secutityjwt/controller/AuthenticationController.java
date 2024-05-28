package ru.difembaxio.secutityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.difembaxio.secutityjwt.dto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.dto.SignInRequest;
import ru.difembaxio.secutityjwt.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping
  public ResponseEntity<JwtAuthenticationResponse> signIn(
      @RequestBody SignInRequest signInRequest) {
    return ResponseEntity.ok(authenticationService.signIn(signInRequest));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthenticationResponse> refresh(
      @RequestBody RefreshTokenRequest refreshTokenRequest) {
    return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
  }
}
