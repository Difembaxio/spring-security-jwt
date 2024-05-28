package ru.difembaxio.secutityjwt.service.impl;


import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.dto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.dto.RegistrationRequest;
import ru.difembaxio.secutityjwt.dto.SignInRequest;
import ru.difembaxio.secutityjwt.model.Role;
import ru.difembaxio.secutityjwt.model.User;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.AuthenticationService;
import ru.difembaxio.secutityjwt.service.JwtService;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final PasswordEncoder passwordEncoder;

  @Override
  public User registrationUser(RegistrationRequest singUpRequest) {
    User user = new User();
    user.setLogin(singUpRequest.getLogin());
    user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
    user.setRole(Role.USER);

    return userRepository.save(user);

  }

  @Override
  public User registrationAdmin(RegistrationRequest singUpRequest) {
    User user = new User();
    user.setLogin(singUpRequest.getLogin());
    user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
    user.setRole(Role.ADMIN);

    return userRepository.save(user);
  }


  public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        signInRequest.getLogin(), signInRequest.getPassword()));

    var user = userRepository.findUserByLogin(signInRequest.getLogin()).orElseThrow(
        IllegalArgumentException::new);
    var jwt = jwtService.generateToken(user);
    var jwtRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

    jwtAuthenticationResponse.setAccessToken(jwt);
    jwtAuthenticationResponse.setRefreshToken(jwtRefreshToken);
    return jwtAuthenticationResponse;
  }

  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
    String login = jwtService.extractUserName(refreshTokenRequest.getRefreshToken());
    User user = userRepository.findUserByLogin(login).orElseThrow();
    if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
      var jwt = jwtService.generateToken(user);
      JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

      jwtAuthenticationResponse.setAccessToken(jwt);
      jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
      return jwtAuthenticationResponse;
    }
    return null;
  }
}